package ecommerce.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ecommerce.application.domain.Pagamento;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento,Integer>{
	

}