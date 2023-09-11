package bsh40_mvc.controller.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bsh40_mvc.controller.growandsell.Action;
import bsh40_mvc.controller.growandsell.ActionForward;
import bsh40_mvc.model.PageInfoVO;
import bsh40_mvc.model.ProductDAO;
import bsh40_mvc.model.ProductDTO;

public class selectTest2 implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		String currentPageNo;
		ProductDAO po = new ProductDAO();
		PageInfoVO pageinfo;
		int cpn;
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("pageInfoVO")==null) {
			pageinfo = new PageInfoVO();
			session.setAttribute("pageInfoVO", pageinfo);
		}
		else{
			pageinfo = (PageInfoVO)session.getAttribute("pageInfoVO");
		}
		
		currentPageNo = request.getParameter("currentPageNo");
		cpn = (currentPageNo ==null)?0:Integer.parseInt(currentPageNo);
		pageinfo.setCurrentPageNo(cpn);
		pageinfo.adjPageInfo();
		
		String typeCheck = request.getParameter("type");
		
		if(typeCheck != null && typeCheck.equals("전체")) {
			session.setAttribute("typeCheck", "전체");
			if(session.getAttribute("search") != null || session.getAttribute("searchValue") != null) {
				session.removeAttribute("search");
				session.removeAttribute("searchValue");
			}
		}else if(typeCheck != null && typeCheck.equals("묘목")) {
			session.setAttribute("typeCheck", "묘목");
			if(session.getAttribute("search") != null || session.getAttribute("searchValue") != null) {
				session.removeAttribute("search");
				session.removeAttribute("searchValue");
			}
		}else if(typeCheck != null && typeCheck.equals("모종")){
			session.setAttribute("typeCheck", "모종");
			if(session.getAttribute("search") != null || session.getAttribute("searchValue") != null) {
				session.removeAttribute("search");
				session.removeAttribute("searchValue");
			}
		}
		if(String.valueOf(session.getAttribute("typeCheck")).equals("전체")) {
			ArrayList<ProductDTO> productList = po.getListForPage(pageinfo);
			request.setAttribute("productList", productList);
		}else if(String.valueOf(session.getAttribute("typeCheck")).equals("묘목")) {
			ArrayList<ProductDTO> productList = po.getListForPageSelect("묘목", pageinfo);
			request.setAttribute("productList", productList);
		}else if(String.valueOf(session.getAttribute("typeCheck")).equals("모종")){
			ArrayList<ProductDTO> productList = po.getListForPageSelect("모종", pageinfo);
			request.setAttribute("productList", productList);
		}
		String search = request.getParameter("search");
		if(search != null) {
			session.setAttribute("search", search);
			if(session.getAttribute("typeCheck") != null) {
				session.removeAttribute("typeCheck");
			}
		}
		if(session.getAttribute("search") != null) {
			String searchValue = request.getParameter("searchValue");
			if(searchValue != null) {
				searchValue = searchValue.trim().replace(" ", "");
				session.setAttribute("searchValue", searchValue);
			}
			String s1, s2;
			if(session.getAttribute("search") != null && session.getAttribute("searchValue").equals("")) {
				ArrayList<ProductDTO> productList = po.getListForPage(pageinfo);
				request.setAttribute("productList", productList);
			}else if(session.getAttribute("search") != null && !String.valueOf(session.getAttribute("searchValue")).equals("")) {
				if(String.valueOf(session.getAttribute("search")).equals("allproduct")) {
					s2 = String.valueOf(session.getAttribute("searchValue"));
					ArrayList<ProductDTO> productList = po.getProductListForAllSearch(s2, pageinfo);
					request.setAttribute("productList", productList);
				}else if(!String.valueOf(session.getAttribute("search")).equals("allproduct")){
					s1 = String.valueOf(session.getAttribute("search"));
					s2 = String.valueOf(session.getAttribute("searchValue"));
					ArrayList<ProductDTO> productList = po.getProductListForPageSearch(s1, s2, pageinfo);
					request.setAttribute("productList", productList);
				}
			}
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/com/view/productList.jsp");
		return forward;
	}
}
