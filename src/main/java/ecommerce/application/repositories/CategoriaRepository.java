package ecommerce.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ecommerce.application.domain.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Integer>{

}
