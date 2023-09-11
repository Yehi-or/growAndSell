package bsh40_mvc.controller.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import bsh40_mvc.controller.growandsell.Action;
import bsh40_mvc.controller.growandsell.ActionForward;
import bsh40_mvc.model.PageInfoVO;
import bsh40_mvc.model.PaymentDAO;
import bsh40_mvc.model.PaymentDTO;
import bsh40_mvc.model.ProductDAO;
import bsh40_mvc.model.ProductDTO;

public class MyBuyProduct implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		String currentPageNo;
		PageInfoVO pageinfo;
		
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
		
		String user_id = request.getParameter("user_id");
		PaymentDAO pdao = new PaymentDAO();
		
		if(user_id != null) {
			session.setAttribute("user_id", user_id);
		}
		
		if(session.getAttribute("user_id") != null) {
			ArrayList<PaymentDTO> product_list = pdao.selectMyPayment(String.valueOf(session.getAttribute("user_id")), pageinfo);
			request.setAttribute("productList", product_list);
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/com/view/myPagePayment.jsp");
		return forward;
	}
}
