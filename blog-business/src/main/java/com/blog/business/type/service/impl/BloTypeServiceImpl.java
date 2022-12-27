package com.blog.business.type.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blog.business.type.mapper.BloTypeMapper;
import com.blog.business.type.domain.BloType;
import com.blog.business.type.service.IBloTypeService;

/**
 * 博客分类Service业务层处理
 *
 * @author blog
 * @date 2022-12-16
 */
@Service
public class BloTypeServiceImpl extends ServiceImpl<BloTypeMapper,BloType>  implements IBloTypeService
{
    @Autowired
    private BloTypeMapper bloTypeMapper;

    /**
     * 查询博客分类
     *
     * @param id 博客分类主键
     * @return 博客分类
     */
    @Override
    public BloType selectBloTypeById(Long id)
    {
        return bloTypeMapper.selectBloTypeById(id);
    }

    /**
     * 查询博客分类列表
     *
     * @param bloType 博客分类
     * @return 博客分类
     */
    @Override
    public List<BloType> selectBloTypeList(BloType bloType)
    {
        return bloTypeMapper.selectBloTypeList(bloType);
    }

    /**
     * 新增博客分类
     *
     * @param bloType 博客分类
     * @return 结果
     */
    @Override
    public int insertBloType(BloType bloType)
    {
        return bloTypeMapper.insertBloType(bloType);
    }

    /**
     * 修改博客分类
     *
     * @param bloType 博客分类
     * @return 结果
     */
    @Override
    public int updateBloType(BloType bloType)
    {
        return bloTypeMapper.updateBloType(bloType);
    }

    /**
     * 批量删除博客分类
     *
     * @param ids 需要删除的博客分类主键
     * @return 结果
     */
    @Override
    public int deleteBloTypeByIds(Long[] ids)
    {
        return bloTypeMapper.deleteBloTypeByIds(ids);
    }

    /**
     * 删除博客分类信息
     *
     * @param id 博客分类主键
     * @return 结果
     */
    @Override
    public int deleteBloTypeById(Long id)
    {
        return bloTypeMapper.deleteBloTypeById(id);
    }
}
