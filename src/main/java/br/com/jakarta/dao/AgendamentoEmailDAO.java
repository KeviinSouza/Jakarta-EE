package br.com.jakarta.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import br.com.jakarta.entidade.AgendamentoEmail;

@Stateless
public class AgendamentoEmailDAO {

	@PersistenceContext
	private EntityManager entity;

	public List<AgendamentoEmail> listar() {
		return entity.createQuery("SELECT ae FROM AgendamentoEmail ae", AgendamentoEmail.class).getResultList();
	}

	public void inserir(AgendamentoEmail agendamentoEmail) {
		entity.persist(agendamentoEmail);
	}

	public List<AgendamentoEmail> listarNaoAgendados() {
		return entity.createQuery("SELECT ae FROM AgendamentoEmail ae WHERE ", AgendamentoEmail.class).getResultList();
	}

}
