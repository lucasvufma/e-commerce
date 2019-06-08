package ecommerce.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ecommerce.application.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Integer>{
	

}