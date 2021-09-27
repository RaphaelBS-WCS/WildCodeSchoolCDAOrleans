package org.wcscda.worms;

import java.io.*;
import java.util.*;

public class Config {
  private Properties prop;

  // Singleton design pattern
  private static final String CONFIG_FILENAME = "src/resources/app.config";
  private static Config config = null;

  public static void loadConfig() throws IOException {
    config = new Config();
  }

  public static boolean isDebug() {
    return config.prop.getProperty("app.debug").equals("1");
  }

  public static int getClockDelay() {
    return Integer.parseInt(config.prop.getProperty("app.clock_delay"));
  }

  public static int getMaxWormTurnDuration() {
    return Integer.parseInt(config.prop.getProperty("app.worms.turn_delay"));
  }

  public Config() throws IOException {
    prop = new Properties();

    try (FileInputStream fis = new FileInputStream(CONFIG_FILENAME)) {
      prop.load(fis);
    } catch (FileNotFoundException ex) {
      throw ex;
    } catch (IOException ex) {
      throw ex;
    }
  }
}
