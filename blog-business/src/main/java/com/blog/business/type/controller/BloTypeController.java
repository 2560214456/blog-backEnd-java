package com.blog.business.type.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blog.common.exception.ServiceException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.blog.common.annotation.Log;
import com.blog.common.core.controller.BaseController;
import com.blog.common.core.domain.AjaxResult;
import com.blog.common.enums.BusinessType;
import com.blog.business.type.domain.BloType;
import com.blog.business.type.service.IBloTypeService;
import com.blog.common.utils.poi.ExcelUtil;
import com.blog.common.core.page.TableDataInfo;

/**
 * 博客分类Controller
 *
 * @author blog
 * @date 2022-12-16
 */
@RestController
@RequestMapping("/blog/type")
public class BloTypeController extends BaseController
{
    @Autowired
    private IBloTypeService bloTypeService;

    /**
     * 查询博客分类列表
     */
    @PreAuthorize("@ss.hasPermi('blog:type:list')")
    @GetMapping("/list")
    public TableDataInfo list(BloType bloType)
    {
        startPage();
        List<BloType> list = bloTypeService.selectBloTypeList(bloType);
        return getDataTable(list);
    }

    /**
     * 导出博客分类列表
     */
    @PreAuthorize("@ss.hasPermi('blog:type:export')")
    @Log(title = "博客分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BloType bloType)
    {
        List<BloType> list = bloTypeService.selectBloTypeList(bloType);
        ExcelUtil<BloType> util = new ExcelUtil<BloType>(BloType.class);
        util.exportExcel(response, list, "博客分类数据");
    }

    /**
     * 获取博客分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('blog:type:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bloTypeService.getById(id));
    }

    /**
     * 新增博客分类
     */
    @PreAuthorize("@ss.hasPermi('blog:type:add')")
    @Log(title = "博客分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BloType bloType)
    {
        long count = bloTypeService.count(new LambdaQueryWrapper<BloType>().eq(BloType::getName, bloType.getName()));
        if(count > 0){
            throw new ServiceException("分类名称重复");
        }
        return toAjax(bloTypeService.save(bloType));
    }

    /**
     * 修改博客分类
     */
    @PreAuthorize("@ss.hasPermi('blog:type:edit')")
    @Log(title = "博客分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BloType bloType)
    {
        long count = bloTypeService.count(new LambdaQueryWrapper<BloType>()
                .eq(BloType::getName, bloType.getName())
                .ne(BloType::getId,bloType.getId())
        );
        if(count > 0){
            throw new ServiceException("分类名称重复");
        }
        return toAjax(bloTypeService.updateById(bloType));
    }

    /**
     * 删除博客分类
     */
    @PreAuthorize("@ss.hasPermi('blog:type:remove')")
    @Log(title = "博客分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bloTypeService.removeByIds(CollectionUtils.arrayToList(ids)));
    }
}
