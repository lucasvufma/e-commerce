package ecommerce.application.services;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import ecommerce.application.domain.ItemPedido;
import ecommerce.application.domain.Pedido;
import ecommerce.application.domain.Produto;
import ecommerce.application.enums.TipoPagamento;
import ecommerce.application.repositories.ItemPedidoRepository;
import ecommerce.application.repositories.PagamentoRepository;
import ecommerce.application.repositories.PedidoRepository;
import ecommerce.application.repositories.ProdutoRepository;
@Service
public class PedidoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	public List<Pedido> findAll(){
		return repository.findAll();
	}
	public Optional<Pedido> find(Integer id) {
		return repository.findById(id);
	}
	public Pedido post(Pedido object) {
		object.setCod_pedido(null);
		object.setDate_pedido(new Date());
		object.getPagamento().setStatus(1); //1 Pendente
		object.getPagamento().setPedido(object);
		if(object.getPagamento().getTipo_pagamento()==TipoPagamento.Boleto) {
			//
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
			Optional<Produto> produto = produtoRepository.findById(ip.getProduto().getCod_prod());
			if (produto.isPresent()) {
				ip.setPreco(produto.get().getPreço());
				ip.setPedido(object);
				ip.setProduto(produto.get());
			}
		}
		itemPedidoRepository.saveAll(object.getItempedido());
		return object;
	}
	
	
	public void deleteById(Integer id) {
		find(id);
		repository.deleteById(id);
	}
	public  Page<Pedido> findPage(Integer page, Integer linesPerPage,String direction, String orderBy){
		return repository.findAll(PageRequest.of(page,linesPerPage, Direction.valueOf(direction),orderBy));
	}

}
