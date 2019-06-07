package dto;

import org.springframework.lang.Nullable;

public class ComputerDTO {

	@Nullable
	private Long id;
	private String name;
	private String introduced;
	private String discontinued;
	@Nullable
	private Long company_id;
	@Nullable
	private String company_name;

	public ComputerDTO() {
		super();
	}

	public ComputerDTO(Long id, String name, String introduced, String discontinued, Long company_id,
			String company_name) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company_id = company_id;
		this.company_name = company_name;

	}

	public ComputerDTO(Long id, String name, String dateIntroduced, String dateDiscontinued, Long idCompanie) {
		this.id = id;
		this.name = name;
		this.introduced = dateIntroduced;
		this.discontinued = dateDiscontinued;
		this.company_id = idCompanie;
	}

	public ComputerDTO(String name, String dateIntroduced, String dateDiscontinued, Long idCompanie) {
		this.id = (long) -1;
		this.name = name;
		this.introduced = dateIntroduced;
		this.discontinued = dateDiscontinued;
		this.company_id = idCompanie;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}

	@Override
	public String toString() {
		return "ComputerDTO [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued="
				+ discontinued + ", company_name=" + company_name + "]";
	}

}
