package ecommerce.application.resources;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.application.domain.Pedido;
import ecommerce.application.domain.Produto;
import ecommerce.application.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> get(@PathVariable Integer id){
		Optional<Produto> produto = service.find(id);
		if (produto.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(produto.get());	
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Produto post(@RequestBody Produto produto) {
		return service.post(produto);
	}
	
}
