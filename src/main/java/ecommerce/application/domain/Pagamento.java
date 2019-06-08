package ecommerce.application.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import ecommerce.application.enums.TipoPagamento;

@Entity
public class Pagamento {
	private TipoPagamento tipo_pagamento;
	private boolean status;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer cod_pagamento;
	
	@OneToMany(mappedBy="pagamento",cascade = CascadeType.ALL)
	private List<ItemPedido> ItemPedido=new ArrayList<>();

	public TipoPagamento getTipo_pagamento() {
		return tipo_pagamento;
	}
	public Pagamento(TipoPagamento tipo_pagamento, boolean status,
			List<ecommerce.application.domain.ItemPedido> itemPedido) {
		super();
		this.tipo_pagamento = tipo_pagamento;
		this.status = status;
		ItemPedido = itemPedido;
	}
	public Pagamento() {
	}

	public void setTipo_pagamento(TipoPagamento tipo_pagamento) {
		this.tipo_pagamento = tipo_pagamento;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Integer getCod_pagamento() {
		return cod_pagamento;
	}

	public void setCod_pagamento(Integer cod_pagamento) {
		this.cod_pagamento = cod_pagamento;
	}

	public List<ItemPedido> getItemPedido() {
		return ItemPedido;
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
	public void setItemPedido(List<ItemPedido> itemPedido) {
		ItemPedido = itemPedido;
	}

}
