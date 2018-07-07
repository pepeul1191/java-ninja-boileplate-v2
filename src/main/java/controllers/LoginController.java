package controllers;

import ninja.Context;
import ninja.FilterWith;
import ninja.Result;
import ninja.Results;
import ninja.session.Session;
import com.google.inject.Singleton;
import java.text.DateFormat;
import java.util.Date;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import providers.accesos.UsuarioProvider;
import providers.accesos.SistemaProvider;
import controllers.ApplicationController;
import filters.SessionViewFalseFilter;
import filters.SessionViewTrueFilter;
import org.javalite.http.HttpException;
import helpers.LoginHelper;

@Singleton
public class LoginController extends ApplicationController {
  @FilterWith(SessionViewFalseFilter.class)
  public Result index() {
    Result result = Results.html().template("/views/login/index.ftl.html");
    result.render("title", "Bienvenido");
    result.render("constants", this.constants);
    result.render("mensaje", "");
    result.render("load_css", LoginHelper.loadCSS(LoginHelper.indexCSS()));
    result.render("load_js", LoginHelper.loadJS(LoginHelper.indexJS()));
    return result;
  }  

  public Result acceder(Context context, Session session) {
    String usuario = context.getParameter("usuario");
    String contrasenia = context.getParameter("contrasenia");
    String mensaje = "";
    boolean continuar = true;
    Result result;
    try {
      String validacionSistema = SistemaProvider.validarUsuario(usuario);
      if(validacionSistema.equalsIgnoreCase("1")){
        String validacionUsuario = UsuarioProvider.validarUsuario(usuario, contrasenia);
        if(!validacionUsuario.equalsIgnoreCase("1")){
          continuar = false;
          mensaje = "Usuario y/o contraseña incorrectos";
        }
      }else{
        continuar = false;
        mensaje = "Usuario no se encuentra registrado al sistema";
      }
    } catch (HttpException e) {
      System.out.println(e.getMessage());
      continuar = false;
      mensaje = "Error de comunicación con el servidor de accesos";
    }catch (Exception e) {
      continuar = false;
      mensaje = "Se ha producido un error";
    }
    if(continuar == true){
      DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); Date date = new Date();
      session.put("usuario", usuario);
      session.put("estado", "activo");
      session.put("tiempo", dateFormat.format(date));
      result = Results.redirect("/ubicaciones/#/");
    }else{
      result = Results.html().template("/views/login/index.ftl.html");
      result.render("title", "Bienvenido");
      result.render("constants", this.constants);
      result.render("mensaje", mensaje);
      result.render("load_css", LoginHelper.loadCSS(LoginHelper.indexCSS()));
      result.render("load_js", LoginHelper.loadJS(LoginHelper.indexJS()));
      result.status(500);
    }
    return result;
  }  

  @FilterWith(SessionViewTrueFilter.class)
  public Result ver(Session session){
    JSONObject rptaMensaje = new JSONObject();
    rptaMensaje.put("usuario", session.get("usuario"));
    rptaMensaje.put("tiempo", session.get("tiempo"));
    rptaMensaje.put("estado", session.get("estado"));
    String rpta = rptaMensaje.toString();
    return Results.text().render(rpta);
  }

  public Result cerrar(Session session){
    session.clear();
    return Results.redirect("/login");
  }
}
