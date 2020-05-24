package br.com.vini.projetointegrador;

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
import br.com.vini.projetointegrador.dominio.Produto;
import br.com.vini.projetointegrador.dominio.enums.TipoCliente;
import br.com.vini.projetointegrador.repository.CategoriaRepository;
import br.com.vini.projetointegrador.repository.CidadeRepository;
import br.com.vini.projetointegrador.repository.ClienteRepository;
import br.com.vini.projetointegrador.repository.EnderecoRepository;
import br.com.vini.projetointegrador.repository.EstadoRepository;
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
	}

}
