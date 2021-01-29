package com.muyi.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 通用Mapper中叫 @Table(name = "tk_user")
 */
@Data
@ApiModel("用户DTO")
@TableName("mp_user")
public class MpUserPojo{

    /**
     * MyVatis-Plus默认名为'id'的字段是主键
     * 如果主键名不叫'id'，而是'userId'之类的，必须通过 @TableId标识
     * 主键生成策略默认是无意义的long数值，可以指定@TableId的IdType属性为AUTO，随数据库自增
     * 加上 @TableId(Type = IdType.AUTO)
     */
    @ApiModelProperty(value = "id", dataType = "Long", required = true)
    private Long id;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名", dataType = "String", required = true)
    private String name;

    /**
     * 年龄
     */
    @ApiModelProperty(value = "年龄", dataType = "String", required = true)
    private Integer age;

    /**
     * 用户类型
     */
    @ApiModelProperty(value = "用户类型", dataType = "Integer", required = true)
    private Integer userType;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", dataType = "Date", required = true)
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间", dataType = "Date", required = true)
    private Date updateTime;

    /**
     * 是否删除，逻辑删除请用 @TableLogic
     */
    @TableLogic
    @ApiModelProperty(value = "是否删除", dataType = "Boolean", required = true)
    private Boolean deleted;

    /**
     * 乐观锁版本号，需要乐观锁请用 @Version
     * 支持的字段类型：
     * long，
     * Long，
     * int，
     * Integer，
     * java.util.Date,
     * java.sql.Timestamp,
     * java.time.LocalDateTime
     */
    @Version
    @ApiModelProperty(value = "乐观锁版本号", dataType = "Integer", required = true)
    private Integer version;
    
}