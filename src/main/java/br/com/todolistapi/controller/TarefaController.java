package br.com.todolistapi.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.todolistapi.model.Tarefa;
import br.com.todolistapi.respository.TarefaRepository;
import br.com.todolistapi.service.TarefaService;
import br.com.todolistapi.service.exception.DataFimMenorQueDataInicioException;

@RestController
@RequestMapping("/api/v1/tarefas")
public class TarefaController {

	@Autowired
	private TarefaRepository tarefaRepository;
	
	@Autowired
	private TarefaService tarefaService;
	
	@GetMapping
	public ResponseEntity<?> getAll() {
		List<Tarefa> tarefas = this.tarefaRepository.findAll();
		
		if(tarefas.size() == 0) {
			Map<String, String> values = new HashMap<>();
			values.put("message", "Não há tarefa(s) cadastrada(s)");
			return ResponseEntity.ok().body(values);
		}
		
		return ResponseEntity
				 .ok()
				 .body(tarefas);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		Optional<Tarefa> tarefaOptional = this.tarefaRepository.findById(id);
		
		if(!tarefaOptional.isPresent()) {
			Map<String, String> values = new HashMap<>();
			values.put("message", "Nenhum resultado encontrado");
			
			return ResponseEntity.status(404).body(values);
		}
		
		return ResponseEntity
				.ok()
				.body(tarefaOptional.get());
	}
	
	@GetMapping(params = {"titulo", "dtHoraInicio", "dtHoraFim"})
	public ResponseEntity<Page<Tarefa>> getTarefasWithFilter(@RequestParam(name = "titulo") String titulo, @RequestParam(name = "dtHoraInicio") LocalDateTime dtHoraInicio, @RequestParam(name = "dtHoraFim") LocalDateTime dtHoraFim, @PageableDefault(size = 5) Pageable pageable) {
		Page<Tarefa> page = this.tarefaRepository.filtrar(titulo, dtHoraInicio, dtHoraFim, pageable);
		return ResponseEntity.status(200).body(page);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> save(@RequestBody @Valid Tarefa tarefa, BindingResult result) {
		if(result.hasErrors()) {
			Map<String, List<String>> values = new HashMap<>();
			
			List<ObjectError> errors = result.getAllErrors();
			
			List<String> mensagensErros = new ArrayList<>();
			
			errors.forEach(e -> mensagensErros.add(e.getDefaultMessage()));
			
			values.put("errors", mensagensErros);
			
			return ResponseEntity.status(404).body(values);
		}
		
		try {
			this.tarefaService.salvar(tarefa);
		} catch(DataFimMenorQueDataInicioException ex) {
			Map<String, String> values = new HashMap<>();
			values.put("errors", ex.getMessage());
			return ResponseEntity.status(404).body(values);
		}
		
		return ResponseEntity.status(201).build();
	}
	
	@PutMapping(value = {"/{id}"}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid Tarefa tarefa, BindingResult result) {
		tarefa.setId(id);
		
		if(result.hasErrors()) {
			Map<String, List<String>> values = new HashMap<>();
			
			List<ObjectError> errors = result.getAllErrors();
			
			List<String> mensagensErros = new ArrayList<>();
			
			errors.forEach(e -> mensagensErros.add(e.getDefaultMessage()));
			
			values.put("errors", mensagensErros);
			
			return ResponseEntity.status(404).body(values);
		}
		
		try {
			this.tarefaService.salvar(tarefa);
		} catch(DataFimMenorQueDataInicioException ex) {
			Map<String, String> values = new HashMap<>();
			values.put("errors", ex.getMessage());
			return ResponseEntity.status(404).body(values);
		}
		
		return ResponseEntity.status(200).build();
	}
	
	@DeleteMapping(value = {"/{id}"})
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Tarefa tarefa) {
		this.tarefaService.deletar(tarefa);
	}
	
}
