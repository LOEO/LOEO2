package com.loeo.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * Created by LT on 2015/12/13 0013 21:41
 */
public class EntityUtil {
    public static <T> T buildEntity(Class<T> cls, Map<String,Object> formData) {
        String jsonStr = JSON.toJSONString(formData);
        return JSON.parseObject(jsonStr, cls);
    }

    public static <T> List<T> buildEntityList(Class<T> cls, List<Map<String,Object>> formDatas) {
        List<T> list = new ArrayList<T>();
        for(int i=0;i<formDatas.size();i++) {
            list.add(buildEntity(cls,formDatas.get(i)));
        }
        return list;
    }

}
