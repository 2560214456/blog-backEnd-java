package com.blog.business.tag.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.blog.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 博客标签对象
 */
@Data
public class BloTag extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 博客标签id */
    @TableId(type = IdType.ASSIGN_ID)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;


    /** 标签名称 */
    private String name;
}
