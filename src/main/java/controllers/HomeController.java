package controllers;

import ninja.Result;
import ninja.Results;
import ninja.FilterWith;
import ninja.Context;
import com.google.inject.Singleton;
import controllers.ApplicationController;
import helpers.HomeHelper;
import filters.SessionViewTrueFilter;

@Singleton
public class HomeController extends ApplicationController {
  @FilterWith(SessionViewTrueFilter.class)
  public Result index() {
    Result result = Results.html().template("/views/home/index.ftl.html");
    result.render("title", "Inicio");
    result.render("constants", this.constants);
    result.render("mensaje", false);
    result.render("modulos", HomeHelper.menuModulos());
    result.render("items", HomeHelper.itemsModulo("Accesos"));
    result.render("data", "{\"modulo\":\"Accesos\"}");
    result.render("load_css", HomeHelper.loadCSS(HomeHelper.indexCSS()));
    result.render("load_js", HomeHelper.loadJS(HomeHelper.indexJS()));
    return result;
  }
}
