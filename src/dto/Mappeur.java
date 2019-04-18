package dto;

import java.sql.Date;
import java.util.ArrayList;

import modele.Company;
import modele.Computer;

public class Mappeur {
	
	public ComputerDTO ModelToDTO(Computer computer) {
		return new ComputerDTO(
				String.valueOf(computer.getId_()), 
				computer.getName(), 
				String.valueOf(computer.getIntroduced()), 
				String.valueOf(computer.getDiscontinued()),
				String.valueOf(computer.getCompany_id()));
	}
	
	public Computer DTOToModel(ComputerDTO computerDto) {
		return new Computer(
				Integer.parseInt(computerDto.getId()),
				computerDto.getName(),
				Date.valueOf(computerDto.getIntroduced()).toLocalDate(),
				Date.valueOf(computerDto.getDiscontinued()).toLocalDate(),
				Integer.parseInt(computerDto.getCompany_id()));
	}
	
	public ArrayList<Computer> DTOToModel(ArrayList<ComputerDTO> computerDto){
		ArrayList<Computer> tmp = new ArrayList<Computer>();
		for(ComputerDTO c : computerDto) {
			tmp.add(DTOToModel(c));
		}
		return tmp;
	}
	
	public ArrayList<ComputerDTO> ModelToDTO(ArrayList<Computer> computer){
		ArrayList<ComputerDTO> tmp = new ArrayList<ComputerDTO>();
		for(Computer c : computer) {
			tmp.add(ModelToDTO(c));
		}
		return tmp;
	}
	
	public CompanyDTO ModelToDTOCompany(Company company) {
		return new CompanyDTO(
				String.valueOf(company.getId_()),
				company.getName());
	}
	
	public Company DTOTOModelCompany(CompanyDTO companyDto) {
		return new Company(
				Integer.parseInt(companyDto.getId()),
				companyDto.getName());
	}
	
	public ArrayList<Company> DTOToModel_(ArrayList<CompanyDTO> companyDto){
		ArrayList<Company> tmp = new ArrayList<Company>();
		for(CompanyDTO c : companyDto) {
			tmp.add(DTOTOModelCompany(c));
		}
		return tmp;
	}
	
	public ArrayList<CompanyDTO> ModelToDTO_(ArrayList<Company> company){
		ArrayList<CompanyDTO> tmp = new ArrayList<CompanyDTO>();
		for(Company c : company) {
			tmp.add(ModelToDTOCompany(c));
		}
		return tmp;
	}
}
