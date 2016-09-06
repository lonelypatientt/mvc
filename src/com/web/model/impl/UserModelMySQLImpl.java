package com.web.model.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import com.web.dao.UserDao;
import com.web.dao.impl.UserDaoImpl;
import com.web.entity.Menu;
import com.web.entity.Role;
import com.web.entity.User;
import com.web.model.UserModel;
import com.web.util.Page;
import com.web.vo.MenuVo;

public class UserModelMySQLImpl implements UserModel{
	
	//模型层有一个DAO层对象
	private UserDao userDao = new UserDaoImpl();
	/**
	 * 通过用户加载一个用户对象(登录)
	 * @param userName 用户输入的帐号
	 * @return	表示用户的帐号不存在
	 */
	public User loadUserByuserName(String userName){
		System.out.println("模型层");
		User user = userDao.loadUserByuserName(userName);
		return user;
	}
	/**
	 * 查看所有用户
	 */
	public List<User> loadAllUsers(){
		return userDao.loadAllUsers();
	}
	/**
	 * 查看所有班级
	 */
	public List<Object[]> loadAllClass(){
		return userDao.loadAllClass();
	}
	/**
	 * 查看所有学生
	 * @return
	 */
	public List<Object[]> loadAllStudent(){
		return userDao.loadAllStudent();
	}
	/**
	 * 删除某一用户
	 * @param userName
	 */
	public void deleteUser(String userName) {
		userDao.deleteUser(userName);
	}
	/**
	 * 查看某一个用户
	 */
	public void showUser(String userName){
		userDao.showUser(userName);
	}
	/**
	 * 添加学生
	 */
	public void addStudent(String sname, String cname){
		userDao.addStudent(sname, cname);
	}
	/**
	 * 添加班级
	 */
	public void addClass(String cname){
		userDao.addClass(cname);
	}
	/**
	 * 权限管理
	 */
	public List<Menu> loadMenuByUid(int uid){
		return userDao.loadMenuByUid(uid);
	}
	
	/**
	 * 加载所有菜单
	 * @return
	 */
	public Page<MenuVo> loadAllMenus(int pageNo,int pageSize){
		return userDao.loadAllMenus(pageNo,pageSize);
	}
	
	/**
	 * 加载所有一级和二级菜单
	 * @return
	 */
	public List<Menu> load12Menus(){
		return userDao.load12Menus();
	}
	
	/**
	 * 添加菜单
	 * @param name 菜单名称
	 * @param url  菜单地址
	 * @param isshow 是否在欢迎界面展示
	 * @param parentid 父级菜单ID
	 * @return 返回1表示添加成功 返回2表示父级菜单不存在
	 */
	public int addMenu(String mname,String url,int isshow,int parentid){
		Menu m = userDao.loadMenuById(parentid);
		if(null != m){
			userDao.addMenu(mname, url, isshow, m.getLevel()+1, parentid);
			return 1;
		}else{
			return 2;
		}
	}
	public boolean checkUserMenu() {
		return false;
	}
	
	/**
	 * 加载所有角色
	 * @return
	 */
	public List<Role> loadAllRoles(){
		return userDao.loadAllRoles();
	}
	
	/**
	 * 通过角色id查询角色菜单
	 * @param rid
	 * @return
	 */
	public List<Object[]> loadRoleMenuByRoleId(int rid){
		return userDao.loadRoleMenuByRoleId(rid);
	}
	
	/**
	 * 通过角色id加载角色对象
	 * @param rid
	 * @return
	 */
	public Role loadRoleById(int rid){
		return userDao.loadRoleById(rid);
	}
	
	/**
	 * 编辑角色权限
	 * @param rid
	 * @param mids
	 */
	public void editRoleMenu(int rid, String[] mids){
		userDao.editRoleMenu(rid, mids);
	}
	
	/**
	 * 检查uid这个用户是否拥有uri这个菜单权限
	 * @param uid
	 * @param uri
	 * @return 返回true表示有权限 返回false表示无权限
	 */
	public boolean checkUserMenu(int uid, String uri){
		return userDao.checkUserMenu(uid, uri);
	}
	/**
	 * 通过角色的rid 去找到他对应的所拥有的用户 就是那些用户是这个角色
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public List<Object[]> showUserbyRole_Rid(int rid){
		return userDao.showUserbyRole_Rid(rid);
		
	}
	
	/**
	 * 执行修改角色拥有的用户
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void executeAlterUser(int roleRid, int[] usersSid){
		userDao.executeAlterUser(roleRid, usersSid);
	}
	
}
