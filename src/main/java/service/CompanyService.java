package main.java.service;

import java.util.ArrayList;

import main.java.dto.CompanyDTO;
import main.java.dto.Mappeur;
import main.java.modele.Company;
import main.java.persistence.CompanyDAO;
import main.java.persistence.ComputerDAO;


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
	
	public ArrayList<CompanyDTO> findAll(){
		ArrayList<Company> company = this.companyDAO.findAll();
		return this.mappeur.ModelToDTO_(company);
		
	}

	public ArrayList<CompanyDTO> findAll(int limits, int offset) {
		ArrayList<Company> companies = this.companyDAO.findAll(limits, offset);
		return this.mappeur.ModelToDTO_(companies);
	}
}
