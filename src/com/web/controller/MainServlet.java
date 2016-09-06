package com.web.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.web.entity.Menu;
import com.web.entity.Role;
import com.web.entity.User;
import com.web.model.UserModel;
import com.web.model.impl.UserModelMySQLImpl;
import com.web.util.DBUtil;
import com.web.util.Page;
import com.web.vo.MenuVo;


public class MainServlet extends HttpServlet{
	//控制层持有一个模型层对象
	private UserModel userModel = new UserModelMySQLImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//接收所有请求
		req.setCharacterEncoding("utf-8");
		String methodName = req.getParameter("methodName");
		Class c = MainServlet.class;
		try {
			Method m = c.getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			m.invoke(this, req,resp);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 用户登录
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void login(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		String userName = req.getParameter("userName");
		String userPassword = req.getParameter("userPassword");
		User user = userModel.loadUserByuserName(userName);
		int result = 1;
		if (user != null){
			// 用户名存在
			if (userPassword.equals(user.getUserPassword())) {
				List<Menu> menulist = userModel.loadMenuByUid(user.getUid());
				req.getSession().setAttribute("menulist", menulist);
				// 密码正确
				req.getSession().setAttribute("loginUser", user);
				//req.getRequestDispatcher("view/Welcome.jsp").forward(req, resp);
				result = 1;
			} else {
				// 用户名正确 但密码错误
				//req.setAttribute("loginError", "密码错误");
				//req.getRequestDispatcher("view/login/login.jsp").forward(req, resp);
				result = 2;
			}
		} else {
			// 用户名不存在
			//req.setAttribute("loginError", "帐号不存在!");
			//req.getRequestDispatcher("view/login/login.jsp").forward(req, resp);
			result = 3;
		}
		resp.setCharacterEncoding("utf-8");
		resp.getWriter().write(result+"");
		resp.getWriter().flush();
	}
	
	
	/**
	 * 展示所有用户
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showUsers(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		List<User> userList = userModel.loadAllUsers();
		//保存数据 全局可以
		req.setAttribute("show", userList);
		req.getRequestDispatcher("/view/showuser.jsp").forward(req, resp);
		
	}
	
	/**
	 * 用户注册
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void reg(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		req.getSession().invalidate();
		resp.sendRedirect("view/regisiter.jsp");
		
	}
	
	/**
	 * 用户退出
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void logout(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		resp.sendRedirect("login.jsp");
	}
	/**
	 * 展示所有班级
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showClass(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException{
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		String sql = "select * from class order by sj desc ";
		List<Object[]>list = DBUtil.executeQuery(sql, null);
		req.setAttribute("classmsg", list);	
		req.getRequestDispatcher("view/showclass.jsp").forward(req, resp);	
	}
	/**
	 * 展示所有学生信息
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showStudent(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException{
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		String sql = "select * from student order by sj desc";
		List<Object[]>list = DBUtil.executeQuery(sql, null);
		req.setAttribute("studentmsg", list);	
		req.getRequestDispatcher("view/showstudent.jsp").forward(req, resp);
	}
	/**
	 * 删除某一个用户
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void deleteUser(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException{
		resp.setCharacterEncoding("utf-8");
		req.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		String userName=req.getParameter("userName");
		userModel.deleteUser(userName);
		req.getRequestDispatcher("showUsers.do?methodName=showUsers").forward(req, resp);
	}
	
	/**
	 * 添加班级
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addClass(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException{
		resp.setCharacterEncoding("utf-8");
		req.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		String cname = req.getParameter("cname");
		userModel.addClass(cname);
		req.setAttribute("addclassmsg","添加成功");
		req.getRequestDispatcher("view/addclass.jsp").forward(req, resp);
	}
	/**
	 * 添加学生
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addStudent(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException{
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		String cname = req.getParameter("cname");
		String sql = "select * from class where cname = ?";
		List<Object []> list = DBUtil.executeQuery(sql, new Object[]{cname});
		if(list.size()==0){
			req.setAttribute("addclassError", "没有这个班级，请重新添加班级！");
			req.getRequestDispatcher("view/addstudent.jsp").forward(req, resp);
		}else{
			req.setAttribute("addclassmsg", "添加学生信息成功！");
			Object [] m =list.get(0);
			sql = "insert into student(sname,sj,cid) values (?,?,?)";
			Date date = new Date();
			String sname=req.getParameter("sname");
			Object[] n={sname,date,m[0]};
			DBUtil.executeDML(sql, n);
			req.getRequestDispatcher("view/addstudent.jsp").forward(req, resp);
		}
	}
	/**
	 * 展示所有菜单
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showMenus(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int pageNo = Integer.valueOf(req.getParameter("pageNo"));
		int pageSize = Integer.valueOf(req.getParameter("pageSize"));
		
		Page<MenuVo> page = userModel.loadAllMenus(pageNo,pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows",page.getDataList());
		map.put("total",page.getTotal());
		String json = JSONObject.fromObject(map).toString();
		
		//String json = JSONArray.fromObject(menuList).toString();
		resp.setCharacterEncoding("utf-8");
		resp.getWriter().write(json);
		resp.getWriter().flush();
		
		
		//req.setAttribute("menuList", menuList);
		//req.getRequestDispatcher("view/showMenus.jsp").forward(req, resp);
	}
	
	/**
	 * 链接到添加菜单界面  动态加载所有一级和二级菜单
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void toAddMenu(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<Menu> menuList = userModel.load12Menus();
		req.setAttribute("menuList", menuList);
		req.getRequestDispatcher("view/addMenu.jsp").forward(req, resp);
	}
	
	/**
	 * 添加菜单
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addMenu(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		
		String mname = req.getParameter("mname");
		String url = req.getParameter("url");
		String isshow = req.getParameter("isshow");
		String parentid = req.getParameter("parentid");
		int i = userModel.addMenu(mname, url, Integer.valueOf(isshow), Integer.valueOf(parentid));
		if(i == 1){
			req.setAttribute("msg", "添加成功！");
			//添加成功 跳转到菜单列表界面
			this.showMenus(req, resp);
		}else{
			req.setAttribute("msg", "添加失败！");
			//添加失败 跳转到添加界面
			this.toAddMenu(req, resp);
		}
	}
	/**
	 * 展示角色列表
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showRoles(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<Role> roleList = userModel.loadAllRoles();
		req.setAttribute("roleList", roleList);
		req.getRequestDispatcher("view/showRoles.jsp").forward(req, resp);
	}
	
	/**
	 * 加载角色对应的菜单
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void loadRoleMenu(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String rid = req.getParameter("rid");
		List<Object[]> roleMenuList = userModel.loadRoleMenuByRoleId(Integer.valueOf(rid));
		req.setAttribute("roleMenuList", roleMenuList);
		Role r = userModel.loadRoleById(Integer.valueOf(rid));
		req.setAttribute("role", r);
		req.getRequestDispatcher("view/roleMenu.jsp").forward(req, resp);
	}
	
	/**
	 * 编辑角色权限
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void editRoleMenu(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String rid = req.getParameter("rid");
		String[] mids = req.getParameterValues("mids");
		userModel.editRoleMenu(Integer.valueOf(rid), mids);
		req.setAttribute("msg", "修改成功!");
		this.loadRoleMenu(req, resp);
	}
	
	/**
	 * 通过角色的rid 去找到他对应的所拥有的用户 就是那些用户是这个角色
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showRoleUser(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException{
		int rid = Integer.valueOf(req.getParameter("rid"));
		List<Object[]>  userList = userModel.showUserbyRole_Rid(rid);	
		req.setAttribute("userList", userList);
		req.setAttribute("rid", rid);
		req.getRequestDispatcher("view/alterRoleUser.jsp").forward(req, resp);
	}
	
	
	/**
	 * 执行修改角色拥有的用户
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void executeAlterUser(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException{
		String [] userSid = req.getParameterValues("userName");
		int roleRid =Integer.valueOf(req.getParameter("rid"));
		int [] usersSid = new int [userSid.length];
		//将返回的字符串数组转化为int数组
		for(int i = 0 ; i< userSid.length;i++){
			usersSid[i]=Integer.valueOf(userSid[i]);
		}
		userModel.executeAlterUser(roleRid, usersSid);
		req.setAttribute("msg", "角色拥有的用户修改成功！！");
		req.getRequestDispatcher("view/alterRoleUser.jsp").forward(req, resp);
	}
	
	
	public void testAJAX(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException{
		System.out.println("AJAX测试来了！");
		
		resp.setCharacterEncoding("utf-8");
		resp.getWriter().write("{\"address\":\"重庆市渝中区\",\"name\":\"盛国祥\"}");
//		resp.getWriter().write("重庆");
		resp.getWriter().flush();
	}
}

