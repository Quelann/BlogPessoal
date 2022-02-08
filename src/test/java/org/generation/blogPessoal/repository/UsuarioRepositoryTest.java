package org.generation.blogPessoal.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.Usuario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

/**
 * @author Wesley Barreto
 * 
 * @SpringBootTest = Transformar a classe  em uma classe de teste
 * webEnvironment = O ambiente. Ex: porta de acesso 8080. Usado para setar em qual porta vai ser utilizado no test
 * @TestInstance = Indica que o Ciclo de vida da Classe de Teste será por Classe.
 *
 * #Para conseguir testar qualquer aplicação, o DB precisa ter dados inseridos. 
 *
 * @BeforeAll = Para fazer uma ação antes de ser testado.
 * @Test = O que de fato ira ser testado. 
 * @DisplayName = Para setar uma mensagem customizada. 
 * 
 * #Todo teste é feito em cima de dados existentes 
 */


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository repository;

	@BeforeAll
	void start() {
		repository.save(new Usuario(0L,"Wesley Barreto", "Ws159", "123654"));
		repository.save(new Usuario(0L,"Larissa Barreto", "lrs0712", "123654"));
		repository.save(new Usuario(0L,"Maria Sueli Alves Barreto", "maria123", "123654"));
	}
	
	//Test by User
	@Test
	@DisplayName("Retorna apenas 1 usuario")
	void deveRetornarUmUsuario () {
		Usuario user = repository.findByUsuario("Ws159").get();
		assertTrue(user.getUsuario().equals("Ws159"));
		
	}
	
	@Test
	@DisplayName("Retorna o nome")
	void deveRetornarONome() {
		List<Usuario> listaUser = repository.findAllByNomeContainingIgnoreCase("Barreto");
		assertEquals(3, listaUser.size());
		assertTrue(listaUser.get(0).getNome().equals("Wesley Barreto"));
		assertTrue(listaUser.get(1).getNome().equals("Larissa Barreto"));
		assertTrue(listaUser.get(2).getNome().equals("Maria Sueli Alves Barreto"));
	}
	
	@Test
	@DisplayName("Retorna True para usuario não existente!")
	void pesquisaUsuarioQueNaoExiste() {
		Optional<Usuario> optional = repository.findByUsuario("flavinho123");
		assertFalse(optional.isPresent());
	}
}
