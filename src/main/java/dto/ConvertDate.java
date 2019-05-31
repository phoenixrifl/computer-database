package dto;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ConvertDate implements AttributeConverter<LocalDate, Timestamp> {

	@Override
	public Timestamp convertToDatabaseColumn(LocalDate locDate) {
		return locDate == null ? null : Timestamp.valueOf(LocalDateTime.parse(locDate.toString()));
	}

	@Override
	public LocalDate convertToEntityAttribute(Timestamp sqlDate) {
		return sqlDate == null ? null : sqlDate.toLocalDateTime().toLocalDate();
	}
}
