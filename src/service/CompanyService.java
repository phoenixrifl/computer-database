package service;

import java.util.ArrayList;

import dto.CompanyDTO;
import dto.Mappeur;
import modele.Company;
import persistence.CompanyDAO;


public class CompanyService {

	private CompanyDAO companyDAO;
	private Mappeur mappeur;
	
	public CompanyService() {
		this.companyDAO = new CompanyDAO();
		this.mappeur = new Mappeur();
	}
	
	public ArrayList<CompanyDTO> findAll(){
		ArrayList<Company> company = this.companyDAO.findAll();
		return this.mappeur.ModelToDTO_(company);
		
	}
}
