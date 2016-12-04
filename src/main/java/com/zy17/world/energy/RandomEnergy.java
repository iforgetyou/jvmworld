package com.zy17.world.energy;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Created by zy17 on 2016/12/3.
 */
@Component
public class RandomEnergy implements Energy {
  Random random = new Random();

  public Object getT(Class<?> clazz) {
    Object o = null;
    if (clazz == Integer.TYPE) {
      o = random.nextInt();
    }
    if (clazz == Float.TYPE) {
      o=random.nextFloat();
    }
    if (clazz == Double.TYPE) {
      o=random.nextDouble();
    }
    if (clazz == Byte.TYPE) {
      System.out.println("Byte...");
    }
    if (clazz == Long.TYPE) {
      o=random.nextLong();
    }
    if (clazz == Boolean.TYPE) {
      o=random.nextBoolean();
    }
    if (clazz == String.class) {
      o = getRandomStr();
    }
    return o;
  }

  public String getRandomStr() {
    int length = 8;
    String base = "abcdefghijklmnopqrstuvwxyz0123456789";
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < length; i++) {
      int number = random.nextInt(base.length());
      sb.append(base.charAt(number));
    }
    return sb.toString();
  }
}
