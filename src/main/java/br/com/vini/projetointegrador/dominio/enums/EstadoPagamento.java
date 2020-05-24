package br.com.vini.projetointegrador.dominio.enums;

public enum EstadoPagamento {
	PENDENTE(1,"Pendente"),
	QUITADO(2,"Quitado"),
	CANCELADO(3,"Cancelado");
	
	private int cod;
	private String descricao;
	
	
	private EstadoPagamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	public int getCod() {
		return cod;
	}


	public String getDescricao() {
		return descricao;
	}
	// ele vai obter controle de cada codigo atribuido na numeração 
	public static EstadoPagamento toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		//ele vai percorrer todo o codigo paraa conferir se o codigo pra retornar o valor 
		for(EstadoPagamento x : EstadoPagamento.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
	//ele cria uma mensagem informando que o codigo é invalido 
	throw new IllegalArgumentException("Id inválido"+cod);
	}
	
}
