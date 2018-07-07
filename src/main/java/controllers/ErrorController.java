package controllers;

import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.params.PathParam;
import com.google.inject.Singleton;
import org.json.JSONObject;
import controllers.ApplicationController;
import helpers.ErrorHelper;

@Singleton
public class ErrorController extends ApplicationController {
  public Result access(@PathParam("numero") int numeroError, Context context) {
    String numero = "";
    String mensaje = "";
    String descripcion = "";
    Result result;
    switch (numeroError) {
      case 404: 
        numero = "404";
        mensaje = "Archivo no encontrado";
        descripcion = "La p&aacutegina que busca no se encuentra en el servidor";
        break;
      case 505: 
        numero = "505";
        mensaje = "Acceso restringido";
        descripcion = "Necesita estar logueado";
        break;
      default: 
        numero = "404";
        mensaje = "Archivo no encontrado";
        descripcion = "La p&aacutegina que busca no se encuentra en el servidor";
        break;
    }
    result = Results.html().template("/views/error/index.ftl.html");
    result.render("title", "Error");
    result.render("constants", this.constants);
    result.render("mensaje", mensaje);
    result.render("numero", numero);
    result.render("descripcion", descripcion);
    result.render("load_css", ErrorHelper.loadCSS(ErrorHelper.indexCSS()));
    result.render("load_js", ErrorHelper.loadJS(ErrorHelper.indexJS()));
    result.status(500);
    return result;
  }  

  public Result errorPOST() {      
    String rpta = "";
    int status = 404;
    String[] cuerpoMensaje = {"Recurso no disponible" , "Error 404"};
    JSONObject rptaMensaje = new JSONObject();
    rptaMensaje.put("tipo_mensaje", "error");
    rptaMensaje.put("mensaje", cuerpoMensaje);
    rpta = rptaMensaje.toString();
    return Results.text().render(rpta).status(status);
  }
}
