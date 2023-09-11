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

public class AllProducts implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String currentPageNo;
		PageInfoVO pageinfo;
		ProductDAO pdao = new ProductDAO();
		
		HttpSession session = request.getSession();
		int cpn;
		
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
		
		ArrayList<ProductDTO> list = pdao.getListForPage(pageinfo);
		request.setAttribute("productList", list);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/com/view/productsinquiry.jsp");
		return forward;
	}
}
