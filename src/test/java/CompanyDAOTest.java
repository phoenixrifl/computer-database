package test.java;

import org.junit.Before;
import org.junit.Test;

import main.java.persistence.CompanyDAO;

public class CompanyDAOTest {
	
	private CompanyDAO companyDAO;
	
	@Before
	public void setUp() {
		companyDAO = CompanyDAO.getInstance();
	}
	
	@Test
	public void TestFindAll() {
		companyDAO.findAll(1,10);
	}
	

}
