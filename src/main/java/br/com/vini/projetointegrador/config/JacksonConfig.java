package br.com.vini.projetointegrador.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.vini.projetointegrador.dominio.PagamentoComBoleto;
import br.com.vini.projetointegrador.dominio.PagamentoComCartao;

@Configuration
public class JacksonConfig {

	//esse pácote é exigencia do pacote jackson
	@Bean  
	public Jackson2ObjectMapperBuilder objectMapperBuilder() { 
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {   
			// https://stackoverflow.com/questions/41452598/overcome-can-not-construct-instance-ofinterfaceclass-without-hinting-the-pare
				public void configure(ObjectMapper objectMapper) { 
					objectMapper.registerSubtypes(PagamentoComCartao.class);    
					objectMapper.registerSubtypes(PagamentoComBoleto.class); 
					super.configure(objectMapper); 
};  
};   return builder;  

	} 
	}

