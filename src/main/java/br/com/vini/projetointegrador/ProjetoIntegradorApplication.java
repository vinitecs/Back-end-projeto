package br.com.vini.projetointegrador;


import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

@SpringBootApplication
public class ProjetoIntegradorApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoIntegradorApplication.class, args);
	}
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
	@Override
	public void run(String... args) throws Exception {
	
		Categoria cat1 = new Categoria(null, "computador");
		Categoria cat2 = new  Categoria(null,"conzinha");
		
		Produto p1 = new Produto(null, "computador", 200.00);
		Produto p2 = new Produto(null,"sofa",22.222);
		Produto p3 = new Produto(null,"mouse",83.2);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p3));
		cat2.getProdutos().addAll(Arrays.asList(p3));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "minas gerais");
		Estado est2 = new Estado(null, "sao paulo");
		
		Cidade c1 = new Cidade(null, "uberlandia", est1);
		Cidade c2 = new Cidade(null, "sao paulo", est2);
		Cidade c3 = new Cidade(null,"campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c2,c3));
		est2.getCidades().addAll(Arrays.asList(c1));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null,"maria silva","maria@gmail.com","35173114",TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("35173115","35361114"));
		
		Endereco e1 = new Endereco(null,"Rua Flores","300","apto 303","Jardim","7459036",cli1,c1);
		Endereco e2 = new Endereco(null,"av matos","35","apto 10","balneario","14597252",cli1,c2);
	
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
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
