package com.zy17.world.energy;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.util.Random;

/**
 * Created by zy17 on 2016/12/3.
 */
@Slf4j
@Component
public class RandomEnergy implements Energy {
  Random random = new Random();

  public Object getT(Class<?> clazz) {
    Object o = null;

    if (clazz == int.class) {
      return random.nextInt();
    }
    if (clazz == float.class) {
      return random.nextFloat();
    }
    if (clazz == double.class) {
      return random.nextDouble();
    }
    if (clazz == byte.class) {
      return (byte) random.nextInt();
    }
    if (clazz == short.class) {
      return (short) random.nextInt();
    }
    if (clazz == long.class) {
      return random.nextLong();
    }
    if (clazz == boolean.class) {
      return random.nextBoolean();
    }
    if (clazz == String.class) {
      return getRandomStr();
    }
    if (clazz == byte[].class) {
      return getRandomStr().getBytes();
    }

    if (clazz instanceof Object) {
      o = newInstanceByConstructorNewInstance(clazz);
    }
    if (o == null) {
      log.warn("no resource for {}", clazz);
    }
    return o;
  }

  public String getRandomStr() {
    int length = 8;
    String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < length; i++) {
      int number = random.nextInt(base.length());
      sb.append(base.charAt(number));
    }
    return sb.toString();
  }


  /*通过Constructor.newInstance()创建新的类示例*/
  public Object newInstanceByConstructorNewInstance(Class<?> aClass) {
    Constructor<?>[] constructors = aClass.getConstructors();
    for (Constructor<?> constructor : constructors) {
      try {
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        // 排除递归构造方法
        Object[] objects = new Object[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
          if (parameterTypes[i] == aClass) {
            throw new Exception("StackOverflowError");
          }
          objects[i] = getT(parameterTypes[i]);
        }
        return constructor.newInstance(objects);
      } catch (Exception e) {
        log.debug(e.getMessage());
      }
    }
    return null;
  }
}
