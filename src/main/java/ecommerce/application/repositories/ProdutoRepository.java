package ecommerce.application.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ecommerce.application.domain.Categoria;
import ecommerce.application.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Integer>{
	
	List<Produto> findByNome (String nome);
}