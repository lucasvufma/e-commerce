package ecommerce.application.services;

import java.util.List;
import java.util.Optional;

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

	public List<Produto> findByNome(String nome) {
		return produtoRepository.findByNome(nome);
	}

	public Optional<Produto> find(Integer id) {
		return produtoRepository.findById(id);
	}

	public Produto post(Produto object) {
		// Falta implementar o categoria.addProduto(PRODUTO);
		return produtoRepository.save(object);
	}
	
	//Rever esse metodo de busca de produtos por categoria depois "/produtos?categorias=id"
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);	
	}
	

}
