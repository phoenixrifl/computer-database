package convert;

import java.sql.Timestamp;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ConvertDate implements AttributeConverter<LocalDate, Timestamp> {

	@Override
	public Timestamp convertToDatabaseColumn(LocalDate locDate) {
		return locDate == null ? null : Timestamp.valueOf(locDate.atStartOfDay());

	}

	@Override
	public LocalDate convertToEntityAttribute(Timestamp sqlDate) {
		return sqlDate == null ? null : sqlDate.toLocalDateTime().toLocalDate();
	}
}
