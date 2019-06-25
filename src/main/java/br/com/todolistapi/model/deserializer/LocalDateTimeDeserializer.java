package br.com.todolistapi.model.deserializer;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.format.datetime.standard.DateTimeFormatterFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class LocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {
	private static final long serialVersionUID = 1L;

	public LocalDateTimeDeserializer() {
		this(null);
	}
	
	protected LocalDateTimeDeserializer(Class<?> clazz) {
		super(clazz);
	}

	@Override
	public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		String dt = p.getText();
		
		return LocalDateTime.parse(dt, new DateTimeFormatterFactory("dd/MM/yyyy HH:mm").createDateTimeFormatter());
	}

}
