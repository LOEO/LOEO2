package com.loeo.mybatis;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.loeo.common.uniquekey.IdGenerateFactory;
import com.loeo.shiro.ShiroContextUtils;
import com.loeo.utils.ApplicationContextUtils;
import com.loeo.utils.DateUtils;

/**
 * 功能：
 *
 * @author：LT(286269159@qq.com)
 * @create：2018-03-03 15:19:15
 * @version：2018 Version：1.0
 * @company：创海科技 Created with IntelliJ IDEA
 */
public class LoeoMateObjectHandler extends MetaObjectHandler {
    private IdGenerateFactory idGenerateFactory;

    @Override
    public void insertFill(MetaObject metaObject) {
        if (StringUtils.isEmpty(getFieldValByName("id",metaObject))) {
            setFieldValByName("id", getIdGenerateFactory().nextStringId(), metaObject);
        }
        setFieldValByName("createUser", ShiroContextUtils.getCurUserId(), metaObject);
        setFieldValByName("createDt", DateUtils.now(), metaObject);
    }


    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updateUser", ShiroContextUtils.getCurUserId(), metaObject);
        setFieldValByName("updateDt", DateUtils.now(), metaObject);
    }

    private IdGenerateFactory getIdGenerateFactory() {
        if (idGenerateFactory == null) {
            idGenerateFactory = ApplicationContextUtils.getBean(IdGenerateFactory.class);
        }
        return idGenerateFactory;
    }

}
