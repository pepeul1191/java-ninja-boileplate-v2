package conf;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.javalite.http.Http;
import org.javalite.http.HttpException;
import org.javalite.http.Get;

public class ApplicationHelper{
  protected static Config constants = ConfigFactory.parseResources("conf/application.conf");

  public static String loadCSS(String[] csss) {
    String rpta = "";
    for (String css : csss) {
      String temp = "<link rel='stylesheet' type='text/css' href='" + constants.getString("static_url") + css + ".css'/>";
      rpta = rpta + temp;
    }
    return rpta;
  }    

  public static String loadJS(String[] jss) {
    String rpta = "";
    for (String js : jss) {
      String temp = "<script src='" + constants.getString("static_url") + js + ".js' type='text/javascript'></script>";
      rpta = rpta + temp;
    }
    return rpta;
  }
  
  public static String menuModulos(){
    String rpta = "";
    try {
      Get req = Http.get(constants.getString("accesos.url") + "modulo/menu/" + constants.getString("sistema_id"))
        .header(constants.getString("accesos.csrf_key"), constants.getString("accesos.csrf_value"));
      rpta = req.text();
		} catch (HttpException e) {
			//e.printStackTrace();
      rpta = "[]";
    }
    return rpta;
  }

  public static String itemsModulo(String modulo){
    String rpta = "";
    try {
      String url = constants.getString("accesos.url") + 
        "item/menu?sistema_id=" + constants.getString("sistema_id") + 
        "&modulo=" + modulo;
      Get req = Http.get(url)
        .header(constants.getString("accesos.csrf_key"), constants.getString("accesos.csrf_value"));
      rpta = req.text();
		} catch (HttpException e) {
			//e.printStackTrace();
      rpta = "[]"; 
    }
    return rpta;
  }
}
