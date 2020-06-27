package br.com.vini.projetointegrador.DTO;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class CredenciaisDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private String email;
	private String senha;
	public CredenciaisDTO() {
		
	}
	@NotEmpty(message = "preenchimento obrigatorio")
	@Length(min= 5, max=120, message="O Tamanho deve ser entre 5 a 120 caracteres")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	

}
