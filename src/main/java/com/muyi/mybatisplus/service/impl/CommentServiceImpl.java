package com.muyi.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muyi.mybatisplus.mapper.CommentMapper;
import com.muyi.mybatisplus.mapper.MpUserMapper;
import com.muyi.mybatisplus.pojo.Comment;
import com.muyi.mybatisplus.pojo.MpUserPojo;
import com.muyi.mybatisplus.service.CommentService;
import com.muyi.mybatisplus.service.MpUserService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService
{
}
