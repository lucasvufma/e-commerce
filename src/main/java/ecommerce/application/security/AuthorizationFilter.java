package ecommerce.application.security;

import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class AuthorizationFilter extends BasicAuthenticationFilter {

	
	private JWTUtil jwtUtil;
	
}
