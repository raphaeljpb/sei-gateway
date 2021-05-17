package jus.trepe.br.sei.remote.service;

import org.junit.jupiter.api.BeforeAll;

import jus.trepe.br.sei.dto.usuario.request.UsuarioLogin;
import jus.trepe.br.sei.remote.SeiAccess;

public class SeiTest {
	
	static SeiAccess sei;
	static String SEI_URL = "http://localhost:8080/sei/modulos/wssei/controlador_ws.php/api/v2";
	static UsuarioLogin validUser;
	static UsuarioLogin invalidUser;
	static final long ID_INTERESSADO_VALIDO = 100000008L;
	static ProcessoService processoService;
	
	@BeforeAll
	public static void config() {
		validUser = new UsuarioLogin("teste", "teste");
		invalidUser = new UsuarioLogin("teste", "test");
		sei = new SeiAccess(validUser, SEI_URL);	
		processoService = new ProcessoService(sei);
	}
	
	public static void login() {
		sei.setAutenticacao(validUser);
	}
	
}
