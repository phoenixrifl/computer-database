package dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import model.Company;
import model.Computer;

@Component
public class Mappeur {

	public Mappeur() {

	}

	public ComputerDTO ModelToDTO(Computer computer) {
		ComputerDTO computerDTO = new ComputerDTO();
		if (computer != null) {
			computerDTO.setId(computer.getId_());
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
				computerDTO.setCompany_id(computer.getCompany().getId_());
				if (computer.getCompany().getName() != null)
					computerDTO.setCompany_name(computer.getCompany().getName());
			}
		}
		return computerDTO;
	}

	public Computer DTOToModel(ComputerDTO computerDto) {
		LocalDate local1;
		LocalDate local2;

		local1 = (computerDto.getIntroduced() == null || computerDto.getIntroduced().equals("")
				|| computerDto.getIntroduced().isEmpty()) ? null : LocalDate.parse(computerDto.getIntroduced());
		local2 = (computerDto.getDiscontinued() == null || computerDto.getDiscontinued().equals("")
				|| computerDto.getDiscontinued().isEmpty()) ? null : LocalDate.parse(computerDto.getDiscontinued());

		return new Computer(computerDto.getId(), computerDto.getName(), local1, local2,
				(computerDto.getCompany_id() == null || computerDto.getCompany_id() == 0) ? 0 : computerDto.getCompany_id(),
				(computerDto.getCompany_name() == null || computerDto.getCompany_name().equals("")) ? null : computerDto.getCompany_name());

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
