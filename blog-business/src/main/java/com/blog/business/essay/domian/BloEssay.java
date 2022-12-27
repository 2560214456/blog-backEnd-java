package com.blog.business.essay.domian;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.blog.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 *  随笔
 */
@Data
public class BloEssay extends BaseEntity {
    @TableId(type = IdType.ASSIGN_ID)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    /** 随笔标题 */
    private String title;
    /** 随笔内容 */
    private String content;
    /** 图片 */
    private String image;
    /** 边框颜色 */
    private String color;
}
