package br.com.todolistapi.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.com.todolistapi.service.TarefaService;

@Configuration
@ComponentScan(basePackageClasses = { TarefaService.class })
public class ServiceConfig {

}
