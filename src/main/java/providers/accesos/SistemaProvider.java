package providers.accesos;

import com.typesafe.config.Config;
import org.javalite.http.Http;
import org.javalite.http.HttpException;
import org.javalite.http.Post;
import org.json.JSONObject;
import com.typesafe.config.ConfigFactory;

public class SistemaProvider {
  private static Config constants = ConfigFactory.parseResources("conf/application.conf");

  public static String validarUsuario(String usuario) throws HttpException{
    String rpta = "";
    try {
      Post req = Http.post(constants.getString("accesos.url") + "sistema/usuario/validar")
        .param("usuario", usuario)
        .param("sistema_id", constants.getString("sistema_id"))
        .header(constants.getString("accesos.csrf_key"), constants.getString("accesos.csrf_value"));
      rpta = req.text();
		} catch (HttpException e) {
			//e.printStackTrace();
      String[] error = {"Error de conexión con el servicio de accesos", e.toString()};
      JSONObject rptaTry = new JSONObject();
      rptaTry.put("tipo_mensaje", "error");
      rptaTry.put("mensaje", error);
      rpta = rptaTry.toString();
      throw new HttpException(rpta, e);  
    }
    return rpta;
  }
}