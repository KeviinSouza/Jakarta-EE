package br.com.jakarta.controller;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import br.com.jakarta.entidade.AgendamentoEmail;
import br.com.jakarta.servico.AgendamentoEmailServico;

public class AgendamentoEmailController {
	
	@Inject
	private AgendamentoEmailServico emailServico;
	
	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response listar() {
		return Response.ok(emailServico.listar()).build();
	}
	
	@POST
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response inserir(AgendamentoEmail agendamentoEmail) {
		emailServico.inserir(agendamentoEmail);
		return Response.status(201).build();
	}
	

}
