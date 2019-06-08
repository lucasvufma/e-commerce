package ecommerce.application;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ecommerce.application.domain.Categoria;
import ecommerce.application.domain.Produto;
import ecommerce.application.repositories.CategoriaRepository;
import ecommerce.application.repositories.ProdutoRepository;

@SpringBootApplication
public class ECommerceApplication implements CommandLineRunner{
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Populando com categorias
		Categoria cat1 = new Categoria("Informatica");
		
		//Populando com produtos
		Produto p1 = new Produto("Computador",500.0);
		
		//Flow
		cat1.addProduto(p1);
		p1.setCategoria(cat1);
		
	
		categoriaRepository.saveAll(Arrays.asList(cat1));
		produtoRepository.saveAll(Arrays.asList(p1));
		


		
	}

}
