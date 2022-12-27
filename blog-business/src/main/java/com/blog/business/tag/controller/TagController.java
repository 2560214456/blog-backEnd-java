package com.blog.business.tag.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.business.tag.domain.BloTag;
import com.blog.business.tag.service.TagService;
import com.blog.common.core.controller.BaseController;
import com.blog.common.core.domain.AjaxResult;
import com.blog.common.core.page.TableDataInfo;
import com.blog.common.exception.ServiceException;
import com.blog.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/blog/Tag")
public class TagController extends BaseController {
    @Autowired
    private TagService tagService;

    /**
     * 查询列表
     */
    @PreAuthorize("@ss.hasPermi('blog:tag:list')")
    @GetMapping("/list")
    public TableDataInfo list(BloTag tag){
        QueryWrapper<BloTag> wrapper = new QueryWrapper<>(tag);
        startPage();
        List<BloTag> list = tagService.list(wrapper);
        return getDataTable(list);
    }

    /**
     * 获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('blog:tag:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id){
        return success(tagService.getById(id));
    }

    /**
     * 新增
     */
    @PreAuthorize("@ss.hasPermi('blog:tag:add')")
    @PostMapping
    public AjaxResult add(@RequestBody BloTag tag){
        long count = tagService.count(new LambdaQueryWrapper<BloTag>()
                .eq(BloTag::getName, tag.getName()));
        if(count > 0){
            throw new ServiceException("标签名称重复");
        }
        return toAjax(tagService.save(tag));
    }
    /**
     * 修改
     */
    @PreAuthorize("@ss.hasPermi('blog:tag:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody BloTag tag){
        long count = tagService.count(new LambdaQueryWrapper<BloTag>()
                .eq(BloTag::getName, tag.getName())
                .ne(BloTag::getId, tag.getId()));
        if (count > 0){
            throw new ServiceException("标签名称重复");
        }
        return toAjax(tagService.updateById(tag));
    }
    /**
     * 删除
     */
    @PreAuthorize("@ss.hasPermi('blog:tag:remove')")
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable List<Long> ids){
        return toAjax(tagService.removeBatchByIds(ids));
    }
    /**
     * 导出
     */
    @PreAuthorize("@ss.hasPermi('blog:tag:export')")
    @PostMapping("/export")
    public void export(@RequestBody BloTag tag, HttpServletResponse response){
        QueryWrapper<BloTag> wrapper = new QueryWrapper<>(tag);
        List<BloTag> list = tagService.list(wrapper);
        ExcelUtil<BloTag> excelUtil = new ExcelUtil<>(BloTag.class);
        excelUtil.exportExcel(response,list,"标签数据");
    }
}
