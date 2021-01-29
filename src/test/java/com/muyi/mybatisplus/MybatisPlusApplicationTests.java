package com.muyi.mybatisplus;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisPlusApplicationTests
{

    /**
     * MyBatis-Plus VS 通用Mapper
     *      MyBatis-Plus默认是Selective的，避免插入null导致数据库默认值失效，实在想改也行，但是不建议
     *      MyBatis-Plus默认会生成id，如果不需要可以自行配置，且默认"id"为主键，但可以通过@TableId修改
     *      MyBatis-Plus提供了Wrapper接口，使用更安全，能避免一部分条件空值导致的全表修改，但Wrapper为空还是会导致全表修改
     *      MyBatis-Plus和通用Mapper一样默认是物理删除，但可配置为逻辑删除
     *      MyBatis-Plus条件构造可以使用数据库字段也可以用POJO属性(Lambda)，通用Mapper使用的是POJO属性
     *      MyBatis-Plus自带分页功能，而通用Mapper需要额外引入PageHelper
     *      MyBatis-Plus做乐观锁很简单
     *      MyBatis-Plus还有一些插件能防止全表更新、删除
     *      MyBatis-Plus可以帮我们分批执行批量SQL，比如1次1000条（MyBatis默认批量操作一次最多发送1w+）
     *
     * 可以说MyBatis-Plus几乎是完胜Mapper的...硬要说缺点，MyBatis-Plus比通用Mapper灵活意味着它更难掌握，前者上手的难度大概是后者的2倍，尤其是Wrapper那一块，api太多了。
     * 
     */
    @Test
    void contextLoads()
    {
    }

}
