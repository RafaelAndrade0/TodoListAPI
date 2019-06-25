package br.com.todolistapi.model.serializer;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.format.datetime.standard.DateTimeFormatterFactory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class LocalDateTimeSerializer extends StdSerializer<LocalDateTime>{
	private static final long serialVersionUID = 1L;

	public LocalDateTimeSerializer() {
		this(null);
	}
	
	protected LocalDateTimeSerializer(Class<LocalDateTime> clazz) {
		super(clazz);
	}

	@Override
	public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		String dt = value.format(new DateTimeFormatterFactory("dd/MM/yyyy HH:mm").createDateTimeFormatter());
		gen.writeString(dt);
	}

}
