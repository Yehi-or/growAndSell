package bsh40_mvc.controller.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.demo.exam01.dto.UserDAO;
import com.example.demo.exam01.dto.UserDTO;

import bsh40_mvc.controller.growandsell.Action;
import bsh40_mvc.controller.growandsell.ActionForward;
import bsh40_mvc.model.PageInfoVO;
import bsh40_mvc.model.ProductDAO;

public class AllUsers implements Action {
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
		
		UserDAO udao = new UserDAO();
		ArrayList<UserDTO> list = udao.allUserSelect(pageinfo);
		request.setAttribute("userList", list);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/com/view/memberinquiry.jsp");
		return forward;
	}
}