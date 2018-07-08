package filters;

import ninja.Context;
import ninja.Filter;
import ninja.FilterChain;
import ninja.Result;
import ninja.Results;

import org.json.JSONObject;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class CsrfHeaderFilter implements Filter {
	@Override
	public Result filter(FilterChain filterChain, Context context) {
		Result result = filterChain.next(context);
    Config constants = ConfigFactory.parseResources("conf/application.conf");
    String[] mensaje = new String[2];
		if(constants.getString("ambiente_csrf").equalsIgnoreCase("activo")){
      boolean error = false;
      try {
        String csrfRequest = context.getHeader(constants.getString("csrf.key"));
        if(!csrfRequest.equalsIgnoreCase(constants.getString("csrf.secret"))){
          mensaje[0] = "No se puede acceder al recurso"; 
          mensaje[1] = "CSRF Token error no coincide";
          error = true;
        }
      } catch (java.lang.NullPointerException e) {
        mensaje[0] = "No se puede acceder al recurso"; 
        mensaje[1] = "CSRF Token error no coincide";
        error = true;
      }
      if(error == true){
        JSONObject rptaJSON = new JSONObject();
        rptaJSON.put("tipo_mensaje", "error");
        rptaJSON.put("mensaje", mensaje);
        String rpta = rptaJSON.toString();
        return Results.text().render(rpta).status(500);
      }
		}
		return result;
	}
}
