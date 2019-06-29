package ecommerce.application;


import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ecommerce.application.domain.Categoria;
import ecommerce.application.domain.Cliente;
import ecommerce.application.domain.Endereco;
import ecommerce.application.domain.ItemPedido;
import ecommerce.application.domain.Pagamento;
import ecommerce.application.domain.Pedido;
import ecommerce.application.domain.Produto;
import ecommerce.application.enums.Perfil;
import ecommerce.application.enums.TipoPagamento;
import ecommerce.application.repositories.CategoriaRepository;
import ecommerce.application.repositories.ClienteRepository;
import ecommerce.application.repositories.EnderecoRepository;
import ecommerce.application.repositories.ItemPedidoRepository;
import ecommerce.application.repositories.PagamentoRepository;
import ecommerce.application.repositories.PedidoRepository;
import ecommerce.application.repositories.ProdutoRepository;
import ecommerce.application.services.EmailService;
import ecommerce.application.services.SmtpEmailService;

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
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		

		
		//Populando com categorias
		Categoria cat1 = new Categoria("Microcontroladores");
		Categoria cat2 = new Categoria("Diodos");
		Categoria cat3 = new Categoria("Amplificadores");
		Categoria cat4 = new Categoria("Resistores");
		Categoria cat5 = new Categoria("Embarcados");
		Categoria cat6 = new Categoria("Capacitores");
		Categoria cat7 = new Categoria("Indutores");
		
		//Populando com produtos
		Produto p1 = new Produto("PIC16F677-I/P",15.20,"O Microcontrolador PIC16F677-I/P faz parte da popular família de microcontroladores de 8 bits (série PIC16F...) lançada pela MICROCHIP");
		Produto p2 = new Produto("ATmega328P",20.99,"O microcontrolador ATmega328P faz parte da popular família de microcontroladores de 8 bits CMOS baseado na arquitetura AVR lançada pela ATMEL, é utilizado nas placas de ARDUINO UNO. ");
		Produto p3 = new Produto("ATtiny85",22.99,"O microcontrolador Atmel 8-bits AVR de baixa potência e alto desempenho combina memória flash ISP de 8KB ISP flash, 512B de EEPROM, 512 bytes de RAM, 6 linhas de I / O para uso geral, 32 registradores de uso geral,.");
		Produto p4 = new Produto("Diodo Zener BZX84C [3V6 / 250mW]",0.20,"O diodo zener BZX84C é indicado para aplicação em circuitos de estabilização e divisão de tensão.");
		Produto p5 = new Produto("Diodo 1N4001",0.20,"O diodo retificador 1N4001 da família 1N40XX é o que suporta a menor tensão reversa, sendo o mais sensível dentre todos os retificadores desta série.");
		Produto p6 = new Produto("INA821 High-Precision Instrumentation Amplifier",30.99,"The INA821 is a high-precision instrumentation amplifier that offers low power consumption and operates over a wide single-supply or dual-supply range.=.");
		Produto p7 = new Produto(" OPA1671 Audio Operational Amplifier",20.99,"The OPA1671 is a wide-bandwidth, low-noise, low-distortion, audio operational amplifier	that provides rail-to-rail input and output operation.");
		Produto p8 = new Produto("Resistor 39R 5% (2W)",0.20);
		Produto p9= new Produto("Resistor 11K 5% (1/4W)",0.07);
		Produto p10=new Produto("Resistor 10K 5% (1/2W)",0.14);
		Produto p11= new Produto("Resistor 1M2 5% (1/2W)",0.14);
		Produto p12=new Produto("Raspberry Pi 3 Model B",210.99,"O novo Raspberry Pi 3 Model B tem muito mais poder de processamento, graças ao processador Broadcom Quad Core BCM2837 de 64 bits e clock de 1.2GHz.");
		
		//Flow
		cat1.addProduto(p1);p1.setCategoria(cat1);
		cat1.addProduto(p2);p2.setCategoria(cat1);
		cat1.addProduto(p3);p3.setCategoria(cat1);
		cat2.addProduto(p4);p4.setCategoria(cat2);
		cat2.addProduto(p5);p5.setCategoria(cat2);
		cat3.addProduto(p6);p6.setCategoria(cat3);
		cat3.addProduto(p7);p7.setCategoria(cat3);
		cat4.addProduto(p8);p8.setCategoria(cat4);
		cat4.addProduto(p9);p9.setCategoria(cat4);
		cat4.addProduto(p10);p10.setCategoria(cat4);
		cat4.addProduto(p11);p11.setCategoria(cat4);
		cat5.addProduto(p12);p12.setCategoria(cat5);

		
		Cliente c1 = new Cliente("Lucas Machado","lucasvufma@gmail.com","60908095351",encoder.encode("123"));;
		Cliente c2 = new Cliente("Lucas V. P. MAchado","lucasvufmaadmin@gmail.com","60908095350",encoder.encode("123"));;
		c2.addPerfil(Perfil.ADMIN);
		Endereco e1 = new Endereco("Apartamento 201","6","Alguma Coisa Logradouro","Calhau","65073143", "São Luís","Ma",c1);
		Endereco e2 = new Endereco("complemento","66","Logradouro","Calhau","65073143", "São Luís","Ma",c2);
		c1.setEndereco(e1);
		c2.setEndereco(e2);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(c1,sdf.parse("30/09/2017 10:32"));
		
		Pagamento pagto1 = new Pagamento(TipoPagamento.Boleto,0,ped1);
		
		ItemPedido item1 = new ItemPedido(1,0.0, ped1, p1);
		
		ped1.getItempedido().addAll(Arrays.asList(item1));
	
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12));
		clienteRepository.saveAll(Arrays.asList(c1,c2));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		pedidoRepository.saveAll(Arrays.asList(ped1));
		pagamentoRepository.saveAll(Arrays.asList(pagto1));
		itempedidoRepository.saveAll(Arrays.asList(item1));

		
	}

}
