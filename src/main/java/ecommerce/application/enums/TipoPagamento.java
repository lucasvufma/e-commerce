package ecommerce.application.enums;

public enum TipoPagamento {
	Boleto(1,"Pagamento com Boleto"),Cartao(2,"Pagamento com Cartao");
	
	private int tipo_pagamento;
	private String descricao;
	
	TipoPagamento(int tipo_pagamento, String descricao) {
		this.tipo_pagamento=tipo_pagamento;
		this.descricao=descricao;
	}
	public int getTipo_pagamento() {
		return tipo_pagamento;
	}
	public void setTipo_pagamento(int tipo_pagamento) {
		this.tipo_pagamento = tipo_pagamento;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	

}
