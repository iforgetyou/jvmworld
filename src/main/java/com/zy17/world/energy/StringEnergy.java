package com.zy17.world.energy;

import java.util.Random;

/**
 * Created by zy17 on 2016/12/3.
 */
public class StringEnergy {

  public static String getRandomStr() {
    int length = 8;
    String base = "abcdefghijklmnopqrstuvwxyz0123456789";
    Random random = new Random();
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < length; i++) {
      int number = random.nextInt(base.length());
      sb.append(base.charAt(number));
    }
    return sb.toString();
  }
}
