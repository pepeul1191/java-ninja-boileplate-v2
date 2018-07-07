package controllers.ubicaciones;

import ninja.Context;
import ninja.Result;
import ninja.Results;
import com.google.inject.Singleton;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import conf.Database;
import controllers.ApplicationController;
import models.ubicaciones.Departamento;

@Singleton
public class DepartamentoController extends ApplicationController{
  public Result index() {
    return Results.html();
  }
  
  //@FilterWith(CorsHeadersFilter.class)
  public Result listarDB() {      
    String rpta = "";
    int status = 200;
    Database db = new Database();
    try {
      List<JSONObject> rptaTemp = new ArrayList<JSONObject>();
      db.open();
      List<Departamento> rptaList = Departamento.findAll();
      for (Departamento departamento : rptaList) {
        JSONObject obj = new JSONObject();
        obj.put("id", departamento.get("id"));
        obj.put("nombre", departamento.get("nombre"));
        rptaTemp.add(obj);
      }
      rpta = rptaTemp.toString();
    }catch (Exception e) {
      String[] error = {"Se ha producido un error en  listar los departamentos", e.toString()};
      JSONObject rptaTry = new JSONObject();
      rptaTry.put("tipo_mensaje", "error");
      rptaTry.put("mensaje", error);
      rpta = rptaTry.toString();
      status = 500;
    } finally {
      db.close();
    }
    return Results.text().render(rpta).status(status);
  }

  public Result guardar(Context context) {  
    String rpta = "";
    int status = 200;
    List<JSONObject> listJSONNuevos = new ArrayList<JSONObject>();
    Database db = new Database();
    try {
      JSONObject data = new JSONObject(context.getParameter("data"));
      JSONArray nuevos = data.getJSONArray("nuevos");
      JSONArray editados = data.getJSONArray("editados");
      JSONArray eliminados = data.getJSONArray("eliminados");
      db.open();
      db.getDb().openTransaction();
      if(nuevos.length() > 0){
        for (int i = 0; i < nuevos.length(); i++) {
          JSONObject sistema = nuevos.getJSONObject(i);
          String antiguoId = sistema.getString("id");
          String nombre = sistema.getString("nombre");
          Departamento n = new Departamento();
          n.set("nombre", nombre);
          n.saveIt();
          int nuevoId = (int) n.get("id"); 
          JSONObject temp = new JSONObject();
          temp.put("temporal", antiguoId);
          temp.put("nuevo_id", nuevoId);
          listJSONNuevos.add(temp);
        }
      }
      if(editados.length() > 0){
        for (int i = 0; i < editados.length(); i++) {
          JSONObject departamento = editados.getJSONObject(i);
          int id = departamento.getInt("id");
          String nombre = departamento.getString("nombre");
          Departamento e = Departamento.findFirst("id = ?", id);
          if(e != null){
            e.set("nombre", nombre);
            e.saveIt();
          }
        }
      }
      if(eliminados.length() > 0){
        for (Object eliminado : eliminados) {
          String eleminadoId = (String)eliminado;
          Departamento d = Departamento.findFirst("id = ?", eleminadoId);
          if(d != null){
            d.delete();
          }
        }
      }
      db.getDb().commitTransaction();
      JSONArray cuerpoMensaje =  new JSONArray();
      cuerpoMensaje.put("Se ha registrado los cambios en los sistemas");
      cuerpoMensaje.put(listJSONNuevos);
      JSONObject rptaMensaje = new JSONObject();
      rptaMensaje.put("tipo_mensaje", "success");
      rptaMensaje.put("mensaje", cuerpoMensaje);
      rpta = rptaMensaje.toString();
    }catch (Exception e) {
      String[] cuerpoMensaje = {"Se ha producido un error en  guardar los sistemas", e.toString()};
      JSONObject rptaMensaje = new JSONObject();
      rptaMensaje.put("tipo_mensaje", "error");
      rptaMensaje.put("mensaje", cuerpoMensaje);
      status = 500;
      rpta = rptaMensaje.toString();
      e.printStackTrace();
    } finally {
      db.close();
    }
    return Results.text().render(rpta).status(status);
  }
  
  public static class SimplePojo {
    public String content;
  }
}
