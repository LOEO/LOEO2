package com.loeo.mybatis;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.loeo.common.uniquekey.IdGererateFactory;
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
	private IdGererateFactory idGererateFactory;
	@Override
	public void insertFill(MetaObject metaObject) {
		if(StringUtils.isEmpty(metaObject.getValue("id"))){
			metaObject.setValue("id", getIdGererateFactory().nextStringId());
		}
		if (metaObject.hasSetter("createUser")) {
			metaObject.setValue("createUser", ShiroContextUtils.getCurUserId());
		}
		if (metaObject.hasSetter("createDt")) {
			metaObject.setValue("createDt", DateUtils.now());
		}
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		if (metaObject.hasSetter("updateUser")) {
			metaObject.setValue("updateUser", ShiroContextUtils.getCurUserId());
		}
		if (metaObject.hasSetter("updateDt")) {
			metaObject.setValue("updateDt", DateUtils.now());
		}
	}

	private IdGererateFactory getIdGererateFactory() {
		if (idGererateFactory == null) {
			idGererateFactory = ApplicationContextUtils.getBean(IdGererateFactory.class);
		}
		return idGererateFactory;
	}

}
