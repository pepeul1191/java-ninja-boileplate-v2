package controllers.ubicaciones;

import ninja.Result;
import ninja.Results;
import com.google.inject.Singleton;
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
  
  public static class SimplePojo {
    public String content;
  }
}
