import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 2017/3/15 zy17
 */
public class ClassLoaderTest {
  @Test
  public void test() {
    ClassLoader loader = ClassLoaderTest.class.getClassLoader();
    while (loader != null) {
      System.out.println(loader.toString());
      loader = loader.getParent();
    }
  }

  public static void main(String[] args) throws IOException, ClassNotFoundException {
    List<Class<?>> classList = new ArrayList<Class<?>>();

    URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();

    for (URL url : urls) {
      try {
        if (url != null) {
          String protocol = url.getProtocol();
          String pkgPath = url.getPath();
          System.out.println("protocol:" + protocol + " path:" + pkgPath);

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
              if (filterPackage(name, "java.lang") && filterInnerClass(name)) {
                System.out.println(name);
                classList.add(Class.forName(name));
              }
            }
          }
        }
      } catch (Exception e) {
        System.out.println(e);
      }
    }

  }


  private static boolean filterPackage(String name, String... packageInclude) {
    for (String packageName : packageInclude) {
      if (name.contains(packageName)) {
        return true;
      }
    }
    return false;
  }

  private static boolean filterInnerClass(String name) {
    if (name.contains("$")) {
      return false;
    }
    return true;
  }
}
