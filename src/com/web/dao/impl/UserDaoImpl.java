package com.web.dao.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.web.dao.UserDao;
import com.web.entity.Menu;
import com.web.entity.Role;
import com.web.entity.User;
import com.web.util.DBUtil;
import com.web.util.Page;
import com.web.vo.MenuVo;

public class UserDaoImpl implements UserDao{

	/**
	 * 通过用户加载一个用户对象(登录)
	 * @param userName 用户输入的帐号
	 * @return	表示用户的帐号不存在
	 */
	public User loadUserByuserName(String userName){
		System.out.println("数据层");
		String sql = "select * from login where userName=?";
		List<Object[]> list = DBUtil.executeQuery(sql, new Object[]{userName});
		User user = null;
		if(list != null && list.size() > 0){
			Object[] os =list.get(0);
			user = new User((Integer)os[0],userName,String.valueOf(os[2]));
		}
		return user;
	}
	/**
	 * 查询所有用户信息
	 */
	public List<User> loadAllUsers() {
		String sql = "select * from login";
		List<Object[]> list = DBUtil.executeQuery(sql,null);
		List<User> userList = new ArrayList<User>();
		User user = null;
		if(list != null && list.size() > 0){
			for(Object[] os : list){
				user = new User((Integer)os[0],String.valueOf(os[1]),String.valueOf(os[2]));
				userList.add(user);
			}
		}
		return userList;
	}
	/**
	 * 查询所有班级列表
	 * @return
	 */
	public List<Object[]> loadAllClass() {
		String sql = "select * from class order by sj desc ";
		List<Object[]>list = DBUtil.executeQuery(sql, null);
		return list;
	}
	/**
	 * 删除某一个用户
	 */
	public void deleteUser(String userName) {
		String sql = "delete from user where userName = ?";
		Object[]m={userName};
		DBUtil.executeDML(sql, m);
	}

	/**
	 * 查看某一个用户
	 */
	public void showUser(String userName) {
		String sql = "select * from user where userName = ?";
		Object[]m={userName};
		List<Object []> list = DBUtil.executeQuery(sql, m);
	}
	/**
	 * 查询所有学生信息
	 * @return
	 */
	public List<Object[]> loadAllStudent() {
		String sql = "select * from student order by sj desc";
		List<Object[]>list = DBUtil.executeQuery(sql, null);
		return list;
	}
	
	/**
	 * 添加学生
	 */
	public void addStudent(String sname, String cname) {
		Date date = new Date();
		String sql = "select * from class where cid =?";
		List<Object []> list = DBUtil.executeQuery(sql, new Object[]{cname});
		Object [] o = list.get(0);
		sql = "insert into student(sno,sname,sex,sj,cid) values (?,?,?,?,?)";
		Object[] os ={sname,date,o[0]};
		DBUtil.executeDML(sql, os);
	}
	
	/**
	 * 添加班级
	 */
	public void addClass(String cname){
		Date date = new Date();
		Object[]m={cname,date};
		String sql = "insert into class(cname,sj) values (?,?)";
		DBUtil.executeDML(sql, m);
		
	}
	/**
	 * 权限管理菜单 
	 */
	public List<Menu> loadMenuByUid(int uid) {
		String sql = "select m.* from loginrole lr,role r,rolemenu rm,menu m where m.mid = rm.mid and rm.rid = r.rid and r.rid = lr.rid and lr.uid =?";
		List<Object[]> list = DBUtil.executeQuery(sql,new Object[]{uid});
		List<Menu> menuList = new ArrayList<Menu>();
		Menu m = null;
		if(list != null && list.size() > 0){
			for(Object[] os : list){
				m = new Menu((Integer)os[0], String.valueOf(os[1]), String.valueOf(os[2]), (Integer)os[3], (Integer)os[4], (Integer)os[5]);
				menuList.add(m);
			}
		}
		return menuList;
	}
	/**
	 * 加载所有菜单
	 * @return
	 */
	public Page<MenuVo> loadAllMenus(int pageNo,int pageSize){
		String sql = "select m.mid,m.mname,m.url,m.isshow,m.level,(select m2.mname from menu m2 where m2.mid=m.parentid) from menu m limit ?,?";
		List<Object[]> list = DBUtil.executeQuery(sql, new Object[]{(pageNo-1)*pageSize,pageSize});
		List<MenuVo> menuList = new ArrayList<MenuVo>();
		if(null != list && list.size() > 0){
			for(Object[] os : list){
				MenuVo menuvo = new MenuVo((Integer)os[0], String.valueOf(os[1]), String.valueOf(os[2]), (Integer)os[3], (Integer)os[4], String.valueOf(os[5]));
				menuList.add(menuvo);
			}
		}
		sql = "select count(*) from menu";
		list = DBUtil.executeQuery(sql, null);
		long total = (Long)list.get(0)[0];
		return new Page<MenuVo>(pageNo, pageSize, menuList, total);
	}
	
	/**
	 * 加载所有一级和二级菜单
	 * @return
	 */
	public List<Menu> load12Menus(){
		String sql = "select m.* from menu m where m.level in(1,2)";
		List<Object[]> list = DBUtil.executeQuery(sql, null);
		List<Menu> menuList = new ArrayList<Menu>();
		Menu m = null;
		if(null != list && list.size() > 0){
			for(Object[] os : list){
				m = new Menu((Integer)os[0], String.valueOf(os[1]), String.valueOf(os[2]), (Integer)os[3], (Integer)os[4], (Integer)os[5]);
				menuList.add(m);
			}
		}
		return menuList;
	}
	
	/**
	 * 通过主键id加载一个菜单对象
	 * @param mid
	 * @return
	 */
	public Menu loadMenuById(int mid){
		String sql = "select * from menu where mid=?";
		List<Object[]> list = DBUtil.executeQuery(sql, new Object[]{mid});
		Menu m = null;
		if(null != list && list.size() > 0){
			Object[] os = list.get(0);
			m = new Menu((Integer)os[0], String.valueOf(os[1]), String.valueOf(os[2]), (Integer)os[3], (Integer)os[4], (Integer)os[5]);
		}
		return m;
	}
	
	/**
	 * 添加菜单
	 * @param name 菜单名称
	 * @param url  菜单地址
	 * @param isshow 是否在欢迎界面展示
	 * @param level 当前添加的菜单的级别
	 * @param parentid 父级菜单ID
	 */
	public void addMenu(String mname,String url,int isshow,int level,int parentid){
		String sql = "insert into menu(mname,url,isshow,level,parentid) values(?,?,?,?,?)";
		DBUtil.executeDML(sql, new Object[]{mname,url,isshow,level,parentid});
	}
	
	/**
	 * 加载所有角色
	 * @return
	 */
	public List<Role> loadAllRoles(){
		String sql = "select * from role";
		List<Object[]> list = DBUtil.executeQuery(sql, null);
		List<Role> roleList = new ArrayList<Role>();
		Role r = null;
		if(null != list && list.size() > 0){
			for(Object[] os : list){
				r = new Role((Integer)os[0], String.valueOf(os[1]));
				roleList.add(r);
			}
		}
		return roleList;
	}
	
	/**
	 * 通过角色id查询角色菜单
	 * @param rid
	 * @return
	 */
	public List<Object[]> loadRoleMenuByRoleId(int rid){
		String sql = "select m.mid,m.mname,m.parentid,(select 1 from rolemenu rm where rm.mid=m.mid and rm.rid=?) from menu m";
		return DBUtil.executeQuery(sql, new Object[]{rid});
	}
	
	/**
	 * 通过角色id加载角色对象
	 * @param rid
	 * @return
	 */
	public Role loadRoleById(int rid){
		String sql = "select * from role where rid=?";
		List<Object[]> list = DBUtil.executeQuery(sql, new Object[]{rid});
		Role r = null;
		if(null != list && list.size() > 0){
			Object[] os = list.get(0);
			r = new Role((Integer)os[0], String.valueOf(os[1]));
		}
		return r;
	}
	
	
	/**
	 * 编辑角色权限
	 * @param rid
	 * @param mids
	 */
	public void editRoleMenu(int rid, String[] mids){
		/*
		 * 先删除原来已有的菜单关联
		 */
		String sql = "delete from rolemenu where rid=?";
		DBUtil.executeDML(sql, new Object[]{rid});
		
		/*
		 * 再重新添加
		 */
		String sql2 = "insert into rolemenu(rid,mid) values(?,?)";
		if(null != mids && mids.length > 0){
			for(String mid : mids){
				DBUtil.executeDML(sql2, new Object[]{rid, Integer.valueOf(mid)});
			}
		}
	}
	
	/**
	 * 检查uid这个用户是否拥有uri这个菜单权限
	 * @param uid
	 * @param uri
	 * @return 返回true表示有权限 返回false表示无权限
	 */
	public boolean checkUserMenu(int uid, String uri){
		String sql = "select 1 from loginrole lr,rolemenu rm,menu m where lr.rid=rm.rid and rm.mid=m.mid and lr.uid=? and m.url like '%" + uri + "%'";
		List<Object[]> list = DBUtil.executeQuery(sql, new Object[]{uid});
		if(null != list && list.size() > 0){
			return true;
		}
		return false;
	}
	
	/**
	 * 通过角色去查询用户，就是是当前这个角色的所用用户，通过rid来擦
	 */
	public List<Object[]> showUserbyRole_Rid(int rid){
		String sql ="select l.uid,l.userName,(select 1 from loginrole lr  where lr.uid = l.uid and lr.rid = ?) from login l";
		List<Object[]>userList = DBUtil.executeQuery(sql, new Object[]{rid});
		return userList;	
	}
	
	/**
	 * 执行修改角色所拥有的用户
	 */
	public void executeAlterUser(int roleRid,int [] usersSid){
		//先执行删除原来角色所对应的用户
		String sql = "delete from loginrole where rid = ?";
		DBUtil.executeDML(sql, new Object []{roleRid});
		
		//先执行删除原来用户所对应的角色 那一行全删除
		  sql = "delete from loginrole where uid = ?";
		for(int i = 0 ;i <usersSid.length;i++ ){
			DBUtil.executeDML(sql,new Object[]{usersSid[i]});
		}
		//然后执行将不同的用户赋予给他相同的角色
		sql ="insert into loginrole(uid,rid) values(?,?) ";
		for(int i = 0 ;i <usersSid.length;i++ ){
			DBUtil.executeDML(sql,new Object[]{usersSid[i],roleRid});
		}
	}
	
	
	
	
}
