package main.java.dto;


public class ComputerDTO {
	private String id;
	private String name;
	private String introduced;
	private String discontinued;
	private String company_name;
	private String company_id;
	
	public ComputerDTO() {
		super();
	}
	
	public ComputerDTO(String id, String name, String introduced, String discontinued, String company_id, String company_name) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company_name = company_name;
		this.company_id = company_id;

	}
	
	public ComputerDTO(String id, String name, String dateIntroduced, String dateDiscontinued, String idCompanie) {
		this.id = id;
		this.name = name;
		this.introduced = dateIntroduced;
		this.discontinued = dateDiscontinued;
		this.company_id = idCompanie;
	}
	

	public ComputerDTO(String name, String dateIntroduced, String dateDiscontinued, String idCompanie) {
		this.id = "-1";
		this.name = name;
		this.introduced = dateIntroduced;
		this.discontinued = dateDiscontinued;
		this.company_id = idCompanie;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntroduced() {
		return this.introduced;
	}
	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}
	public String getDiscontinued() {
		return this.discontinued;
	}
	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}
	
	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	@Override
	public String toString() {
		return "ComputerDTO [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued="
				+ discontinued + ", company_name=" + company_name + "]";
	}

	
	
	
}
