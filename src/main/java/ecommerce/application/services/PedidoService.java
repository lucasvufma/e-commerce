package ecommerce.application.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import ecommerce.application.domain.Cliente;
import ecommerce.application.domain.ItemPedido;
import ecommerce.application.domain.Pedido;
import ecommerce.application.domain.Produto;
import ecommerce.application.enums.TipoPagamento;
import ecommerce.application.repositories.ClienteRepository;
import ecommerce.application.repositories.ItemPedidoRepository;
import ecommerce.application.repositories.PagamentoRepository;
import ecommerce.application.repositories.PedidoRepository;
import ecommerce.application.repositories.ProdutoRepository;
import ecommerce.application.security.UserSecurity;
import ecommerce.application.services.exceptions.AuthorizationException;
@Service
public class PedidoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;  
	
	@Autowired
	private EmailService emailService;
	
	public List<Pedido> findAll(){
		return repository.findAll();
	}
	public Optional<Pedido> find(Integer id) {
		return repository.findById(id);
	}
	public Pedido post(Pedido object) {
		object.setCod_pedido(null);
		object.setDate_pedido(new Date());
		object.getPagamento().setStatus(0); //0 Pendente
		object.getPagamento().setPedido(object);
		Optional<Cliente> cliente = clienteRepository.findById(object.getCliente().getCod_cliente());
		if (cliente.isPresent()) {
			object.setCliente(cliente.get());
		}
		if(object.getPagamento().getTipo_pagamento()==TipoPagamento.Boleto) {

			// object.getPagamento().setData_vencimento_boleto(Calendar -> Calendar.getInstance().setTime(new Date()).add(Calendar.DAY_OF_MONTH,7));
			// ou Calendar cal = Calengar.getInstance();
			// cal.setTime(object.getDate_pedido());
			// cal.add(Calendar.DAY_OF_MONTH,7);
			// object.getPagamento().setData_vencimento_boleto(cal.getTime());
			
		}
		object = repository.save(object);
		pagamentoRepository.save(object.getPagamento());
		for (ItemPedido ip : object.getItempedido()) {
			ip.setDesconto(0.0);
			Optional<Produto> produto = produtoRepository.findById(ip.getProduto().getCod_produto());
			if (produto.isPresent()) {
				ip.setPreco(produto.get().getPreço());
				ip.setPedido(object);
				ip.setProduto(produto.get());
			}
		}
		itemPedidoRepository.saveAll(object.getItempedido());
		emailService.sendOrderHtmlEmailConfirmation(object);
		return object;
	}
	
	
	public void deleteById(Integer id) {
		find(id);
		repository.deleteById(id);
	}
	public  Page<Pedido> findPage(Integer page, Integer linesPerPage,String direction, String orderBy){
		UserSecurity user = UserService.authenticated();
		if(user==null) {
			throw new AuthorizationException("Acesso Negado");
		}
		Optional<Cliente> cliente = clienteRepository.findById(user.getCod_usuario());
		if (cliente.isPresent()){
			return repository.findByCliente(cliente.get(),
					PageRequest.of(page,linesPerPage, Direction.valueOf(direction),orderBy));
		}
		else {
			throw new RuntimeException();
		}
	}

}
