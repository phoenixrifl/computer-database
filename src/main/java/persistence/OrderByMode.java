package persistence;

public enum OrderByMode {
	ASC("ASC"), DESC("DESC");

	private String name;

	OrderByMode(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;

	}
}
