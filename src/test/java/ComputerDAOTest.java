package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.java.modele.Computer;
import main.java.persistence.ComputerDAO;

public class ComputerDAOTest {
	private ComputerDAO computerDAO = null;
	private Computer computer = null, computerInvalid = null;
	private LocalDate introduced, discontinued;
	
	@Before
	public void setUp() {
		
		computerDAO = ComputerDAO.getInstance();
		introduced = LocalDate.parse("2016-12-11");
		discontinued = LocalDate.parse("2018-03-19");

		computer = new Computer(578, "SuperMAC", introduced, discontinued, 4);
		computerInvalid = new Computer(4, "yolo", null, null, 5);
	}
	


	
	
	@Test
	public void TestFindAll() {
		ArrayList<Computer> computers = computerDAO.findAll(1,10);
		assertTrue(computers != null && !computers.isEmpty());
	}
	
	@Test
	public void TestCreate() {
		Boolean create = computerDAO.create(computer);
		assertTrue(create);
	}
	
	@Test
	public void TestDelete() {
		Boolean delete = computerDAO.delete(computer);
		assertTrue(delete);
	}
	
	@Test
	public void TestFailDelete() {
		Boolean delete = computerDAO.delete(computerInvalid);
		assertFalse(delete);
	}
	
	@Test
	public void TestFind() {
		Computer actual = computerDAO.find(23);
		LocalDate introduced = LocalDate.parse("1986-01-16");
		LocalDate discontinued = LocalDate.parse("1990-10-15");
		Computer expected = new Computer(23, "Macintosh Plus", introduced, discontinued, 1);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void TestUpdate() {
		boolean actual = computerDAO.update(computer);
		assertTrue(actual);
	}
}
