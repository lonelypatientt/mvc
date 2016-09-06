package com.web.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.entity.User;
import com.web.model.UserModel;
import com.web.model.impl.UserModelMySQLImpl;


public class LoginFilter implements Filter{

	public void destroy() {}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		req.setCharacterEncoding("utf-8");
//		String methodName = request.getParameter("methodName");
//		if(methodName.equals("login") || methodName.equals("reg") || methodName.equals("logout")){
//			//请求通过
			chain.doFilter(request, response);
//			
//		}else{
//			User user = (User)request.getSession().getAttribute("loginUser");
//			if(null != user){
//				String uri = request.getServletPath();
//				uri = uri.substring(1);
//				UserModel um = new UserModelMySQLImpl();
//				boolean b = um.checkUserMenu(user.getUid(), uri);
//				if(b){
//					chain.doFilter(request, response);
//				}else{
//					req.setAttribute("noRightError", "对不起,你没有访问此资源的权限!");
//					req.getRequestDispatcher("view/noRightError.jsp").forward(request, response);
//				}
//			}else{
//			request.getSession().setAttribute("loginError", "对不起，请先登录！");
//			PrintWriter out = response.getWriter();
//			out.write("<script type='text/javascript'>window.top.location.href='login.jsp';</script>");//直接跳转到登录界面
//			}
//		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
