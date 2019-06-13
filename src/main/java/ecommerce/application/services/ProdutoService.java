package ecommerce.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import ecommerce.application.domain.Categoria;
import ecommerce.application.domain.Produto;
import ecommerce.application.repositories.CategoriaRepository;
import ecommerce.application.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	
	public List<Produto> findByNome(String nome){
		return produtoRepository.findByNome(nome); 
	}
}
