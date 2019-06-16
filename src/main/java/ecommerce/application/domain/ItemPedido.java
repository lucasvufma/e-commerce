package ecommerce.application.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class ItemPedido implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer cod_itempedido;
	private int quantidade;
	private double desconto;
	private double preco;
	
	@ManyToOne
	@JoinColumn(name="cod_pedido")
	@JsonIgnore
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name= "cod_produto")
	private Produto produto;
	
	
	public ItemPedido() {
	}
	
	public ItemPedido(int quantidade, double desconto, Pedido pedido, Produto produto) {
		super();
		this.quantidade = quantidade;
		this.desconto = desconto;
		this.pedido = pedido;
		this.produto = produto;
		this.preco=produto.getPreço();
	}

	public Integer getCod_itempedido() {
		return cod_itempedido;
	}

	public void setCod_itempedido(Integer cod_itempedido) {
		this.cod_itempedido = cod_itempedido;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
		this.preco=produto.getPreço();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cod_itempedido == null) ? 0 : cod_itempedido.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		if (cod_itempedido == null) {
			if (other.cod_itempedido != null)
				return false;
		} else if (!cod_itempedido.equals(other.cod_itempedido))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		return true;
	}
	
	public double getSubTotal() {
		return (preco-desconto)*quantidade;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		StringBuilder builder = new StringBuilder();
		builder.append(getProduto().getNome());
		builder.append(", Qte: ");
		builder.append(getQuantidade());
		builder.append(", Preço unitário: ");
		builder.append(nf.format(getPreco()));
		builder.append(", Subtotal: ");
		builder.append(nf.format(getSubTotal()));
		builder.append("\n");
		return builder.toString();
	}

	
	
	
}
