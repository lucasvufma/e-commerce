package ecommerce.application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecommerce.application.domain.Categoria;
import ecommerce.application.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	
	public List<Categoria> findAll(){
		return repository.findAll();
	}
	public Optional<Categoria> find(Integer id) {
		return repository.findById(id);
	}
	public Categoria post(Categoria object) {
		object.setCod_categoria(null);
		return repository.save(object);
	}
	public void deleteById(Integer id) {
		find(id);
		repository.deleteById(id);
	}
	public Categoria put(Categoria obj) {
		Optional<Categoria> newObj = find(obj.getCod_categoria());
		updateData(newObj.get(),obj);
		return repository.save(newObj.get());
}
	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
	}
		
}
