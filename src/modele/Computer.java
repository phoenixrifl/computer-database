package modele;

import java.sql.Date;

public class Computer {

	private int id_;
	private String name;
	private Date introduced;
	private Date discontinued;
	private int company_id;
	
	public Computer() {}
	
	public Computer(int id_, String name, Date introduced, Date discontinued, int company_id) {
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

	public Date getIntroduced() {
		return introduced;
	}

	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}

	public Date getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(Date discontinued) {
		this.discontinued = discontinued;
	}

	public int getId_() {
		return id_;
	}

	public int getCompany_id() {
		return company_id;
	}
	
	
	
}
