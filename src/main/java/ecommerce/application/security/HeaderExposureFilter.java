package ecommerce.application.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class HeaderExposureFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	     
		HttpServletResponse resp = (HttpServletResponse) response;
		resp.addHeader("access-control-expose-headers", "location");
		chain.doFilter(request, response);
		// TODO Auto-generated method stub
		
	}

}
