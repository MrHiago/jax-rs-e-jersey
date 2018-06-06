package br.com.alura.loja;

import static org.junit.Assert.assertTrue;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProjetoTest {
	private HttpServer server;
	Client client;
	WebTarget target;

	@Before
	public void startaServidor() {
		this.server = Servidor.inicializaServidor();
		this.client = ClientBuilder.newClient();
		this.target = client.target("http://localhost:8080");
	}

	@After
	public void mataServidor() {
		server.shutdown();
	}

	@Test
	public void testaQueAConexaoComOServidorFuncionaNoPathDeProjetos() {
		String conteudo = this.target.path("/projetos/1").request().get(String.class);
		assertTrue(conteudo.contains("<nome>Minha loja"));
	}
}