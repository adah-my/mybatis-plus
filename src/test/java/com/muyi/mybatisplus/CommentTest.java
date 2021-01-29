package com.muyi.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.muyi.mybatisplus.pojo.Comment;
import com.muyi.mybatisplus.pojo.MpUserPojo;
import com.muyi.mybatisplus.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class CommentTest {

    @Autowired
    private CommentService commentService;

    @Test
    public void testComment() throws JsonProcessingException
    {
        // ==========查出targetId下所有评论（一篇文章下的所有评论）==============
        List<Comment> commentList = commentService.list(new LambdaQueryWrapper<Comment>().eq(Comment::getTargetId, 10086));

        // ==========对平铺数据进行嵌套整理=============
        // 最终结果
        ArrayList<Comment> result = new ArrayList<>();

        // list转map,建立索引
        HashMap<Integer, Comment> commentMap = new HashMap<>();
        for (Comment comment : commentList)
        {
            commentMap.put(comment.getId(), comment);
        }

        for (Comment comment : commentList)
        {
            if (comment.getPid() == 0){
                result.add(comment);
            }else{
                // 二级评论，那么肯定有一级评论且firstComment一定不为null
                Comment firstComment = commentMap.get(comment.getPid());
                // 把二级评论塞到一级评论下
                firstComment.getReplies().add(comment);
            }
        }
        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(result));
    }
}
