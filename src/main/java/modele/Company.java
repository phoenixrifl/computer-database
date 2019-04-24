package main.java.modele;

public class Company {
	private int id_;
	private String name;
	
	public Company() {}
	
	public Company(int id_, String name) {
		super();
		this.id_ = id_;
		this.name = name;
	}


	public int getId_() {
		return id_;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "Company [id_=" + id_ + ", name=" + name + ", getId_()=" + getId_() + ", getName()=" + getName() + "]\n";
	}


	
	
	
	
}
