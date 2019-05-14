package main.java.service;

import java.util.ArrayList;



import main.java.dto.ComputerDTO;
import main.java.dto.Mappeur;
import main.java.exception.SqlCommandeException;
import main.java.modele.Computer;
import main.java.persistence.ComputerDAO;
import main.java.validator.DateValidator;

public class ComputerService {
	
	private ComputerDAO computerDAO;
	private Mappeur mappeur;
	private DateValidator dateValidator;
	
	private static ComputerService instance = null;

	private ComputerService() {
		this.computerDAO = ComputerDAO.getInstance();
		this.mappeur = Mappeur.getInstance();
		this.dateValidator = DateValidator.getInstance();
	}
	
	public final static ComputerService getInstance() {
		if(ComputerService.instance == null) {
			instance = new ComputerService();
		}
		return instance;
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
	
	public ArrayList<ComputerDTO> findAll(int limits, int offset) throws SqlCommandeException{
			ArrayList<Computer> computers = this.computerDAO.findAll(limits, offset);
			return this.mappeur.ModelToDTO(computers);
	}

	public ArrayList<ComputerDTO> find(String search, int limits, int offset) throws SqlCommandeException {
		ArrayList<Computer> computers = this.computerDAO.searchComputers(search, limits, offset);
		return this.mappeur.ModelToDTO(computers);
	}
	
}
