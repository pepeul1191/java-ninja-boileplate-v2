package helpers.ubicaciones;

import conf.ApplicationHelper;

public class UbicacionHelper extends ApplicationHelper{
  public static String[] indexCSS() {
    switch(constants.getString("ambiente_static")) {
      case "desarrollo":
        return new String[] {
          "bower_components/bootstrap/dist/css/bootstrap.min",
				  "bower_components/font-awesome/css/font-awesome.min",
          "bower_components/swp-backbone/assets/css/constants",
          "bower_components/swp-backbone/assets/css/dashboard",
          "bower_components/swp-backbone/assets/css/table",
          "assets/css/constants",
          "assets/css/styles",
        };
      case "produccion":
        return new String[] {
          "dist/home.min"
        };
      default:
        return new String[] {};
    }
  }  
  
  public static String[] indexJS() {
    switch(constants.getString("ambiente_static")) {
      case "desarrollo":
        return new String[] {
          "bower_components/jquery/dist/jquery.min",
          "bower_components/bootstrap/dist/js/bootstrap.min",
          "bower_components/underscore/underscore-min",
          "bower_components/backbone/backbone-min",
          "bower_components/handlebars/handlebars.min",
          "bower_components/swp-backbone/layouts/application",
          "bower_components/swp-backbone/views/table",
          "bower_components/swp-backbone/views/modal",
          "models/ubicaciones/departamento",
          "models/ubicaciones/provincia",
          "collections/ubicaciones/departamento_collection",
          "collections/ubicaciones/provincia_collection",
          "data/ubicaciones/tabla_distrito",
          "data/ubicaciones/tabla_provincia",
          "data/ubicaciones/tabla_departamento",
          "views/ubicaciones/tabla_provincia_view",
          "views/ubicaciones/tabla_departamento_view",
          "views/ubicaciones/ubicacion_view",
          "routes/ubicaciones",
        };
      case "produccion":
        return new String[] {
          "dist/home.min"
        };
      default:
        return new String[] {};
    }
  }  
}
