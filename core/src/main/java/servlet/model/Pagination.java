package servlet.model;

import model.OrderBy;

public class Pagination {
	private int page;
	private int limit;
	private int offset;
	private long nbTotalComputers;
	private int nbTotalPages;
	private OrderBy orderby;
	private int begin;
	private int end;
	private String search;

	public Pagination() {
		this.page = 1;
		this.limit = 10;
		this.begin = 1;
		this.end = 1;
		this.offset = limit * (page - 1);
		this.orderby = OrderBy.ID;
	}

	public void pageview() {

		this.nbTotalPages = (int) (this.nbTotalComputers / this.limit);
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

	public long getNbTotalComputers() {
		return nbTotalComputers;
	}

	public void setNbTotalComputers(long nbTotalComputers) {
		this.nbTotalComputers = nbTotalComputers;
	}

	public int getNbTotalPages() {
		return nbTotalPages;
	}

	public void setNbTotalPages(int nbTotalPages) {
		this.nbTotalPages = nbTotalPages;
	}

	public OrderBy getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		if (orderby == null || orderby.isEmpty()) {
			return;
		} else {
			for (OrderBy ob : OrderBy.values()) {
				if (ob.toString().equals(orderby)) {
					this.orderby = ob;
				}
			}
		}
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
				+ nbTotalComputers + ", nbTotalPages=" + nbTotalPages + ", orderby=" + orderby + ", begin=" + begin
				+ ", end=" + end + ", search=" + search + "]";
	}

}
