package ecommerce.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ecommerce.application.domain.Cliente;
import ecommerce.application.repositories.ClienteRepository;
import ecommerce.application.security.UserSecurity;

@Service
public class ConcreteUserDetailService implements UserDetailsService{

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Cliente cliente = clienteRepository.findByEmail(email);
		if (cliente==null) {
			throw new UsernameNotFoundException(email);
		}
		
		return new UserSecurity(cliente.getCod_cliente(),cliente.getEmail(),cliente.getSenha(),cliente.getPerfis());
	}

}
