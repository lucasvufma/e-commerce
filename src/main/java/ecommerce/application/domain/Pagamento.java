package ecommerce.application.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ecommerce.application.enums.TipoPagamento;

@Entity
public class Pagamento {
	private TipoPagamento tipo_pagamento;
	private int status;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer cod_pagamento;
	
	

	@JsonIgnore
	@OneToOne
	@JoinColumn(name="cod_pedido")
	private Pedido pedido;
	

	public TipoPagamento getTipo_pagamento() {
		return tipo_pagamento;
	}
	public Pagamento(TipoPagamento tipo_pagamento, int status, Pedido pedido) {
		super();
		this.tipo_pagamento = tipo_pagamento;
		this.status = status;
		this.pedido=pedido;
	}
	public Pagamento() {
	}

	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public void setTipo_pagamento(TipoPagamento tipo_pagamento) {
		this.tipo_pagamento = tipo_pagamento;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Integer getCod_pagamento() {
		return cod_pagamento;
	}

	public void setCod_pagamento(Integer cod_pagamento) {
		this.cod_pagamento = cod_pagamento;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cod_pagamento == null) ? 0 : cod_pagamento.hashCode());
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
		Pagamento other = (Pagamento) obj;
		if (cod_pagamento == null) {
			if (other.cod_pagamento != null)
				return false;
		} else if (!cod_pagamento.equals(other.cod_pagamento))
			return false;
		return true;
	}

}
