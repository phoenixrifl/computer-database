package service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import dto.CompanyDTO;
import dto.Mappeur;
import exception.SqlCommandeException;
import modele.Company;
import persistence.CompanyDAO;
import servlet.model.Pagination;

@Service
public class CompanyService {

	private CompanyDAO companyDAO;
	private Mappeur mappeur;

	public CompanyService(CompanyDAO companyDAO, Mappeur mappeur) {
		this.companyDAO = companyDAO;
		this.mappeur = mappeur;
	}

	public CompanyDTO find(long id) throws SqlCommandeException {
		Company company = this.companyDAO.find(id);
		return this.mappeur.ModelToDTOCompany(company);
	}

	public ArrayList<CompanyDTO> findAll(Pagination pagination) throws SqlCommandeException {
		ArrayList<Company> companies = this.companyDAO.findAll(pagination);
		return this.mappeur.ModelToDTO_(companies);
	}

	public ArrayList<CompanyDTO> findAll() throws SqlCommandeException {
		ArrayList<Company> companies = this.companyDAO.findAll();
		return this.mappeur.ModelToDTO_(companies);
	}

	public boolean delete(int id) throws SqlCommandeException {
		return this.companyDAO.delete(id);
	}
}
