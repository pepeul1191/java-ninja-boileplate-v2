package controllers.ubicaciones;

import ninja.Result;
import ninja.Results;
import com.google.inject.Singleton;

import controllers.ApplicationController;
import helpers.ubicaciones.UbicacionHelper;

@Singleton
public class UbicacionController extends ApplicationController {
  public Result index() {
    Result result = Results.html().template("/views/ubicaciones/index.ftl.html");
    result.render("title", "Bienvenido");
    result.render("constants", this.constants);
    result.render("mensaje", false);
    result.render("modulos", "[{\"url\":\"accesos\",\"nombre\":\"Accesos\"}]");
    result.render("items", "[{\"subtitulo\":\"Opciones\",\"items\":[{\"item\":\"Gestion de Sistemas\",\"url\":\"accesos/#/sistema\"},{\"item\":\"Gestion de Usuarios\",\"url\":\"accesos/#/usuario\"}]}]");
    result.render("data", "{\"modulo\":\"Accesos\"}");
    result.render("load_css", UbicacionHelper.loadCSS(UbicacionHelper.indexCSS()));
    result.render("load_js", UbicacionHelper.loadJS(UbicacionHelper.indexJS()));
    return result;
  }  
}
