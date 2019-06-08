package ecommerce.application.domain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Categoria implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer cod_categoria;
	private String nome;
	
	
	@OneToMany(mappedBy="categoria",cascade = CascadeType.ALL)
	private List<Produto> produtos = new ArrayList<>();
	
	
	public List<Produto> getProdutos() {
		return produtos;
	}
	
	public void addProduto(Produto produto){
		produtos.add(produto);
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Categoria() {
	}

	public Categoria(String nome) {
		super();;
		this.nome = nome;
	}

	public Integer getCod_categoria() {
		return cod_categoria;
	}

	public void setCod_categoria(Integer cod_categoria) {
		this.cod_categoria = cod_categoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cod_categoria == null) ? 0 : cod_categoria.hashCode());
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
		Categoria other = (Categoria) obj;
		if (cod_categoria == null) {
			if (other.cod_categoria != null)
				return false;
		} else if (!cod_categoria.equals(other.cod_categoria))
			return false;
		return true;
	}

	public Categoria(String nome, List<Produto> produtos) {
		super();
		this.nome = nome;
		this.produtos = produtos;
	}

	
	
	

}
