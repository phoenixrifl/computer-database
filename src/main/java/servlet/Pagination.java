package servlet;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.ComputerDTO;
import exception.SqlCommandeException;
import persistence.OrderByColumn;
import persistence.OrderByMode;
import service.ComputerService;

public class Pagination {
	private static Logger logger = LoggerFactory.getLogger(Pagination.class);
	private ComputerService computerService = ComputerService.getInstance();


	public HttpServletRequest doPage(HttpServletRequest request) {
		String page, pagination;
		int limits, pageCourante, offset;
		OrderByColumn byColumn = OrderByColumn.ID;
		OrderByMode byMode = OrderByMode.ASC;
		String search = request.getParameter("search");

		if (request.getParameter("page") == null) {
			page = "1";
		} else {
			page = request.getParameter("page");
		}

		if (request.getParameter("limit") == null) {
			pagination = "10";
		} else {
			pagination = request.getParameter("limit");
		}

		limits = Integer.valueOf(pagination);
		pageCourante = Integer.parseInt(page);
		if (pageCourante < 1) {
			pageCourante = 1;
			page = "1";
		}
		if (limits < 1) {
			limits = 1;
			pagination = "1";
		}
		offset = limits * (pageCourante - 1);
		
		String orderByStr = request.getParameter("orderbycolumn");
		if (orderByStr== null || orderByStr.isEmpty()) {
			orderByStr = "computer.id";
		}
		
		for(OrderByColumn ob : OrderByColumn.values()) {
			if(ob.toString().equals(orderByStr)) {
				byColumn = ob;
			}
		}
		
		if (request.getParameter("asc") == null) {
			byMode = OrderByMode.ASC;
		} else {
			byMode = OrderByMode.DESC;
		}

		ArrayList<ComputerDTO> computerDTO_list = null;
		int nbTotal = 0;
		try {
			if (search == null || search.isEmpty() || search.equals("dashboard")) {
				computerDTO_list = computerService.findAll(limits, offset, byMode, byColumn);
				nbTotal = computerService.count();
			}
			else {
				computerDTO_list = computerService.find(search, limits, offset, byMode, byColumn);
				nbTotal = computerService.countSearch(search);

			}
			
		} catch (SqlCommandeException e) {
			logger.error("findAll " + e.getMessage());

		}
		int nbTotalPage = nbTotal / limits;
		if (nbTotal % limits != 0)
			nbTotalPage++;

		int begin = 0, end = 0;
		if (nbTotalPage <= 5) {
			begin = 1;
			end = nbTotalPage;
		} else {
			if (pageCourante <= 3) {
				begin = 1;
				end = 5;
			} else if (pageCourante >= nbTotalPage - 3) {
				begin = nbTotalPage - 6;
				end = nbTotalPage;
			} else {
				begin = pageCourante - 3;
				end = pageCourante + 3;
			}
		}

		if (pageCourante > nbTotalPage) {
			pageCourante = nbTotalPage - 1;
			page = String.valueOf(pageCourante);
		}
		request.setAttribute("search", search);
		request.setAttribute("orderbycolumn", orderByStr);
		request.setAttribute("asc", byMode);
		request.setAttribute("computers", computerDTO_list);
		request.setAttribute("taille", nbTotal);
		request.setAttribute("begin", begin);
		request.setAttribute("end", end);
		request.setAttribute("page", page);
		request.setAttribute("limit", limits);
		
		return request;
	}
}
