package com.blog.business.type.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.business.type.domain.BloType;

/**
 * 博客分类Mapper接口
 *
 * @author blog
 * @date 2022-12-16
 */
public interface BloTypeMapper extends BaseMapper<BloType>
{
    /**
     * 查询博客分类
     *
     * @param id 博客分类主键
     * @return 博客分类
     */
    public BloType selectBloTypeById(Long id);

    /**
     * 查询博客分类列表
     *
     * @param bloType 博客分类
     * @return 博客分类集合
     */
    public List<BloType> selectBloTypeList(BloType bloType);

    /**
     * 新增博客分类
     *
     * @param bloType 博客分类
     * @return 结果
     */
    public int insertBloType(BloType bloType);

    /**
     * 修改博客分类
     *
     * @param bloType 博客分类
     * @return 结果
     */
    public int updateBloType(BloType bloType);

    /**
     * 删除博客分类
     *
     * @param id 博客分类主键
     * @return 结果
     */
    public int deleteBloTypeById(Long id);

    /**
     * 批量删除博客分类
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBloTypeByIds(Long[] ids);
}
