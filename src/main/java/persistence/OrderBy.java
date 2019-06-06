package persistence;

import com.querydsl.core.types.OrderSpecifier;

import modele.QCompany;
import modele.QComputer;

public enum OrderBy {
	ID("computer.id ASC", QComputer.computer.id_.asc()), NAME_ASC("computer.name ASC", QComputer.computer.name.asc()),
	NAME_DESC("computer.name DESC", QComputer.computer.name.desc()),
	INTRODUCED_ASC("computer.introduced ASC", QComputer.computer.introduced.asc()),
	INTRODUCED_DESC("computer.introduced DESC", QComputer.computer.introduced.desc()),
	DISCONTINUED_ASC("computer.discontinued ASC", QComputer.computer.discontinued.asc()),
	DISCONTINUED_DESC("computer.discontinued DESC", QComputer.computer.discontinued.desc()),
	COMPANY_NAME_ASC("company.name ASC", QCompany.company.name.asc()),
	COMPANY_NAME_DESC("company.name DESC", QCompany.company.name.desc());

	private String name = "";
	private OrderSpecifier<?> field;

	OrderBy(String name, OrderSpecifier<?> field) {
		this.name = name;
		this.field = field;
	}

	public String getName() {
		return name;
	}

	public OrderSpecifier<?> getField() {
		return field;
	}

	public static OrderBy getOrderByField(String s) {
		for (OrderBy orderby : OrderBy.values()) {
			if (orderby.name.contentEquals(s)) {
				return orderby;
			}
		}
		return ID;
	}

	@Override
	public String toString() {
		return name;

	}
}
