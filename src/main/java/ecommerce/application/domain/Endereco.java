package ecommerce.application.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Endereco implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer cod_endereco;
	private String numero;
	private String logradouro;
	private String bairro;
	private String cep;
	private String cidade;
	private String estado;
	@OneToOne
	@JsonIgnore
	@JoinColumn(name= "cod_cliente")
	private Cliente cliente;
	
	
	public Endereco() {
		
	}
	
	public Endereco(String numero, String logradouro, String bairro, String cep, String cidade, String estado,Cliente cliente) {
		super();
		this.numero = numero;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.estado = estado;
		this.cliente = cliente;
	}

	public Integer getCod_endereco() {
		return cod_endereco;
	}
	public void setCod_endereco(Integer cod_endereco) {
		this.cod_endereco = cod_endereco;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cod_endereco == null) ? 0 : cod_endereco.hashCode());
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
		Endereco other = (Endereco) obj;
		if (cod_endereco == null) {
			if (other.cod_endereco != null)
				return false;
		} else if (!cod_endereco.equals(other.cod_endereco))
			return false;
		return true;
	}

	

}
