package ecommerce.application.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ecommerce.application.enums.Perfil;

public class UserSecurity implements UserDetails {
	private static final long serialVersionUID = 1L;
	private Integer cod_usuario;
	private String email;
	private String senha;
	private Collection<? extends GrantedAuthority> authorities;
	
	
	public UserSecurity() {
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}
	
	public UserSecurity(Integer cod_usuario, String email, String senha,Set<Perfil> perfis) {
		super();
		this.cod_usuario = cod_usuario;
		this.email = email;
		this.senha = senha;
		this.authorities = 
				perfis.stream().map(
						each -> new SimpleGrantedAuthority(each.getDescricao())
						).collect(Collectors.toList());
	}
	public Integer getCod_usuario() {
		return this.cod_usuario;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
