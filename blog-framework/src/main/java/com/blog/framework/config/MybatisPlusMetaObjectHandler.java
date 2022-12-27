package com.blog.framework.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.blog.common.constant.HttpStatus;
import com.blog.common.core.domain.BaseEntity;
import com.blog.common.core.domain.entity.SysUser;
import com.blog.common.exception.ServiceException;
import com.blog.common.utils.SecurityUtils;
import com.blog.common.utils.StringUtils;
import com.blog.common.utils.spring.SpringUtils;
import com.blog.framework.web.domain.server.Sys;
import com.blog.system.service.ISysUserService;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.rmi.ServerException;
import java.util.Date;

@Component
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {

    private static String CREATE_BY = "createBy"; //创建人
    private static String CREATE_TIME = "createTime"; // 创建时间
    private static String UPDATE_BY = "updateBy"; // 修改人
    private static String UPDATE_TIME = "updateTime"; // 修改时间


    private static String MODIFIED_DATE = "et.modifiedDate";
    private static String MODIFIED_BY = "modifiedBy";
    private static String IS_DELETED = "isDeleted";
    /**
     * 新增
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {

        // Object enabledMark = this.getFieldValByName("enabledMark", metaObject);
        // Object creatorUserId = this.getFieldValByName("creatorUserId", metaObject);
        // Object creatorTime = this.getFieldValByName("creatorTime", metaObject);
        // Object creatorUser = this.getFieldValByName("creatorUser", metaObject);

        SysUser user;
        if(new Boolean(true).equals(SecurityUtils.isApp.get())) {
            ISysUserService sysUserService = SpringUtils.getBean(ISysUserService.class);
            user = sysUserService.selectUserById(1l);
        }else {
            user = SecurityUtils.getLoginUser().getUser();
        }

        newInsert(metaObject,user);

    }
    private void newInsert(MetaObject metaObject, SysUser user){
        try {
            if(metaObject != null && metaObject.getOriginalObject() instanceof BaseEntity){
                BaseEntity baseEntity =  (BaseEntity) metaObject.getOriginalObject();
                Date current = new Date();
                this.setFieldValByName(CREATE_TIME,current,metaObject); //创建时间
                this.setFieldValByName(UPDATE_TIME,current,metaObject); // 更新时间
                // 当前已登录 并且 创建人为空
                if(StringUtils.isNotBlank(user.getUserName()) && StringUtils.isBlank(baseEntity.getCreateBy())){
                    // this.setFieldValByName(CREATE_BY, SecurityUtils.getUsername(), metaObject);
                    // this.setFieldValByName(UPDATE_BY,SecurityUtils.getUsername(),metaObject);
                    baseEntity.setCreateBy(user.getUserName()); // 创建人

                }
                // 当前已登录 并且 更新人为空
                if(StringUtils.isNotBlank(user.getUserName()) && StringUtils.isBlank(baseEntity.getUpdateBy())){
                    baseEntity.setUpdateBy(user.getUserName()); // 修改人
                }
            }
        }catch (Exception e){
            throw new ServiceException("自动注入异常 ----> "+e.getMessage(),HttpStatus.UNAUTHORIZED);
        }
    }


    /**
     * 修改
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        SysUser user;
        if(new Boolean(true).equals(SecurityUtils.isApp.get())) {
            ISysUserService sysUserService = SpringUtils.getBean(ISysUserService.class);
            user = sysUserService.selectUserById(1l);
        }else {
            user = SecurityUtils.getLoginUser().getUser();
        }

        newUpdate(metaObject,user);
    }
    private void newUpdate(MetaObject metaObject,SysUser user){
        try {
            if (metaObject != null && metaObject.getOriginalObject() instanceof BaseEntity) {
                BaseEntity baseEntity = (BaseEntity) metaObject.getOriginalObject();
                Date current = new Date();
                baseEntity.setUpdateTime(current);
                // 当前已登录 更新人填充
                if(StringUtils.isNotBlank(user.getUserName())){
                    baseEntity.setUpdateBy(user.getUserName());
                }
            }
        } catch (Exception e){
            throw new ServiceException("自动注入失败 -----> "+e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}
