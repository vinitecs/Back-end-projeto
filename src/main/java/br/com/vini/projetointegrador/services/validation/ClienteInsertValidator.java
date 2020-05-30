package br.com.vini.projetointegrador.services.validation;



import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.vini.projetointegrador.DTO.ClienteNewDTO;
import br.com.vini.projetointegrador.dominio.enums.TipoCliente;
import br.com.vini.projetointegrador.resources.exception.FieldMessage;
import br.com.vini.projetointegrador.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Override
	public void initialize(ClienteInsert ann) {
		
	}

	@Override     
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) { 
	 
	        
		List<FieldMessage> list = new ArrayList<>();   
		

		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
		
			list.add(new FieldMessage("cpfOuCnpj", "CPF invalido"));
		}
		

		if(objDto.getTipo().equals(TipoCliente.PESOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
		
			list.add(new FieldMessage("cpfOuCnpj", "CPF invalido"));
		}
		// inclua os testes aqui, inserindo erros na lista 
		for (FieldMessage e : list) {       
			context.disableDefaultConstraintViolation();    
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();    
			}   
		return list.isEmpty();    
		}
	} 

