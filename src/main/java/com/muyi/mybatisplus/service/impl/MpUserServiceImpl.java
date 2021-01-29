package com.muyi.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muyi.mybatisplus.mapper.MpUserMapper;
import com.muyi.mybatisplus.pojo.MpUserPojo;
import com.muyi.mybatisplus.service.MpUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 继承MyBatis-Plus提供的ServiceImpl，定好Mapper和DO
 * 实现自己的Service
 * <p>
 * 但不得不说，这种写法很恶心，语义上讲不通
 */
@Service
public class MpUserServiceImpl extends ServiceImpl<MpUserMapper, MpUserPojo> implements MpUserService{

    @Autowired
    private MpUserService userService;

    /**
     * 重新理解rollbackFor和propagation
     * 先说结论：
     *      并不是所有的异常都会触发事务回滚，所以最好指定rollbackFor （一般图省事直接指定Exception.class）
     *      propagation是写给调用者看的，而不是写给被调用者看的
     *
     * 最好指定rollbackFor：
     * 虽然rollbackFor默认制定了异常类型，但仅仅包括Error和RuntimeException。
     * 如果是其他自定义的业务类型，就不会触发回滚（理论上是这样，但通常业务异常都会继承自RuntimeException，因为运行时异常无需强制处理）。
     *
     * propagation的案例；99%情况下都是默认REQUIRED，只需要注意rollbackFor即可。
     *
     * Propagation.REQUIRED：如果当前存在事务，则加入该事务，如果当前不存在事务，则创建一个新的事务。
     * （也就是说如果A方法和B方法都添加了注解，在默认传播模式下，A方法内部调用B方法，会把两个方法的事务合并为一个事务 ）
     *
     * Propagation.SUPPORTS：如果当前存在事务，则加入该事务；如果当前不存在事务，则以非事务的方式继续运行。
     *
     * Propagation.MANDATORY：如果当前存在事务，则加入该事务；不过当前不存在事务，则抛出异常。也就是要求调用方法必须存在事务。
     * mandatory：强制的
     *
     * Propagation.REQUIRES_NEW：重新创建一个新的事务，和外面的事务相互独立。
     *
     * Propagation.NOT_SUPPORTED：以非事务的方式运行，无论调用者是否存在事务，自己都不受其影响
     *
     * Propagation.NEVER：以以非事务的方式运行，如果当前存在事务，则抛出异常。
     * （即如果methodB设置了NEVER，而methodA设置了事务，那么调用methodB时就会抛出异常。它不想在有事务的方法内运行。）
     *
     * Propagation.NESTED：和Propagation.REQUIRED效果一样
     */
    @Transactional(rollbackFor = Exception.class)
    public void showTransactional(){
        userService.updateById(new MpUserPojo());
        int i = 1/0;
        userService.updateById(new MpUserPojo());
        
    }


































}