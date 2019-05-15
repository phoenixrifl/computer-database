package service;

import java.util.ArrayList;

import dto.CompanyDTO;
import dto.Mappeur;
import exception.SqlCommandeException;
import modele.Company;
import persistence.CompanyDAO;


public class CompanyService {

	private CompanyDAO companyDAO;
	private Mappeur mappeur;
	
	private static CompanyService instance = null;
	
	private CompanyService() {
		this.companyDAO = CompanyDAO.getInstance();
		this.mappeur = Mappeur.getInstance();
	}
	
	public final static CompanyService getInstance() {
		if(CompanyService.instance == null) {
			instance = new CompanyService();
		}
		return instance;
	}


	public CompanyDTO find(long id) throws SqlCommandeException {
		Company company = this.companyDAO.find(id);
		return this.mappeur.ModelToDTOCompany(company);
	}
	
	public ArrayList<CompanyDTO> findAll(int limits, int offset) throws SqlCommandeException {
		ArrayList<Company> companies = this.companyDAO.findAll(limits, offset);
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
