package validator;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import modele.Computer;

@Component
public class DateValidator {

	private static Logger logger = LoggerFactory.getLogger(DateValidator.class);

	public DateValidator() {

	}

	public boolean dateIsValid(Computer computer) {
		LocalDate introduced = computer.getIntroduced(), discontinued = computer.getDiscontinued();
		boolean isValid = false;
		if(introduced == null && discontinued != null) {
			isValid = false;
		}
		else {
			 if(introduced.isAfter(discontinued)) {
				 logger.error("date de début trop grand par rapport à date de fin");
				 isValid = false;
			 }
			 else 
				 isValid = true;
		}
		return isValid;
	}

}
