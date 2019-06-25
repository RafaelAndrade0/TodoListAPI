package br.com.todolistapi.respository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.todolistapi.model.Tarefa;
import br.com.todolistapi.respository.helper.tarefa.TarefaQueries;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long>, TarefaQueries {

	
}
