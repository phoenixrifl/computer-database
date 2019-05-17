//package test.java;
//
//import static org.junit.Assert.assertNull;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import main.java.dto.CompanyDTO;
//import main.java.dto.Mappeur;
//import main.java.modele.Company;
//
//public class MappeurTest {
//	private static Mappeur mappeur;
//	
//	@Before
//	public void setUpBefore() {
//		mappeur = Mappeur.getInstance();
//	}
//	
//	@Test
//	public void testDTOtoCompany() {
//		Company company = mappeur.DTOTOModelCompany(new CompanyDTO());
//		assertNull(company.getId_());
//		assertNull(company.getName());
//	}
//}
