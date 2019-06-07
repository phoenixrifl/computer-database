package service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.CompanyDAO;

import model.Company;
import servlet.model.Pagination;

@Service
@Transactional
public class CompanyService {

	private CompanyDAO companyDAO;

	public CompanyService(CompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}

	public Company find(long id){
		Company company = this.companyDAO.find(id);
		return company;
	}

	public List<Company> findAll(Pagination pagination) {
		List<Company> companies = this.companyDAO.findAll(pagination);
		return companies;
	}

	public List<Company> findAll(){
		List<Company> companies = this.companyDAO.findAll();
		return companies;
	}

	public void delete(long id) {
		this.companyDAO.delete(id);
	}
}
