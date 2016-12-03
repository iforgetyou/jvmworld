package com.zy17.world.creator;

import com.zy17.world.alive.AThing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by zy17 on 2016/12/3.
 */
@Component
public class Creator {

  @Autowired
  ApplicationContext context;

  @Scheduled(cron = "0/5 * *  * * ? ")   //每5秒执行一次
  public void start() {
    AThing thing = context.getBean(AThing.class);
    new Thread(thing).start();
  }
}
