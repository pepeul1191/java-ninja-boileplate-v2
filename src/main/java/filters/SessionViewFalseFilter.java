package filters;

import ninja.Context;
import ninja.Filter;
import ninja.FilterChain;
import ninja.Result;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class SessionViewFalseFilter implements Filter {
	@Override
	public Result filter(FilterChain filterChain, Context context) {
		Result result = filterChain.next(context);
		Config constants = ConfigFactory.parseResources("conf/application.conf");
		if(constants.getString("ambiente_session").equalsIgnoreCase("activo")){
      boolean error = false;
      try {
        if(context.getSession().get("estado").equalsIgnoreCase("activo")){
          error = false;
        }
      } catch (java.lang.NullPointerException e) {
        error = true;
      }
      if(error == false){
        return result.redirect(constants.getString("base_url") + "ubicaciones/#/");
      }
		}
		return result;
	}
}
