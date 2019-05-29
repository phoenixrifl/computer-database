package service;

import java.util.List;

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
		String[] computer = computer_dto.split(",");
		return create(new ComputerDTO(computer[0], computer[1], computer[2], computer[3], computer[4]));
	}

	public void createDTOWithId(long id, String computer_dto) {
		String[] computer = computer_dto.split(",");
		update(new ComputerDTO(String.valueOf(id), computer[0], computer[1], computer[2], computer[3]));
	}

	public boolean create(ComputerDTO computerDto) {
		Computer computer = this.mappeur.DTOToModel(computerDto);
		if (dateValidator.dateIsValid(computer)) {
			this.computerDAO.create(computer);
			return true;
		} else
			return false;
	}

	public void delete(Long id) {
		this.computerDAO.delete(this.mappeur.DTOToModel(findOne(id)));
	}

	public void update(ComputerDTO computerDto) {
		Computer computer = this.mappeur.DTOToModel(computerDto);
		if (dateValidator.dateIsValid(computer)) {
			this.computerDAO.update(computer);
		}
	}

	public ComputerDTO findOne(Long id) {
		Computer computer = this.computerDAO.find(id);
		return this.mappeur.ModelToDTO(computer);
	}

	public long count() {
		return this.computerDAO.count();
	}

	public long countSearch(Pagination pagination) {
		return this.computerDAO.countSearch(pagination);
	}

	public List<ComputerDTO> findAll() throws SqlCommandeException {
		List<Computer> computer = this.computerDAO.findAll();
		return this.mappeur.ModelToDTO(computer);

	}

	public List<ComputerDTO> findAll(Pagination pagination) throws SqlCommandeException {
		List<Computer> computers = this.computerDAO.findAll(pagination);
		return this.mappeur.ModelToDTO(computers);
	}

	public List<ComputerDTO> find(Pagination pagination) throws SqlCommandeException {
		List<Computer> computers = this.computerDAO.searchComputers(pagination);
		return this.mappeur.ModelToDTO(computers);
	}

}
