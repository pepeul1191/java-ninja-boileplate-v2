package conf;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class ApplicationHelper{
  protected static Config constants = ConfigFactory.parseResources("conf/application.conf");

  public static String loadCSS(String[] csss) {
    Config constants = ConfigFactory.parseResources("conf/application.conf");
    String rpta = "";
    for (String css : csss) {
      String temp = "<link rel='stylesheet' type='text/css' href='" + constants.getString("static_url") + css + ".css'/>";
      rpta = rpta + temp;
    }
    return rpta;
  }    

  public static String loadJS(String[] jss) {
    Config constants = ConfigFactory.parseResources("conf/application.conf");
    String rpta = "";
    for (String js : jss) {
      String temp = "<script src='" + constants.getString("static_url") + js + ".js' type='text/javascript'></script>";
      rpta = rpta + temp;
    }
    return rpta;
  }  
}
