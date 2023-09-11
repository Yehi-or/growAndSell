package bsh40_mvc.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.exam01.dto.UserDAO;
import com.example.demo.exam01.dto.UserDTO;

import bsh40_mvc.controller.growandsell.Action;
import bsh40_mvc.controller.growandsell.ActionForward;

public class SignUp implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		UserDAO udao = new UserDAO();
		UserDTO udto = new UserDTO();
		boolean result;
		
		udto.setUserId(request.getParameter("id"));
		udto.setUserName(request.getParameter("name"));
		udto.setUserZipcode(request.getParameter("zipcode"));
		udto.setUserAddress(request.getParameter("address"));
		udto.setUserAddressDetail(request.getParameter("address_detail"));
		udto.setUserPasswd(request.getParameter("pssswd_check"));
		udto.setUserCheck(request.getParameter("check"));
		
		result = udao.userSignUp(udto);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		
		if(result == true) {
			forward.setPath("/com/view/user_login.jsp");
			return forward;
		}else {
			forward.setPath("/com/view/error.jsp");
			return forward;
		}
	}
}
