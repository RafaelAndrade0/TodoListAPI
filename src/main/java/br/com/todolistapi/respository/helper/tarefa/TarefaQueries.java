package br.com.todolistapi.respository.helper.tarefa;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.todolistapi.model.Tarefa;

public interface TarefaQueries {

	public Page<Tarefa> filtrar(String titulo, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim, Pageable pageable);
	
}
