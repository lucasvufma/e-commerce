package ecommerce.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ecommerce.application.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Integer>{
	

}