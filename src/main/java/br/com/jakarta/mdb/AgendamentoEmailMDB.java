package br.com.jakarta.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import br.com.jakarta.entidade.AgendamentoEmail;
import br.com.jakarta.servico.AgendamentoEmailServico;


@MessageDriven(activationConfig = {
	@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/queue/EmailQueue"),
	@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class AgendamentoEmailMDB implements MessageListener{
	
	@Inject
	AgendamentoEmailServico emaiServico;
	
	@Override
	public void onMessage(Message message) {
		try {
			AgendamentoEmail agendamentoEmail = message.getBody(AgendamentoEmail.class);
			emaiServico.enviar(agendamentoEmail);
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}
	
	

}
