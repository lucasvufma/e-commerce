package ecommerce.application.domain;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
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

	
	//cartao tipo_pagamento == TipoPagamento.Cartao
	private Integer numeroDeParcelas;
	
	//boleto  tipo_pagamento = TipoPagamento.Boleto
	@JsonFormat(pattern="dd/MM/yyyy",timezone = "GMT-03:00")
	private Date dataVencimento;
	
	
	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}
	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public Date getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	@JsonFormat(pattern="dd/MM/yyyy",timezone = "GMT-03:00")
	private Date dataPagamento;
	

	public TipoPagamento getTipo_pagamento() {
		return tipo_pagamento;
	}
	public Pagamento(TipoPagamento tipo_pagamento, int status, Pedido pedido) {
		super();
		this.tipo_pagamento = tipo_pagamento;
		this.status = status;
		this.pedido=pedido;
		this.updateDatavencimento();
	}
	public Pagamento() {
	}

	public Pedido getPedido() {
		return pedido;
	}
	public void updateDatavencimento() {
		if(tipo_pagamento==TipoPagamento.Boleto) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DAY_OF_MONTH,7);
			this.setDataVencimento(cal.getTime());
		}
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
		if (status==1 && this.tipo_pagamento==TipoPagamento.Boleto) {  //Status 1 = Pago, veja que tou amarrando a data de pagamento, isto é, se tiver sido pago o status altera a data de pagamento poderia fazer o contrario
			this.setDataPagamento(new Date());
		}
		if(status==0 && this.tipo_pagamento==TipoPagamento.Boleto){
			this.updateDatavencimento();
		}
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
