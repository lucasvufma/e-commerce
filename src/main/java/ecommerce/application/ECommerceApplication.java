package ecommerce.application;


import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ecommerce.application.domain.Categoria;
import ecommerce.application.domain.Cliente;
import ecommerce.application.domain.Endereco;
import ecommerce.application.domain.ItemPedido;
import ecommerce.application.domain.Pagamento;
import ecommerce.application.domain.Pedido;
import ecommerce.application.domain.Produto;
import ecommerce.application.enums.TipoPagamento;
import ecommerce.application.repositories.CategoriaRepository;
import ecommerce.application.repositories.ClienteRepository;
import ecommerce.application.repositories.EnderecoRepository;
import ecommerce.application.repositories.ItemPedidoRepository;
import ecommerce.application.repositories.PagamentoRepository;
import ecommerce.application.repositories.PedidoRepository;
import ecommerce.application.repositories.ProdutoRepository;

@SpringBootApplication
public class ECommerceApplication implements CommandLineRunner{
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	@Autowired
	private ItemPedidoRepository itempedidoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;
	
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
		
		Cliente c1 = new Cliente("Lucas MAchado","lucasvufma@gmail.com","60908095350");
		Endereco e1 = new Endereco("6","Logradouro","Calhau","65073143", "São Luís","Ma",c1);
		c1.setEndereco(e1);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(c1,sdf.parse("30/09/2017 10:32"));
		
		Pagamento pagto1 = new Pagamento(TipoPagamento.Boleto,1,ped1);
		
		ItemPedido item1 = new ItemPedido(1,0.0, ped1, p1);
		
		ped1.getItempedido().addAll(Arrays.asList(item1));
	
		categoriaRepository.saveAll(Arrays.asList(cat1));
		produtoRepository.saveAll(Arrays.asList(p1));
		clienteRepository.saveAll(Arrays.asList(c1));
		enderecoRepository.saveAll(Arrays.asList(e1));
		pedidoRepository.saveAll(Arrays.asList(ped1));
		pagamentoRepository.saveAll(Arrays.asList(pagto1));
		itempedidoRepository.saveAll(Arrays.asList(item1));

		
	}

}
