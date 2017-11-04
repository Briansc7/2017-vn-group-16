package converters;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;

public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

	@Override
	public Date convertToDatabaseColumn(LocalDate localDate) {
		if(localDate != null)
			return Date.valueOf(localDate);
		else
			return null;
	}

	@Override
	public LocalDate convertToEntityAttribute(Date date) {
		if(date != null)
			return date.toLocalDate();
		else
			return null;
	}

}
