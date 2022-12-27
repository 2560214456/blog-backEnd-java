package com.blog.business.essay.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.business.essay.domian.BloEssay;
import com.blog.business.essay.service.BloEssayService;
import com.blog.business.tag.domain.BloTag;
import com.blog.common.core.controller.BaseController;
import com.blog.common.core.domain.AjaxResult;
import com.blog.common.core.page.TableDataInfo;
import com.blog.common.utils.poi.ExcelUtil;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/blog/essay")
public class BloEssayController extends BaseController {
    @Autowired
    private BloEssayService essayService;

    @PreAuthorize("@ss.hasPermi('blog:essay:list')")
    @GetMapping("/list")
    public TableDataInfo list(BloEssay essay){
        QueryWrapper<BloEssay> wrapper = new QueryWrapper<>(essay);
        startPage();
        List<BloEssay> list = essayService.list(wrapper);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('blog:essay:query')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable Long id){
        return success(essayService.getById(id));
    }

    @PreAuthorize("@ss.hasPermi('blog:essay:add')")
    @PostMapping
    public AjaxResult add(@RequestBody BloEssay essay){
        return toAjax(essayService.save(essay));
    }

    @PreAuthorize("ss.hasPermi('blog:essay:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody BloEssay essay){
        return toAjax(essayService.updateById(essay));
    }

    @PreAuthorize("ss.hasPermi('blog:essay:remove')")
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable List<Long> ids){
        return toAjax(essayService.removeBatchByIds(ids));
    }

    @PreAuthorize("@ss.hasPermi('blog:essay:export')")
    @PostMapping("/export")
    public void export(@RequestBody BloEssay essay, HttpServletResponse response){
        QueryWrapper<BloEssay> wrapper = new QueryWrapper<>(essay);
        List<BloEssay> list = essayService.list(wrapper);
        ExcelUtil<BloEssay> excelUtil = new ExcelUtil<>(BloEssay.class);
        excelUtil.exportExcel(response,list,"随笔数据");
    }
}
