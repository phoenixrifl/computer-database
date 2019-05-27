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
	private int begin;
	private int end;
	private String search;

	public Pagination() {
		this.page = 1;
		this.limit = 10;
		this.begin = 1;
		this.end = 1;
		this.offset = limit * (page - 1);
		this.byColumn = OrderByColumn.ID;
		this.byMode = OrderByMode.ASC;

	}

	public void pageview() {

		this.nbTotalPages = this.nbTotalComputers / this.limit;
		if ((this.nbTotalComputers % this.limit) != 0)
			this.nbTotalPages = this.nbTotalPages + 1;

		if (this.nbTotalPages <= 5) {
			this.begin = 1;
			this.end = this.nbTotalPages;
		} else {
			if (this.page <= 3) {
				this.begin = 1;
				this.end = 5;
			} else if (this.page >= this.nbTotalPages - 3) {
				this.begin = this.nbTotalPages - 5;
				this.end = this.nbTotalPages;
			} else {
				this.begin = this.page - 3;
				this.end = this.page + 3;
			}
		}

		if (this.page > this.nbTotalPages) {
			this.page = this.nbTotalPages - 1;
		}
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

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	@Override
	public String toString() {
		return "Pagination [page=" + page + ", limit=" + limit + ", offset=" + offset + ", nbTotalComputers="
				+ nbTotalComputers + ", nbTotalPages=" + nbTotalPages + ", byColumn=" + byColumn + ", byMode=" + byMode
				+ ", begin=" + begin + ", end=" + end + ", search=" + search + "]";
	}

}
