package dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import exception.DateFormatException;
import modele.Company;
import modele.Computer;

@Component
public class Mappeur {

	private static Logger logger = LoggerFactory.getLogger(Mappeur.class);

	public Mappeur() {

	}

	public ComputerDTO ModelToDTO(Computer computer) {
		ComputerDTO computerDTO = new ComputerDTO();
		if (computer != null) {
			computerDTO.setId(String.valueOf(computer.getId_()));
			computerDTO.setName(computer.getName());
			if (String.valueOf(computer.getIntroduced()).equals("null"))
				computerDTO.setIntroduced("");
			else
				computerDTO.setIntroduced(String.valueOf(computer.getIntroduced()));
			if (String.valueOf(computer.getDiscontinued()).equals("null"))
				computerDTO.setDiscontinued("");
			else
				computerDTO.setDiscontinued(String.valueOf(computer.getDiscontinued()));
			if (computer.getCompany() != null) {
				computerDTO.setCompany_id(String.valueOf(computer.getCompany().getId_()));
				if (computer.getCompany().getName() != null)
					computerDTO.setCompany_name(computer.getCompany().getName());
			}
		}
		return computerDTO;
	}

	public Computer DTOToModel(ComputerDTO computerDto) {
		LocalDate convIntro = null;
		LocalDate convDisco = null;
		Computer computer = null;
		try {

			if (!computerDto.getIntroduced().equals("null")) {
				convIntro = LocalDate.parse(computerDto.getIntroduced());
			}

			if (!computerDto.getDiscontinued().equals("null")) {
				convDisco = LocalDate.parse(computerDto.getDiscontinued());
			}
			computer = new Computer(Long.parseLong(computerDto.getId()), computerDto.getName(), convIntro, convDisco,
					Long.parseLong(computerDto.getCompany_id()), computerDto.getCompany_name());

		} catch (Exception e) {
			logger.error("erreur format date", new DateFormatException());
			throw e;
		}

		return computer;
	}

	public List<Computer> DTOToModel(List<ComputerDTO> computerDto) {
		List<Computer> tmp = new ArrayList<Computer>();
		for (ComputerDTO c : computerDto) {
			tmp.add(DTOToModel(c));
		}
		return tmp;
	}

	public List<ComputerDTO> ModelToDTO(List<Computer> computer) {
		List<ComputerDTO> tmp = new ArrayList<ComputerDTO>();
		for (Computer c : computer) {
			tmp.add(ModelToDTO(c));
		}
		return tmp;
	}

	public CompanyDTO ModelToDTOCompany(Company company) {
		return new CompanyDTO(String.valueOf(company.getId_()), company.getName());
	}

	public Company DTOTOModelCompany(CompanyDTO companyDto) {
		return new Company(Long.parseLong(companyDto.getId()), companyDto.getName());
	}

	public List<Company> DTOToModel_(List<CompanyDTO> companyDto) {
		List<Company> tmp = new ArrayList<Company>();
		for (CompanyDTO c : companyDto) {
			tmp.add(DTOTOModelCompany(c));
		}
		return tmp;
	}

	public List<CompanyDTO> ModelToDTO_(List<Company> company) {
		List<CompanyDTO> tmp = new ArrayList<CompanyDTO>();
		for (Company c : company) {
			tmp.add(ModelToDTOCompany(c));
		}
		return tmp;
	}
}
