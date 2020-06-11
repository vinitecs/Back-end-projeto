package br.com.vini.projetointegrador.DTO;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.vini.projetointegrador.dominio.Cliente;
import br.com.vini.projetointegrador.services.validation.ClienteUpdate;
@ClienteUpdate
public class ClienteDTO  implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "preenchimento obrigatorio")
	@Length(min= 5, max=120, message="O Tamanho deve ser entre 5 a 120 caracteres")
	private String nome;
	
	@NotEmpty(message = "preenchimento obrigatorio")
	@Email(message="email invalido")
	private String email;

	@NotEmpty(message = "preenchimento obrigatorio")
	private String senha;
	
	public ClienteDTO() {
		
	}
	
public ClienteDTO(Cliente obj) {
		id = obj.getId();
		nome = obj.getNome();
		email =obj.getEmail();
		
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
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
	
}
