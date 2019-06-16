package ecommerce.application.services;

import org.springframework.security.core.context.SecurityContextHolder;

import ecommerce.application.security.UserSecurity;

public class UserService {
	
	
	public static UserSecurity authenticated() {
		try {
			return (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch(Exception e) {
			return null;
		}
	}
}
