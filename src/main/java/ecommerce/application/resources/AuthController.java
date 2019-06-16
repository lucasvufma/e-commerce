package ecommerce.application.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.application.dto.emailDTO;
import ecommerce.application.security.JWTUtil;
import ecommerce.application.security.UserSecurity;
import ecommerce.application.services.AuthService;
import ecommerce.application.services.UserService;

@RestController
@RequestMapping(value="/auth")
public class AuthController {
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthService authService;
	
	@PostMapping(value="/refresh_token")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void refreshToken(HttpServletResponse response) {
	UserSecurity user = UserService.authenticated();
	String token = jwtUtil.generateToken(user.getUsername());
	response.addHeader("Authorization", "Bearer " + token);
	}
	
	@PostMapping(value="/forgot")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void forgot(@Valid @RequestBody emailDTO obj) {
	authService.sendNewPassword(obj.getEmail());
	}
}
