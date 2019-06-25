package br.com.todolistapi.service.exception;

public class DataFimMenorQueDataInicioException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataFimMenorQueDataInicioException(String msg) {
		super(msg);
	}
	
}
