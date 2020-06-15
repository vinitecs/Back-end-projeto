package br.com.vini.projetointegrador.services;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.vini.projetointegrador.dominio.Categoria;
import br.com.vini.projetointegrador.dominio.Cliente;
import br.com.vini.projetointegrador.dominio.ItemPedido;
import br.com.vini.projetointegrador.dominio.PagamentoComBoleto;
import br.com.vini.projetointegrador.dominio.Pedido;
import br.com.vini.projetointegrador.dominio.enums.EstadoPagamento;
import br.com.vini.projetointegrador.repository.ClienteRepository;
import br.com.vini.projetointegrador.repository.ItemPedidoRepository;
import br.com.vini.projetointegrador.repository.PagamentoRepository;
import br.com.vini.projetointegrador.repository.PedidoRepository;
import br.com.vini.projetointegrador.security.UserSS;
import br.com.vini.projetointegrador.services.exception.AuthorizationException;
import br.com.vini.projetointegrador.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	@Autowired
	PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService; 
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EmailService emailService;
	

	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			
			boletoService.PreencherPagamentoComBoleto(pagto,obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip: obj.getItens() ) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		emailService.sendOrderConfirmationHtmlEmail(obj);
		System.out.println(obj);
		
		return obj;
	}
	
	public Page<Pedido> findPage(Integer page, Integer linesPerPage,String orderBy, String direction){
		UserSS user =  UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("acesso negado");
		}
		PageRequest pageRequest = PageRequest.of(page,linesPerPage,Direction.valueOf(direction),orderBy);
		Cliente cliente = clienteService.find(user.getId());
		return repo.findByCliente(cliente, pageRequest);
	}
}

