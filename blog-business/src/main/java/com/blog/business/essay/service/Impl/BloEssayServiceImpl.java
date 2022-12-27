package com.blog.business.essay.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.business.essay.domian.BloEssay;
import com.blog.business.essay.mapper.BloEssayMapper;
import com.blog.business.essay.service.BloEssayService;
import org.springframework.stereotype.Service;

@Service
public class BloEssayServiceImpl extends ServiceImpl<BloEssayMapper, BloEssay> implements BloEssayService {
}
