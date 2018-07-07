package helpers;

import conf.ApplicationHelper;

public class ErrorHelper extends ApplicationHelper{
  public static String[] indexCSS() {
    switch(constants.getString("ambiente_static")) {
      case "desarrollo":
        return new String[] {
          "bower_components/bootstrap/dist/css/bootstrap.min",
          "bower_components/font-awesome/css/font-awesome.min",
          "bower_components/swp-backbone/assets/css/constants",
          "assets/css/constants",
          "assets/css/error",
        };
      case "produccion":
        return new String[] {
          "dist/login.min"
        };
      default:
        return new String[] {};
    }
  }  
  
  public static String[] indexJS() {
    switch(constants.getString("ambiente_static")) {
      case "desarrollo":
        return new String[] {
        };
      case "produccion":
        return new String[] {
        };
      default:
        return new String[] {};
    }
  }  
}
