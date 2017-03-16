package com.zy17.world.dao;

import com.zy17.world.energy.BaseSpringTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 2017/3/16 yanzhang153
 */
public class ThingHisRepositoryTest extends BaseSpringTest {
  @Autowired
  ThingHisRepository dao;

  @Test
  public void test(){
    dao.save(new ThingHis());
  }

}