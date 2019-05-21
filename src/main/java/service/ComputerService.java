package service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import dto.ComputerDTO;
import dto.Mappeur;
import exception.SqlCommandeException;
import modele.Computer;
import persistence.ComputerDAO;
import servlet.model.Pagination;
import validator.DateValidator;

@Service
public class ComputerService {
	
	private ComputerDAO computerDAO;
	private Mappeur mappeur;
	private DateValidator dateValidator;
	

	public ComputerService(ComputerDAO computerDAO, Mappeur mappeur, DateValidator dateValidator) {
		this.computerDAO = computerDAO;
		this.mappeur = mappeur;
		this.dateValidator = dateValidator;
	}
		
	public boolean createDTO(String computer_dto) {
		String [] computer = computer_dto.split(",");
		return create(new ComputerDTO
				(computer[0],
				computer[1],
				computer[2],
				computer[3],
				computer[4]
				));
	}
	
	public boolean createDTOWithId(int id, String computer_dto) {
		String [] computer = computer_dto.split(",");
		return update(new ComputerDTO
				(String.valueOf(id),
				computer[0],
				computer[1],
				computer[2],
				computer[3]
				));
	}
	
	public boolean create(ComputerDTO computerDto) {
		Computer computer = this.mappeur.DTOToModel(computerDto);
		if(dateValidator.dateIsValid(computer)) {
			this.computerDAO.create(computer);
		}
		return true;
	}
	
	public boolean delete(int id) {
		return this.computerDAO.delete(this.mappeur.DTOToModel(findOne(id)));
	}
	
	public boolean update(ComputerDTO computerDto) {
		Computer computer = this.mappeur.DTOToModel(computerDto);
		if(dateValidator.dateIsValid(computer)) {
			this.computerDAO.update(computer);
		}
		return true;
	}
	
	public ComputerDTO findOne(int id) {
		Computer computer = this.computerDAO.find(id);
		return this.mappeur.ModelToDTO(computer);
	}
	
	public int count() {
		return this.computerDAO.count();
	}
	
	public int countSearch(String search) {
		return this.computerDAO.countSearch(search);
	}
	
	public ArrayList<ComputerDTO> findAll() throws SqlCommandeException{
		ArrayList<Computer> computer = this.computerDAO.findAll();
		return this.mappeur.ModelToDTO(computer);
		
	}
	
	public ArrayList<ComputerDTO> findAll(Pagination pagination) throws SqlCommandeException{
			ArrayList<Computer> computers = this.computerDAO.findAll(pagination);
			return this.mappeur.ModelToDTO(computers);
	}

	public ArrayList<ComputerDTO> find(String search, Pagination pagination) throws SqlCommandeException {
		ArrayList<Computer> computers = this.computerDAO.searchComputers(search, pagination);
		return this.mappeur.ModelToDTO(computers);
	}
	
}
