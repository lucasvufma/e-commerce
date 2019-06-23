package ecommerce.application.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.Email;


public class ClienteNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String nome;
	@Column(unique=true)
	@Email
	private String email;
	private String cpf;
	
	private String telefone1;
	private String telefone2;
	
	private String senha;
	
	private String numero;
	private String logradouro;
	private String bairro;
	private String cep;
	private String cidade;
	private String estado;
	private String complemento;
	
	public ClienteNewDTO() {
		
	}

	public String getNome() {
		return nome;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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

	public ClienteNewDTO(String complemento, String nome, @Email String email, String cpf, String telefone1, String telefone2, String senha,
			String numero, String logradouro, String bairro, String cep, String cidade, String estado) {
		super();
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.telefone1 = telefone1;
		this.telefone2 = telefone2;
		this.senha = senha;
		this.numero = numero;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.estado = estado;
		this.complemento=complemento;
	}
	
	
}
