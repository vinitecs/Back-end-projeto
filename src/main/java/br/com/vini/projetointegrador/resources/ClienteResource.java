package br.com.vini.projetointegrador.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.vini.projetointegrador.dominio.Cliente;
import br.com.vini.projetointegrador.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
		
		@Autowired
		ClienteService service;
		
		@RequestMapping(value="/{id}",method = RequestMethod.GET)
		public ResponseEntity<?> find(@PathVariable Integer id){
			
			Cliente obj = service.buscar(id);			
			return ResponseEntity.ok().body(obj);
			
			}
}
