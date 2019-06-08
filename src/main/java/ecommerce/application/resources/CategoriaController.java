package ecommerce.application.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ecommerce.application.domain.Categoria;
import ecommerce.application.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	@Autowired
	private CategoriaService service;
	
	@GetMapping
	public List<Categoria> getAll(){
		return service.findAll();
	}
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> get(@PathVariable Integer id){
		Optional<Categoria> categoria = service.find(id);
		if (categoria.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(categoria.get());	
	}
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Categoria post(@RequestBody Categoria categoria) {
		return service.post(categoria);
	}
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		try {
			service.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Existe um ou mais produtos associados a esta categoria");
		}
	}
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Categoria put(@RequestBody Categoria categoria,@PathVariable Integer id) {
		Categoria obj = categoria;
		obj.setCod_categoria(id);
		return service.put(obj);	
	}
	
	
	
	
	
	
	
	

	
	
}
