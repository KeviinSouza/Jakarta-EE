package br.com.jakarta.job;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

import br.com.jakarta.entidade.AgendamentoEmail;
import br.com.jakarta.servico.AgendamentoEmailServico;

@Singleton
public class AgendamentoEmailJob {
	
	@Inject
	private AgendamentoEmailServico emailServico;
	
	@Inject
	@JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
	private JMSContext context;
	
	@Resource(mappedName = "java:/jms/queue/EmailQueue")
	private Queue queue;
	
	@Schedule(hour = "*", minute = "*", second = "*/10")
	public void enviarEmail() {
		List<AgendamentoEmail> listarPorNaoAgendado 
			= emailServico.listarNaoAgendado();
		listarPorNaoAgendado.forEach(emailNaoAgendado -> {
			context.createProducer().send(queue, emailNaoAgendado);
			emailServico.alterar(emailNaoAgendado);
		});
	}

}
