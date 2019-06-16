package ecommerce.application.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class emailDTO implements Serializable {
	@Email(message="Email inválido!")
	@NotEmpty(message="Campo obrigatório!")
	private String email;
	public emailDTO() {
		
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
