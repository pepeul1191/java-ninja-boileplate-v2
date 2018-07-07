package filters;

import ninja.Context;
import ninja.Filter;
import ninja.FilterChain;
import ninja.Result;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class LogFilter implements Filter {
	@Override
	public Result filter(FilterChain filterChain, Context context) {
    Result result = filterChain.next(context);
		Config constants = ConfigFactory.parseResources("conf/application.conf");
		if(constants.getString("ambiente_request_logs").equalsIgnoreCase("activo")){
      System.out.println("+ Log: " + context.getMethod() + " - " + context.getRequestUri());
    }
		return result;
	}
}
