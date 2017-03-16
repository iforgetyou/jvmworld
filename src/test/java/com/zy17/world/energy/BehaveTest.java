package com.zy17.world.energy;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 2017/3/15 zy17
 */
public class BehaveTest extends BaseSpringTest {
  @Autowired
  Behave behave;
  @Autowired
  Energy energy;

  @Test
  public void act() throws Exception {
    Method[] methods = Integer.class.getMethods();
    for (Method method : methods) {
      try {
        behave.act(method);
      } catch (Exception e) {
//        e.printStackTrace();
        System.out.println(e);
      }
    }
  }

  @Test
  public void newInstance() throws IllegalAccessException, InvocationTargetException, InstantiationException {
    Object o = energy.getT(String.class);
    System.out.println(o);
  }


  @Test
  public void objectNewInstance() throws IllegalAccessException, InstantiationException {
    Object t = energy.getT(java.lang.Throwable.class);
    System.out.println(t);
  }

  @Test
  public void objectEquere(){
    Method[] methods = Object.class.getMethods();
    for (Method method : methods) {
      try {
        behave.act(method);
      } catch (Exception e) {
//        e.printStackTrace();
        System.out.println(e);
      }
    }
  }

}