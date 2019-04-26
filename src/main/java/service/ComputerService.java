package main.java.service;

import java.util.ArrayList;

import main.java.dto.ComputerDTO;
import main.java.dto.Mappeur;
import main.java.modele.Computer;
import main.java.persistence.ComputerDAO;

public class ComputerService {
	
	private ComputerDAO computerDAO;
	private Mappeur mappeur;
	
	private static ComputerService instance = null;
	
	private ComputerService() {
		this.computerDAO = ComputerDAO.getInstance();
		this.mappeur = Mappeur.getInstance();
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
				computer[3]
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
	
	public boolean create(ComputerDTO computer) {
		return this.computerDAO.create(this.mappeur.DTOToModel(computer));
	}
	
	public boolean delete(int id) {
		return this.computerDAO.delete(this.mappeur.DTOToModel(findOne(id)));
	}
	
	public boolean update(ComputerDTO computer) {
		return this.computerDAO.update(this.mappeur.DTOToModel(computer));
	}
	
	public ComputerDTO findOne(int id) {
		Computer computer = this.computerDAO.find(id);
		return this.mappeur.ModelToDTO(computer);
	}
	
	public ArrayList<ComputerDTO> findAll(){
		ArrayList<Computer> computer = this.computerDAO.findAll();
		return this.mappeur.ModelToDTO(computer);
		
	}
	
	public ArrayList<ComputerDTO> findAll(int limits, int offset){
			ArrayList<Computer> computers = this.computerDAO.findAll(limits, offset);
			return this.mappeur.ModelToDTO(computers);
	}
	
}
