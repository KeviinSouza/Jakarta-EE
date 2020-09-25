package br.com.jakarta.job;

import java.util.List;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import br.com.jakarta.entidade.AgendamentoEmail;
import br.com.jakarta.servico.AgendamentoEmailServico;

@Singleton
public class AgendamentoEmailJob {
	
	@Inject
	private AgendamentoEmailServico emailServico;
	
	@Schedule(hour = "*", minute = "*", second = "*/10")
	public void enviarEmail() {
		List<AgendamentoEmail> listarPorNaoAgendado 
			= emailServico.listarNaoAgendado();
		listarPorNaoAgendado.forEach(emailNaoAgendado -> {
			emailServico.enviar(emailNaoAgendado);
			emailServico.alterar(emailNaoAgendado);
		});
	}

}
