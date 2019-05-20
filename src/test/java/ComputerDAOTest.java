//
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//
//import java.time.LocalDate;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import modele.Computer;
//import persistence.ComputerDAO;
//
//
//
//public class ComputerDAOTest {
//	private ComputerDAO computerDAO = null;
//	private Computer computer = null, computerInvalid = null;
//	private LocalDate introduced, discontinued;
//	
//	@Before
//	public void setUp() {
//		
//		computerDAO = ComputerDAO.getInstance();
//		introduced = LocalDate.parse("2016-12-11");
//		discontinued = LocalDate.parse("2018-03-19");
//
//
//	}
//
//	
//	@Test
//	public void TestCreate() {
//		Boolean create = computerDAO.create(computer);
//		assertTrue(create);
//	}
//	
//	@Test
//	public void TestDelete() {
//		Boolean delete = computerDAO.delete(computer);
//		assertTrue(delete);
//	}
//	
//	@Test
//	public void TestFailDelete() {
//		Boolean delete = computerDAO.delete(computerInvalid);
//		assertFalse(delete);
//	}
//		
//	@Test
//	public void TestUpdate() {
//		boolean actual = computerDAO.update(computer);
//		assertTrue(actual);
//	}
//}
