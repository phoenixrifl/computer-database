package persistence;

public enum OrderByColumn {
	ID("computer.id"), NAME("computer.name"), INTRODUCED("computer.introduced"), DISCONTINUED("computer.discontinued"),
	COMPANY_NAME("company.name");

	private String name;

	OrderByColumn(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;

	}
}
