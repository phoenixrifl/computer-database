
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import dto.CompanyDTO;
import dto.Mappeur;
import modele.Company;



public class MappeurTest {
	private static Mappeur mappeur;
	
	@Before
	public void setUpBefore() {
		mappeur = Mappeur.getInstance();
	}
	
	@Test
	public void testDTOtoCompany() {
		Company company = mappeur.DTOTOModelCompany(new CompanyDTO());
		assertNull(company.getId_());
		assertNull(company.getName());
	}
}
