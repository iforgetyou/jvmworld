package com.zy17.world.tools;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 2016/12/10 zy17
 */
@Slf4j
@Component
public class ReflectionUtil {
  private Random random = new Random(System.currentTimeMillis());
  private List<Class> list = new ArrayList<Class>();

  String[] packages = {"java.lang","java.math"};

  public List<Class> getAllClasses() throws Exception {
    if (list.size() == 0) {
      URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();

      for (URL url : urls) {
        try {
          if (url != null) {
            String protocol = url.getProtocol();
            String pkgPath = url.getPath();
            if ("file".equals(protocol)) {
              if (pkgPath == null) {
                continue;
              }
              JarFile jar = new JarFile(new File(url.getFile()));
              // 从此jar包 得到一个枚举类
              Enumeration<JarEntry> entries = jar.entries();
              // 同样的进行循环迭代
              while (entries.hasMoreElements()) {
                // 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
                JarEntry entry = entries.nextElement();
                String name = entry.getName().replace("/", ".").replace(".class", "");
                if (filterPackage(name, packages) && filterInnerClass(name)) {
                  Class<?> aClass = Class.forName(name);
                  int modifiers = aClass.getModifiers();
                  if (!Modifier.isAbstract(modifiers)
                      && !Modifier.isInterface(modifiers)
                      && !Modifier.isNative(modifiers)
                      ) {
                    // 非抽象类
                    list.add(aClass);
                  }
                }
              }
            }
          }
        } catch (Exception e) {
          log.warn("getAllClasses error:" + e);
        }
      }
      log.info("classes list size :{},detail:{}", list.size(), JSON.toJSONString(list));
    }
    return list;
  }

  public Class<?> getRandomClass() throws Exception {
    List<Class> allClasses = getAllClasses();
    return allClasses.get(random.nextInt(allClasses.size()));
  }

  public Method getRandomMethod() throws Exception {
    Class<?> randomClass = getRandomClass();
    Method[] methods = randomClass.getMethods();
//    Set<Method> allMethods = ReflectionUtils.getAllMethods(randomClass);
    int i = random.nextInt(methods.length);
    log.debug("index:{}", i);

    return methods[i];
  }


  private boolean filterPackage(String name, String... packageInclude) {
    for (String packageName : packageInclude) {
      if (name.contains(packageName)) {
        return true;
      }
    }
    return false;
  }

  private boolean filterInnerClass(String name) {
    if (name.contains("$")) {
      return false;
    }
    return true;
  }

}
