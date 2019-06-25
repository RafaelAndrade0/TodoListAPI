package br.com.todolistapi.config.formatter;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.format.datetime.standard.DateTimeFormatterFactory;

public class LocalDateTimeFormatter implements Formatter<LocalDateTime>{

	@Override
	public String print(LocalDateTime dt, Locale locale) {
		return dt.format(new DateTimeFormatterFactory("dd/MM/yyyy HH:mm").createDateTimeFormatter());
	}

	@Override
	public LocalDateTime parse(String dt, Locale locale) throws ParseException {
		return LocalDateTime.parse(dt, new DateTimeFormatterFactory("dd/MM/yyyy HH:mm").createDateTimeFormatter());
	}

}
