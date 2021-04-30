package jus.trepe.br.sei.remote.service;

import org.junit.jupiter.api.BeforeAll;

import jus.trepe.br.sei.dto.Usuario;
import jus.trepe.br.sei.remote.SeiAccess;

public class SeiTest {
	
	static SeiAccess sei;
	static String SEI_URL = "http://localhost:8080/sei/modulos/wssei/controlador_ws.php/api/v2";
	static Usuario validUser;
	static Usuario invalidUser;
	
	@BeforeAll
	public static void config() {
		validUser = new Usuario("teste", "teste");
		invalidUser = new Usuario("teste", "test");
		sei = new SeiAccess(validUser, SEI_URL);		
	}
	
	public static void login() {
		sei.setUsuario(validUser);
	}
	
}
