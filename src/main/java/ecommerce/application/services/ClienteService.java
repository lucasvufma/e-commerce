package ecommerce.application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import ecommerce.application.domain.Cliente;
import ecommerce.application.enums.Perfil;
import ecommerce.application.repositories.ClienteRepository;
import ecommerce.application.security.UserSecurity;
import ecommerce.application.services.exceptions.AuthorizationException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	public List<Cliente> findAll(){
		return repository.findAll();
	}
	public Optional<Cliente> find(Integer id) {
		UserSecurity user = UserService.authenticated();
		if(user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getCod_usuario())) {
			throw new AuthorizationException("Acesso Negado!");
		}
		return repository.findById(id);
	}
	public Cliente post(Cliente object) {
		object.setCod_cliente(null);
		object.getEndereco().setCliente(object);
		return repository.save(object);
	}
	public void deleteById(Integer id) {
		find(id);
		repository.deleteById(id);
	}
	public Cliente put(Cliente obj) {
		Optional<Cliente> newObj = find(obj.getCod_cliente());
		updateData(newObj.get(),obj);
		return repository.save(newObj.get());
}
	private void updateData(Cliente newObj, Cliente obj) {
		if(obj.getNome()!=null) {
			newObj.setNome(obj.getNome());
		}
		if (obj.getEmail()!=null) {
			newObj.setEmail(obj.getEmail());
		}
		if (obj.getEndereco()!=null) {
			newObj.getEndereco().updateEnd(obj.getEndereco());
		}
	}

	public  Page<Cliente> findPage(Integer page, Integer linesPerPage,String direction, String orderBy){
		return repository.findAll(PageRequest.of(page,linesPerPage, Direction.valueOf(direction),orderBy));
	}
		
}