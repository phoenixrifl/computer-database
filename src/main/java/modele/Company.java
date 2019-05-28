package modele;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id_;

	@Column(name = "name")
	private String name;

	public Company() {
	}

	public Company(int id_, String name) {
		super();
		this.id_ = id_;
		this.name = name;
	}

	public void setId_(int id_) {
		this.id_ = id_;
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
