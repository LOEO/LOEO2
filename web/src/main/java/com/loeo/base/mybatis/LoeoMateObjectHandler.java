package com.loeo.base.mybatis;

import java.util.Date;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.loeo.common.uniquekey.IdGenerateFactory;
import com.loeo.base.shiro.ShiroContextUtils;
import com.loeo.utils.ApplicationContextUtils;
import com.loeo.utils.DateUtils;

/**
 * 功能：
 *
 * @author ：Tony.L(286269159@qq.com)
 * @create ：2018-03-03 15:19:15
 * @version ：2018 Version：1.0

 */
public class LoeoMateObjectHandler extends MetaObjectHandler {
    private IdGenerateFactory idGenerateFactory;

    @Override
    public void insertFill(MetaObject metaObject) {
        if (StringUtils.isEmpty(getFieldValByName("id",metaObject))) {
            setFieldValByName("id", getIdGenerateFactory().nextStringId(), metaObject);
        }
        String curUserId = getCurUserId();

        Date now = DateUtils.now();
        setFieldValByName("creator", curUserId, metaObject);
        setFieldValByName("created", now, metaObject);
        setFieldValByName("updater", curUserId, metaObject);
        setFieldValByName("updated", now, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updater", getCurUserId(), metaObject);
        setFieldValByName("updated", DateUtils.now(), metaObject);
    }

    private String getCurUserId() {
        String curUserId;
        try {
            curUserId = ShiroContextUtils.getCurUserId();
        } catch (Exception e) {
            //在没有登录的情况下调用ShiroContextUtils.getCurUserId()会发生异常，例如任务调度执行的sql
            curUserId = "auto";
        }
        return curUserId;
    }

    private IdGenerateFactory getIdGenerateFactory() {
        if (idGenerateFactory == null) {
            idGenerateFactory = ApplicationContextUtils.getBean(IdGenerateFactory.class);
        }
        return idGenerateFactory;
    }

}
