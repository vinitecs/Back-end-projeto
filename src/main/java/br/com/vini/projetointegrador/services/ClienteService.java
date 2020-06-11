package br.com.vini.projetointegrador.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.vini.projetointegrador.DTO.ClienteDTO;
import br.com.vini.projetointegrador.DTO.ClienteNewDTO;
import br.com.vini.projetointegrador.dominio.Cidade;
import br.com.vini.projetointegrador.dominio.Cliente;
import br.com.vini.projetointegrador.dominio.Endereco;
import br.com.vini.projetointegrador.dominio.enums.TipoCliente;
import br.com.vini.projetointegrador.repository.ClienteRepository;
import br.com.vini.projetointegrador.repository.EnderecoRepository;
import br.com.vini.projetointegrador.services.exception.DataIntegrityException;
import br.com.vini.projetointegrador.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	@Autowired
	ClienteRepository repo;

	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe; 
	

	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj =  find(obj.getId());
		updadeData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
		repo.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel por que há entidades relacionadas");
		}
	
	}
	
	public List<Cliente> findAll(){
		return repo.findAll();
	}
	public Page<Cliente> findPage(Integer page, Integer linesPerPage,String orderBy, String direction){
			PageRequest pageRequest = PageRequest.of(page,linesPerPage,Direction.valueOf(direction),orderBy);
			
			return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDto) {
		
	 return new Cliente(objDto.getId(),objDto.getNome(),objDto.getEmail(),null, null,null);
		
	}
	
	public Cliente fromDto(ClienteNewDTO  objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(),objDto.getEmail(), objDto.getCpfOuCnpj(),TipoCliente.toEnum(objDto.getTipo()),pe.encode(objDto.getSenha()));
		Cidade cid = new Cidade(objDto.getCidadeId(),null,null);
		Endereco end = new Endereco(null,objDto.getLogradouro(), objDto.getNumero(),objDto.getComplemento(),objDto.getBairro(),objDto.getCep(),cli,cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if(objDto.getTelefone2()!=null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if(objDto.getTelefone3()!=null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;
		}
	private void updadeData(Cliente newObj,Cliente  obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	
}
