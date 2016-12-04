package com.zy17.world.energy;


import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.alibaba.fastjson.JSON;

/**
 * 提供行为
 * Created by zy17 on 2016/12/3.
 */
@Component
public class Behave {

  public static void main(String[] args) throws Exception {
//    获取java所有class
//    获取class中的所有方法
//    反射调用
    Class clazz = java.lang.Math.class;
    Method[] methods = clazz.getMethods();
    RandomEnergy energy = new RandomEnergy();
    for (Method method : methods) {
      // 静态类不需要实例化
      // Object newObj = clazz.newInstance();

      // 获取所有参数类型
      Class<?>[] parameterTypes = method.getParameterTypes();
      // 根据参数类型提供
      Object[] objects = new Object[parameterTypes.length];

      for (int i = 0; i < parameterTypes.length; i++) {
        objects[i] = energy.getT(parameterTypes[i]);
      }
      Object result = null;
      try {
        result = method.invoke(null, objects);
      } catch (InvocationTargetException e) {
        System.out.println("invoke target error: " + e.getCause());
      } catch (Exception e) {
        System.out.println(e);
      } finally {
        System.out.println("method:" + method + " ,input:" + JSON.toJSONString(objects) + " ,result:" + result);
      }
    }
  }


  public static boolean isWrapClass(Class clz) {
    try {
      return ((Class) clz.getField("TYPE").get(null)).isPrimitive();
    } catch (Exception e) {
      return false;
    }
  }
}
