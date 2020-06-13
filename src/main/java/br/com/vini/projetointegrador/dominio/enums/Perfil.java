package br.com.vini.projetointegrador.dominio.enums;

public enum Perfil {
	ADMIN(1,"ROLE_ADMIN"),
	CLIENTE(2,"ROLE_CLIENTE");
	
	
	private int cod;
	private String descricao;
	
	
	private Perfil(int cod, String descricao) {
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
	public static Perfil toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		//ele vai percorrer todo o codigo paraa conferir se o codigo pra retornar o valor 
		for(Perfil x : Perfil.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
	//ele cria uma mensagem informando que o codigo é invalido 
	throw new IllegalArgumentException("Id inválido"+cod);
	}
	
}
