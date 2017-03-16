package com.zy17.world.energy;


import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 提供行为
 * Created by zy17 on 2016/12/3.
 */
@Slf4j
@Component
public class Behave {
  @Autowired
  Energy energy;

  public Object act(Method method) throws Exception {
    Object[] objects = null;
    Object result = null;
    try {
      // 获取所有参数类型
      Class<?>[] parameterTypes = method.getParameterTypes();
      Class<?> aClass = method.getDeclaringClass();
      objects = new Object[parameterTypes.length];
      // 根据参数类型提供
      for (int i = 0; i < parameterTypes.length; i++) {
        objects[i] = energy.getT(parameterTypes[i]);
      }
      int modifiers = method.getModifiers();
      Object obj = null;
      if (!Modifier.isStatic(modifiers)) {
        // 非静态，需要初始化实例
         obj = energy.getT(aClass);
      }

      result = method.invoke(obj, objects);
    } catch (Exception e) {
      throw e;
    } finally {
      log.info("method:{},input:{},result:{}", method, JSON.toJSONString(objects), result);
    }
    return result;
  }



}
