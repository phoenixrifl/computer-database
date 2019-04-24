package main.java.modele;

import java.time.LocalDate;

public class Computer {

	private int id_;
	private String name;
	private LocalDate introduced;
	private LocalDate discontinued;
	private int company_id;
	
	public Computer() {}
	
	public Computer(int id_, String name, LocalDate introduced, LocalDate discontinued, int company_id) {
		super();
		this.id_ = id_;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company_id = company_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getIntroduced() {
		return introduced;
	}

	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}

	public LocalDate getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(LocalDate discontinued) {
		this.discontinued = discontinued;
	}

	public int getId_() {
		return id_;
	}

	public void setId_(int id_) {
		this.id_ = id_;
	}
	public int getCompany_id() {
		return company_id;
	}
	
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	@Override
	public String toString() {
		return "Computer [id_=" + id_ + ", name=" + name + ", introduced=" + introduced + ", discontinued="
				+ discontinued + ", company_id=" + company_id + "]\n";
	}
	
	
	
}
