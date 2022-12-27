package com.blog.business.type.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.blog.common.annotation.Excel;
import com.blog.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 博客分类对象 blo_type
 *
 * @author blog
 * @date 2022-12-16
 */
@Data
public class BloType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 博客分类id */
    @TableId(type = IdType.ASSIGN_ID)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String name;

    /** 背景颜色 */
    private String color;

    /** 分类图片 */
    @Excel(name = "分类图片")
    private String picUrl;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setColor(String color)
    {
        this.color = color;
    }

    public String getColor()
    {
        return color;
    }
    public void setPicUrl(String picUrl)
    {
        this.picUrl = picUrl;
    }

    public String getPicUrl()
    {
        return picUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("color", getColor())
            .append("picUrl", getPicUrl())
            .toString();
    }
}
