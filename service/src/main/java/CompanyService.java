

import java.util.List;

import org.springframework.stereotype.Service;

import CompanyDAO;
import dto.CompanyDTO;
import dto.Mappeur;
import exception.SqlCommandeException;
import model.Company;
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

	public List<CompanyDTO> findAll(Pagination pagination) throws SqlCommandeException {
		List<Company> companies = this.companyDAO.findAll(pagination);
		return this.mappeur.ModelToDTO_(companies);
	}

	public List<CompanyDTO> findAll() throws SqlCommandeException {
		List<Company> companies = this.companyDAO.findAll();
		return this.mappeur.ModelToDTO_(companies);
	}

	public void delete(long id) throws SqlCommandeException {
		this.companyDAO.delete(id);
	}
}
