package br.com.vini.projetointegrador.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.vini.projetointegrador.dominio.Categoria;
import br.com.vini.projetointegrador.dominio.Cidade;
import br.com.vini.projetointegrador.dominio.Cliente;
import br.com.vini.projetointegrador.dominio.Endereco;
import br.com.vini.projetointegrador.dominio.Estado;
import br.com.vini.projetointegrador.dominio.ItemPedido;
import br.com.vini.projetointegrador.dominio.Pagamento;
import br.com.vini.projetointegrador.dominio.PagamentoComBoleto;
import br.com.vini.projetointegrador.dominio.PagamentoComCartao;
import br.com.vini.projetointegrador.dominio.Pedido;
import br.com.vini.projetointegrador.dominio.Produto;
import br.com.vini.projetointegrador.dominio.enums.EstadoPagamento;
import br.com.vini.projetointegrador.dominio.enums.Perfil;
import br.com.vini.projetointegrador.dominio.enums.TipoCliente;
import br.com.vini.projetointegrador.repository.CategoriaRepository;
import br.com.vini.projetointegrador.repository.CidadeRepository;
import br.com.vini.projetointegrador.repository.ClienteRepository;
import br.com.vini.projetointegrador.repository.EnderecoRepository;
import br.com.vini.projetointegrador.repository.EstadoRepository;
import br.com.vini.projetointegrador.repository.ItemPedidoRepository;
import br.com.vini.projetointegrador.repository.PagamentoRepository;
import br.com.vini.projetointegrador.repository.PedidoRepository;
import br.com.vini.projetointegrador.repository.ProdutoRepository;

@Service
public class DBService {
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;

	public void instantiateTestDatabase() throws ParseException {

	
	
		Categoria cat1 = new Categoria(null, "computador");
		Categoria cat2 = new  Categoria(null,"conzinha");
		Categoria cat3 = new  Categoria(null,"eletronico");
		Categoria cat4 = new  Categoria(null,"cama");
		Categoria cat5 = new  Categoria(null,"escritorio");
		Categoria cat6 = new  Categoria(null,"livros");
		Categoria cat7 = new  Categoria(null,"roupas");
		Categoria cat8 = new  Categoria(null,"teste");
		
		
		
		Produto p1 = new Produto(null, "computador", 200.00);
		Produto p2 = new Produto(null,"sofa",22.222);
		Produto p3 = new Produto(null,"mouse",83.2);
		Produto p4 = new Produto(null,"mesa de escritorio",200.00);
		Produto p5 = new Produto(null, "toalha",50.00);
		Produto p6 = new Produto(null,"concha",200.00);
		Produto p7 = new Produto(null,"televisao",1500.00);
		Produto p8 = new Produto(null,"roçadeira",800.00);
		Produto p9 = new Produto(null,"abajour",100.00);
		Produto p10 = new Produto(null,"pendente",180.00);
		Produto p11 = new Produto(null,"shampoo",90.00);
		
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2,p4));
		cat3.getProdutos().addAll(Arrays.asList(p5,p6));
		cat4.getProdutos().addAll(Arrays.asList(p1,p2,p3,p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9,p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));
		
		
		
		p1.getCategorias().addAll(Arrays.asList(cat1,cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2,cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1,cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat4));
		p7.getCategorias().addAll(Arrays.asList(cat5));
		p8.getCategorias().addAll(Arrays.asList(cat6));
		p3.getCategorias().addAll(Arrays.asList(cat6));
		p3.getCategorias().addAll(Arrays.asList(cat7));
		p3.getCategorias().addAll(Arrays.asList(cat7));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7,cat8));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11));
		
		Estado est1 = new Estado(null, "minas gerais");
		Estado est2 = new Estado(null, "sao paulo");
		
		Cidade c1 = new Cidade(null, "uberlandia", est1);
		Cidade c2 = new Cidade(null, "sao paulo", est2);
		Cidade c3 = new Cidade(null,"campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c2,c3));
		est2.getCidades().addAll(Arrays.asList(c1));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null,"maria silva","viniciusamalia@gmail.com","35173114",TipoCliente.PESSOAFISICA,pe.encode("123"));
		cli1.getTelefones().addAll(Arrays.asList("35189405","35361114"));
		
	
		
		Cliente cli2 = new Cliente(null,"ana","llucaseluan69@gmail.com","04680247193",TipoCliente.PESSOAFISICA,pe.encode("123"));
		cli2.getTelefones().addAll(Arrays.asList("981069013","35361114"));
		cli2.addPerfil(Perfil.ADMIN);
		
		
		Endereco e1 = new Endereco(null,"Rua Flores","300","apto 303","Jardim","7459036",cli1,c1);
		Endereco e2 = new Endereco(null,"av matos","35","apto 10","balneario","14597252",cli1,c2);
		
		Endereco e3 = new Endereco(null,"av sao jose","300","apto 303","Jardim","7459036",cli2,c2);
		
	
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		cli2.getEnderecos().addAll(Arrays.asList(e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1,cli2));
		enderecoRepository.saveAll(Arrays.asList(e1,e2,e3));
		
		SimpleDateFormat sdf = new  SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null,sdf.parse("01/05/2020 22:27"),cli1,e1);
		Pedido ped2 = new Pedido(null,sdf.parse("02/06/2019 22:27"),cli1,e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null,EstadoPagamento.QUITADO,ped1,5);
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoComBoleto(null,EstadoPagamento.PENDENTE,ped2,sdf.parse("18/05/2020 22:27"),null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1,p1, 0.00, 1, 2000.0);
		ItemPedido ip2 = new ItemPedido(ped1,p3,0.00,2,80.00);
		ItemPedido ip3 = new ItemPedido(ped2,p2,100.00,1,800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip3));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
	}
}
