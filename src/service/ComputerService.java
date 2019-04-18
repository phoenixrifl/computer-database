package service;

import java.util.ArrayList;

import dto.ComputerDTO;
import dto.Mappeur;
import modele.Computer;
import persistence.ComputerDAO;

public class ComputerService {
	
	private ComputerDAO computerDAO;
	private Mappeur mappeur;
	
	public ComputerService() {
		this.computerDAO = new ComputerDAO();
		this.mappeur = new Mappeur();
	}
	
	public boolean createDTO(String comp) {
		String [] computer = comp.split(",");
		return create(new ComputerDTO
				(computer[0],
				computer[1],
				computer[2],
				computer[3]
				));
	}
	
	public boolean create(ComputerDTO comp) {
		return this.computerDAO.create(this.mappeur.DTOToModel(comp));
	}
	
	public boolean delete(int id) {
		return this.computerDAO.delete(this.mappeur.DTOToModel(findOne(id)));
	}
	
	public boolean update(ComputerDTO comp) {
		return this.computerDAO.update(this.mappeur.DTOToModel(comp));
	}
	
	public ComputerDTO findOne(int id) {
		Computer computer = this.computerDAO.find(id);
		return this.mappeur.ModelToDTO(computer);
	}
	
	public ArrayList<ComputerDTO> findAll(){
		ArrayList<Computer> comp = this.computerDAO.findAll();
		return this.mappeur.ModelToDTO(comp);
		
	}
	
}
