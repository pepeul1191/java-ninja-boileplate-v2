package controllers;

import ninja.Result;
import ninja.Results;
import ninja.FilterWith;
import ninja.Context;
import com.google.inject.Singleton;
import controllers.ApplicationController;
import helpers.UbicacionesHelper;
import filters.SessionViewTrueFilter;

@Singleton
public class UbicacionesController extends ApplicationController {
  @FilterWith(SessionViewTrueFilter.class)
  public Result index() {
    Result result = Results.html().template("/views/ubicaciones/index.ftl.html");
    result.render("title", "Bienvenido");
    result.render("constants", this.constants);
    result.render("mensaje", false);
    result.render("modulos", "[{\"url\":\"\",\"nombre\":\"Ubicaciones\"}]");
    result.render("items", "[{\"subtitulo\":\"\",\"items\":[{\"item\":\"Ubicaciones del Perú\",\"url\":\"ubicaciones/#/\"},{\"item\":\"Autocompletar\",\"url\":\"ubicaciones/#/autocompletar\"}]}]");
    result.render("data", "{\"modulo\":\"Accesos\"}");
    result.render("load_css", UbicacionesHelper.loadCSS(UbicacionesHelper.indexCSS()));
    result.render("load_js", UbicacionesHelper.loadJS(UbicacionesHelper.indexJS()));
    return result;
  }
}
