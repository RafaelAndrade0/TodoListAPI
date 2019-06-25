package br.com.todolistapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.todolistapi.model.Tarefa;
import br.com.todolistapi.respository.TarefaRepository;
import br.com.todolistapi.service.exception.DataFimMenorQueDataInicioException;

@Service
public class TarefaService {

	@Autowired
	private TarefaRepository tarefaRepository;
	
	@Transactional
	public void salvar(Tarefa tarefa) {
		if(tarefa.getDataHoraFim().compareTo(tarefa.getDataHoraInicio()) < 0) {
			throw new DataFimMenorQueDataInicioException("Data/Hora de fim não pode ser menor que Data/Hora de início");
		}
		
		this.tarefaRepository.save(tarefa);
	}
	
	@Transactional
	public void deletar(Tarefa tarefa) {
		this.tarefaRepository.deleteById(tarefa.getId());
	}
	
}
