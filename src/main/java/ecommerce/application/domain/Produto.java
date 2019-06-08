package ecommerce.application.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Produto implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer cod_prod;
	private String nome;
	private double preço;
	@ManyToOne
	@JoinColumn(name= "cod_categoria")
	@JsonIgnore
	private Categoria categoria;
	
	@OneToMany(mappedBy="produto",cascade = CascadeType.ALL)
	private List<ItemPedido> itempedido=new ArrayList<>();
	
	public Produto (){
	}
	
	public Produto(String nome, double preço) {
		super();
		this.nome = nome;
		this.preço = preço;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cod_prod == null) ? 0 : cod_prod.hashCode());
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
		Produto other = (Produto) obj;
		if (cod_prod == null) {
			if (other.cod_prod != null)
				return false;
		} else if (!cod_prod.equals(other.cod_prod))
			return false;
		return true;
	}
	public Integer getCod_prod() {
		return cod_prod;
	}
	public void setCod_prod(Integer cod_prod) {
		this.cod_prod = cod_prod;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getPreço() {
		return preço;
	}
	public void setPreço(double preço) {
		this.preço = preço;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<ItemPedido> getItempedido() {
		return itempedido;
	}

	public void setItempedido(List<ItemPedido> itempedido) {
		this.itempedido = itempedido;
	}
	

}
