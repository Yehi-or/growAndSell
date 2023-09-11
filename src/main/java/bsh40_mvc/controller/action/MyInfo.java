package bsh40_mvc.controller.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.exam01.dto.UserDAO;
import com.example.demo.exam01.dto.UserDTO;

import bsh40_mvc.controller.growandsell.Action;
import bsh40_mvc.controller.growandsell.ActionForward;

public class MyInfo implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		
		String user_id = request.getParameter("user_id");
		UserDAO userdao = new UserDAO();
		ArrayList<UserDTO> list;
		
		list = userdao.userInfo(user_id);
		request.setAttribute("userInfo", list);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/com/view/myPageUserInfo.jsp");
		return forward;
	}
}