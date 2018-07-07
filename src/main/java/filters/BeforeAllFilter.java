package filters;

import ninja.Context;
import ninja.Filter;
import ninja.FilterChain;
import ninja.Result;

public class BeforeAllFilter implements Filter {
	@Override
	public Result filter(FilterChain filterChain, Context context) {
    Result result = filterChain.next(context);
		result.addHeader("Server", "Ubuntu,Jetty(9.3.15.v20161220)");
		return result;
	}
}
