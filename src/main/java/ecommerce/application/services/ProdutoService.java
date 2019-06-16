package ecommerce.application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecommerce.application.domain.Categoria;
import ecommerce.application.domain.Produto;
import ecommerce.application.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

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

}
