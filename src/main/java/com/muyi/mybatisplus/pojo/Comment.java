package com.muyi.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@TableName("t_comment")
public class Comment {

    /**
     * 评论id
     */
    private Integer id;

    /**
     * 所属一级评论的id，如果当前评论为一级，则为0
     */
    private Integer pid;

    /**
     * 评论所属文章id
     */
    private Integer targetId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 该条评论的作者
     */
    private String userId;

    /**
     * 对谁回复，一级评论可以为null
     */
    private String toUserId;

    /**
     * 当前评论的点赞数
     */
    private Integer likesCount;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 该条评论下的回复，非数据库字段，用@TableField(exist=false)忽略字段
     */
    @TableField(exist = false)
    private List<Comment> replies = new ArrayList<>();
}