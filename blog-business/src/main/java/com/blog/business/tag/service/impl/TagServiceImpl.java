package com.blog.business.tag.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.business.tag.domain.BloTag;
import com.blog.business.tag.mapper.TagMapper;
import com.blog.business.tag.service.TagService;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, BloTag> implements TagService {
}
