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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ecommerce.application.domain.Pedido;
import ecommerce.application.services.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService service;
	
	@GetMapping
	public List<Pedido> getAll(){
		return service.findAll();
	}
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> get(@PathVariable Integer id){
		Optional<Pedido> Pedido = service.find(id);
		if (Pedido.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(Pedido.get());	
	}
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pedido post(@RequestBody Pedido Pedido) {
		return service.post(Pedido);
	}
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		try {
			service.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Existe um ou mais produtos associados a esta Pedido");
		}
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/page")
	public Page<Pedido> page(@RequestParam(value="page",defaultValue="0")Integer page,
			@RequestParam(value="linesPerPage",defaultValue="24")Integer linesPerPage,
			@RequestParam(value="orderBy",defaultValue="nome")String orderBy,
			@RequestParam(value="direction",defaultValue="ASC")String direction){
		Page<Pedido> objs = service.findPage(page,linesPerPage,direction,orderBy);
		return objs;			
			}
}
