package checkpointApp.Test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import dao.PersistenciaDacException;
import dao.UsuarioDAO;
import entities.Endereco;
import entities.Usuario;

public class TestandoUsuario {

	private UsuarioDAO usuario;

	private Usuario user;

	@Before
	public void init() throws PersistenciaDacException {
		user = new Usuario();
		user.setEndereco(criarEndereco());
		user.setNome("Manuel");
		user.setCpf("12365478955");

	}

	@Test
	public void pegandoNomeDeUsuario() throws PersistenciaDacException {
		String nome = user.getNome();

		assertEquals("esperamos um resultado: ", "Manuel", nome);
	}

	public Endereco criarEndereco() {
		Endereco en = new Endereco();
		en.setBairro("Cielandia");
		en.setCep("58540000");
		en.setId(0001L);
		en.setNumero("22a");
		en.setRua("Machado de assis");

		return en;

	}
	
	
	
	

}