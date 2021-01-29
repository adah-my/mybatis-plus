package com.muyi.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.muyi.mybatisplus.mapper.MpUserMapper;
import com.muyi.mybatisplus.pojo.MpUserPojo;
import com.muyi.mybatisplus.service.MpUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 1.Service层更加易用，对Mapper做了一层封装
 *
 * 2.Service层的命名方式和Mapper不同
 * select --> get
 * insert --> save
 * update --> update
 * delete --> update
 *
 * 3.支持了批量擦操作，而却可以指定一次操作多少个，比如 saveBatch(Collection<T> entityList, int batchSize);
 *
 * 4.对于增删改查，返回值统一为boolean，而不是int(修改行数)
 *
 * 5.getOne(Wrapper, boolean)与BaseMapper的selctOne(Wrapper)不同，如果传false不会抛异常，有多个值则list.get(0)
 *   T getOne(Wrapper<T> queryWrapper, boolean throwEx);
 *
 * 6.Service层链式调用更顺手
 *
 */
@SpringBootTest
public class ServiceTest{

    @Autowired
    private MpUserService userService;

    @Test
    public void testServiceSave(){
        // ---------- 增 -----------
        // 插入
        MpUserPojo user = new MpUserPojo();
        user.setName("test Service");
        user.setAge(19);
        userService.save(user);

        user.setId(null);

        // 批量插入，即使不传入batchSize，默认也是1000条。比如实际有1w条，内部会按每次1000条批量插入
        boolean save = userService.saveBatch(Collections.singletonList(user), 1000);

    }

    /**
     * 特别注意，虽然Wrapper的条件设计设置为null不影响，但Wrapper本身不设置任何条件还是会触发全表更新
     */
    @Test
    public void testServiceUpdate(){
        // ------------ 改 LambdaWrapper和普通Wrapper的唯一区别是 一个用POJO的字段，一个用数据库的字段表示条件 -------------
        // 根据id更新
        MpUserPojo updateUser = new MpUserPojo();
        updateUser.setId(1352075979412594690L);
        updateUser.setName("muyi");
        updateUser.setAge(36);
        userService.updateById(updateUser);

        // 条件更新 方式1 lambdaUpdate，用D0的字段
        userService.lambdaUpdate().eq(MpUserPojo::getName, "测试1").set(MpUserPojo::getAge, 78).update();

        // 条件更新 方式2 传入LambdaUpdateWrapper，用数据库字段
        userService.update().eq("name", "muyi").set("age", 19).update();

        // 条件更新 方式3 传入LambdaUpdateWrapper，用DO的字段
        userService.update(new LambdaUpdateWrapper<MpUserPojo>().eq(MpUserPojo::getName, "muyi").set(MpUserPojo::getAge, 10));

        // 条件更新 方式4 传入UpdateWrapper，用数据库字段
        userService.update(new UpdateWrapper<MpUserPojo>().eq("name", "muyi").set("age", 67));

        // 条件更新 方式5 传入Entity表示更新的字段，QueryWrapper表示条件
        userService.update(updateUser, new QueryWrapper<MpUserPojo>().lambda().eq(MpUserPojo::getName, "muyi"));

        // 批量更新。如果要根据条件批量更新还是自己写吧，注意SQL
        boolean update = userService.updateBatchById(Collections.singletonList(updateUser), 1000);
    }


    @Test
    public void testServiceGet(){
        // ---------- 查 -----------
        // 条件查询 方式1 lambdaQuery，用DO的字段
        List<MpUserPojo> queryList = userService.lambdaQuery().eq(MpUserPojo::getName, "muyi")
                .select(MpUserPojo::getName, MpUserPojo::getAge)
                .list();

        // 条件查询 方式2 普通Query，用数据库字段
        List<MpUserPojo> queryList2 = userService.query().ge("age", 19).select("name", "age").list();

        // 条件查询 方式3 传入LambdaQueryWrapper，用DO的字段
        List<MpUserPojo> queryList3 = userService.list(new LambdaQueryWrapper<MpUserPojo>().eq(MpUserPojo::getName, "muyi"));

        // 条件查询 方式4 传入QueryWrapper，用数据库字段
        List<MpUserPojo> queryList4 = userService.list(new QueryWrapper<MpUserPojo>().eq("name", "muyi"));

        // getOne条件查询，有个重载方法 getOne(Wrapper<T> queryWrapper, boolean throwEx);
        MpUserPojo getOne = userService.getOne(Wrappers.<MpUserPojo>lambdaQuery().eq(MpUserPojo::getName, "muyi"));

        // 批量查询
        List<MpUserPojo> listBatch = userService.listByIds(Arrays.asList(1L, 2L, 3L));

        // 分页
        Page<MpUserPojo> page = new Page<>();
        page.setPages(1).setSize(1);
        Page<MpUserPojo> pageList = userService.page(page, new QueryWrapper<MpUserPojo>().lambda().eq(MpUserPojo::getName, "muyi"));
        System.out.println(pageList.getRecords());

        // count
        int count = userService.count(new LambdaQueryWrapper<MpUserPojo>().eq(MpUserPojo::getName, "muyi"));
    }

    /**
     * 特别注意，虽然Wrapper的条件设置为null不影响，但Wrapper本身不设置任何条件还是会触发全表删除
     */
    @Test
    public void testServiceRemove(){
        // ------------- 删 和通用Mapper不同的是，@TableLogic对批量删除也是起作用的 -----------------
        // 根据id删除
        userService.removeById(1L);

        // 条件删除， 特别注意，Wrapper不设置任何条件还是会触发全表删除
        userService.remove(Wrappers.<MpUserPojo>lambdaQuery());

        // 根据ids批量删除 UPDATE mp_user SET deleted=1 WHERE id IN (1, 2, 3) AND deleted=0;
        boolean remove = userService.removeByIds(Arrays.asList(1L, 2L, 3L));
    }
}