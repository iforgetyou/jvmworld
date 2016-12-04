package com.zy17.world;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 世界入口
 * Created by zy17 on 2016/12/3.
 */
@SpringBootApplication
@EnableScheduling
public class WorldEntry {
  public static void main(String[] args) {
//    AThing thing = SpringUtils.getBean(AThing.class);
//    new Thread(thing).start();
    SpringApplication.run(WorldEntry.class);
  }
}
