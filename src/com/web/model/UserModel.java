package com.web.model;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import com.web.entity.Menu;
import com.web.entity.Role;
import com.web.entity.User;
import com.web.util.Page;
import com.web.vo.MenuVo;

public interface UserModel {
	/**
	 * 通过用户加载一个用户对象(登录)
	 * @param userName 用户输入的帐号
	 * @return	表示用户的帐号不存在
	 */
	public User loadUserByuserName(String userName);
	/**
	 * 查看所有用户
	 * @return
	 */
	public List<User> loadAllUsers();
	/**
	 * 查看所有班级
	 * @return
	 */
	public List<Object[]> loadAllClass();
	/**
	 * 查看所有学生
	 * @return
	 */
	public List<Object[]> loadAllStudent();
	/**
	 * 删除某一个用户
	 * @param userName
	 */
	public void deleteUser(String userName);
	/**
	 * 查看某一个用户
	 * @param userName
	 */
	public void showUser(String userName);
	
	/**
	 * 添加学生
	 */
	public void addStudent(String sname, String cname);
	/**
	 * 添加班级
	 */
	public void addClass(String cname);
	/**
	 * 权限管理
	 */
	public List<Menu> loadMenuByUid(int uid);
	/**
	 * 加载所有菜单
	 * @return
	 */
	public Page<MenuVo> loadAllMenus(int pageNo,int pageSize);
	
	/**
	 * 加载所有一级和二级菜单
	 * @return
	 */
	public List<Menu> load12Menus();
	
	/**
	 * 添加菜单
	 * @param name 菜单名称
	 * @param url  菜单地址
	 * @param isshow 是否在欢迎界面展示
	 * @param parentid 父级菜单ID
	 */
	public int addMenu(String mname,String url,int isshow,int parentid);
	
	/**
	 * 加载所有角色
	 * @return
	 */
	public List<Role> loadAllRoles();
	
	/**
	 * 通过角色id查询角色菜单
	 * @param rid
	 * @return
	 */
	public List<Object[]> loadRoleMenuByRoleId(int rid);
	
	/**
	 * 通过角色id加载角色对象
	 * @param rid
	 * @return
	 */
	public Role loadRoleById(int rid);
	
	/**
	 * 编辑角色权限
	 * @param rid
	 * @param mids
	 */
	public void editRoleMenu(int rid, String[] mids);
	
	/**
	 * 检查uid这个用户是否拥有uri这个菜单权限
	 * @param uid
	 * @param uri
	 * @return 返回true表示有权限 返回false表示无权限
	 */
	public boolean checkUserMenu(int uid, String uri);
	
	/**
	 * 通过角色的rid 去找到他对应的所拥有的用户 就是那些用户是这个角色
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public List<Object[]> showUserbyRole_Rid(int rid);
	
	/**
	 * 执行修改角色拥有的用户
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void executeAlterUser(int roleRid, int[] usersSid);
	
	
}
