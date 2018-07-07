package controllers.ubicaciones;

import ninja.Result;
import ninja.Results;
import ninja.FilterWith;
import com.google.inject.Singleton;
import controllers.ApplicationController;
import helpers.ubicaciones.UbicacionHelper;
import filters.SessionTrueFilter;

@Singleton
public class UbicacionController extends ApplicationController {
  @FilterWith(SessionTrueFilter.class)
  public Result index() {
    Result result = Results.html().template("/views/ubicaciones/index.ftl.html");
    result.render("title", "Bienvenido");
    result.render("constants", this.constants);
    result.render("mensaje", false);
    result.render("modulos", "[{\"url\":\"\",\"nombre\":\"Ubicaciones\"}]");
    result.render("items", "[{\"subtitulo\":\"\",\"items\":[{\"item\":\"Ubicaciones del Perú\",\"url\":\"ubicaciones/#/\"},{\"item\":\"Autocompletar\",\"url\":\"ubicaciones/#/autocompletar\"}]}]");
    result.render("data", "{\"modulo\":\"Accesos\"}");
    result.render("load_css", UbicacionHelper.loadCSS(UbicacionHelper.indexCSS()));
    result.render("load_js", UbicacionHelper.loadJS(UbicacionHelper.indexJS()));
    return result;
  }  
}
