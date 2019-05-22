package servlet.model;

import persistence.OrderByColumn;
import persistence.OrderByMode;


public class Pagination {
	private int page;
	private int limit;
	private int offset;
	private int nbTotalComputers;
	private int nbTotalPages;
	private OrderByColumn byColumn;
	private OrderByMode byMode;
	

	public Pagination() {
		this.page = 1;
		this.limit = 10;
		this.offset = limit * (page - 1);
		this.byColumn = OrderByColumn.ID;
		this.byMode = OrderByMode.ASC;
	}

	public int getPage() {
		return page;
	}



	public void setPage(int page) {
		this.page = page;
	}



	public int getLimit() {
		return limit;
	}



	public void setLimit(int limit) {
		this.limit = limit;
	}



	public int getOffset() {
		return offset;
	}



	public void setOffset(int offset) {
		this.offset = offset;
	}



	public int getNbTotalComputers() {
		return nbTotalComputers;
	}



	public void setNbTotalComputers(int nbTotalComputers) {
		this.nbTotalComputers = nbTotalComputers;
	}



	public int getNbTotalPages() {
		return nbTotalPages;
	}



	public void setNbTotalPages(int nbTotalPages) {
		this.nbTotalPages = nbTotalPages;
	}



	public OrderByColumn getByColumn() {
		return byColumn;
	}



	public void setByColumn(OrderByColumn byColumn) {
		this.byColumn = byColumn;
	}



	public OrderByMode getByMode() {
		return byMode;
	}



	public void setByMode(OrderByMode byMode) {
		this.byMode = byMode;
	}

}
