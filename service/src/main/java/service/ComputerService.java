package service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.ComputerDAO;

import model.Computer;
import servlet.model.Pagination;

@Service
@Transactional
public class ComputerService {

	private ComputerDAO computerDAO;


	public ComputerService(ComputerDAO computerDAO) {
		this.computerDAO = computerDAO;

	}

//	public boolean createDTO(String computer_dto) {
//		String[] computer = computer_dto.split(",");
//		return create(new ComputerDTO(Long.parseLong(computer[0]), computer[1], computer[2], computer[3],
//				Long.parseLong(computer[4])));
//	}
//
//	public void createDTOWithId(long id, String computer_dto) {
//		String[] computer = computer_dto.split(",");
//		update(new ComputerDTO(id, computer[0], computer[1], computer[2], Long.parseLong(computer[3])));
//	}

	public void create(Computer computer) {
	
		this.computerDAO.create(computer);
	
	}

	public void delete(Long id) {
		this.computerDAO.delete(findOne(id));
	}

	public void update(Computer computer) {
		this.computerDAO.update(computer);
	}

	public Computer findOne(Long id) {
		return this.computerDAO.find(id);
	}

	public long count() {
		return this.computerDAO.count();
	}

	public long countSearch(Pagination pagination) {
		return this.computerDAO.countSearch(pagination);
	}

	public List<Computer> findAll() {
		List<Computer> computer = this.computerDAO.findAll();
		return computer;

	}

	public List<Computer> findAll(Pagination pagination)  {
		List<Computer> computers = this.computerDAO.findAll(pagination);
		return computers;
	}

	public List<Computer> find(Pagination pagination) {
		List<Computer> computers = this.computerDAO.searchComputers(pagination);
		return computers;
	}

}
