package ecommerce.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ecommerce.application.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer>{
	
	Cliente findByEmail(String email);

}
