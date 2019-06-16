package ecommerce.application.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ecommerce.application.domain.Cliente;
import ecommerce.application.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	@Autowired
	private ClienteService service;
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	public List<Cliente> getAll(){
		return service.findAll();
	}
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> get(@PathVariable Integer id){
		Optional<Cliente> Cliente = service.find(id);
		if (Cliente.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(Cliente.get());	
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente post(@RequestBody Cliente Cliente) {
		return service.post(Cliente);
	}
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		try {
			service.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Existe um ou mais produtos associados a esta Cliente");
		}
	}
	

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Cliente put(@RequestBody Cliente Cliente,@PathVariable Integer id) {
		Cliente obj = Cliente;
		obj.setCod_cliente(id);
		return service.put(obj);	
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/page")
	public Page<Cliente> page(@RequestParam(value="page",defaultValue="0")Integer page,
			@RequestParam(value="linesPerPage",defaultValue="24")Integer linesPerPage,
			@RequestParam(value="orderBy",defaultValue="nome")String orderBy,
			@RequestParam(value="direction",defaultValue="ASC")String direction){
		Page<Cliente> objs = service.findPage(page,linesPerPage,direction,orderBy);
		return objs;			
			}
	
	
	
	
	
	
	
	
	

}
