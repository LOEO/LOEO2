package com.loeo.mybatis;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.loeo.common.uniquekey.IdGererateFactory;
import com.loeo.shiro.ShiroContextUtils;
import com.loeo.utils.ApplicationContextUtils;
import com.loeo.utils.DateUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.util.StringUtils;

/**
 * 功能：
 *
 * @author：LT(286269159@qq.com)
 * @create：2018-03-03 15:19:15
 * @version：2018 Version：1.0
 * @company：创海科技 Created with IntelliJ IDEA
 */
public class LoeoMateObjectHandler extends MetaObjectHandler {
    private IdGererateFactory idGererateFactory;

    @Override
    public void insertFill(MetaObject metaObject) {
        if (StringUtils.isEmpty(getFieldValByName("id",metaObject))) {
            setFieldValByName("id", getIdGererateFactory().nextStringId(), metaObject);
        }
        setFieldValByName("createUser", ShiroContextUtils.getCurUserId(), metaObject);
        setFieldValByName("createDt", DateUtils.now(), metaObject);
    }


    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updateUser", ShiroContextUtils.getCurUserId(), metaObject);
        setFieldValByName("updateDt", DateUtils.now(), metaObject);
    }

    public MetaObjectHandler setFieldValByName(String fieldName, Object fieldVal, MetaObject metaObject) {
        if (metaObject.hasSetter(fieldName) && metaObject.hasGetter(fieldName)) {
            metaObject.setValue(fieldName, fieldVal);
        } else if (metaObject.hasGetter(META_OBJ_PREFIX)) {
            Object et = metaObject.getValue(META_OBJ_PREFIX);
            if (et != null) {
                MetaObject etMeta = SystemMetaObject.forObject(et);
                if (etMeta.hasSetter(fieldName)) {
                    etMeta.setValue(fieldName, fieldVal);
                }
            }
        }
        return this;
    }

    public Object getFieldValByName(String fieldName, MetaObject metaObject) {
        if (metaObject.hasGetter(fieldName)) {
            return metaObject.getValue(fieldName);
        } else if (metaObject.hasGetter(META_OBJ_PREFIX + "." + fieldName)) {
            return metaObject.getValue(META_OBJ_PREFIX + "." + fieldName);
        }
        return null;
    }

    private IdGererateFactory getIdGererateFactory() {
        if (idGererateFactory == null) {
            idGererateFactory = ApplicationContextUtils.getBean(IdGererateFactory.class);
        }
        return idGererateFactory;
    }

}
