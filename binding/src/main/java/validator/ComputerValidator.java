package validator;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import dto.ComputerDTO;

@Component
public class ComputerValidator {

	private static Logger logger = LoggerFactory.getLogger(ComputerValidator.class);

	public ComputerValidator() {

	}


	public boolean isAComputerValid(ComputerDTO computerdto) {
		if (isAValidName(computerdto.getName()) == false) {
			logger.error("Computer name invalid : " + computerdto.getName());
			return false;
		} else {
			return true;
		}
	}

	public boolean isAValidName(String name) {
		if (name.equals(null) || name.equals(""))
			return false;
		else
			return true;
	}

	public boolean dateIsValid(ComputerDTO computerdto) {
		LocalDate introduced = LocalDate.parse(computerdto.getIntroduced()),
				discontinued = LocalDate.parse(computerdto.getDiscontinued());
		boolean isValid = false;
		if (introduced == null && discontinued != null) {
			isValid = false;
			return isValid;
		} else if ((introduced == null || introduced != null) && discontinued == null) {
			isValid = true;
			return isValid;

		}

		if (introduced.isAfter(discontinued)) {
			logger.error("date de début trop grand par rapport à date de fin");
			isValid = false;
		} else {
			isValid = true;
		}
		return isValid;
	}

}
