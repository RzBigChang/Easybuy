package com.web.pre;

import com.entity.EasybuyUser;
import com.entity.EasybuyUserAddress;
import com.service.address.Impl.EasybuyUserAddressServiceImpl;
import com.service.order.Impl.OderServiceImpl;
import com.service.user.EasybuyUserService;
import com.service.user.impl.EasybuyUserServiceImpl;
import com.utils.CodeUtil;
import com.utils.EmptyUtils;
import com.utils.Pager;
import com.utils.ReturnResult;
import com.utils.SecurityUtils;
import com.web.AbstractServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户信息控制层！ Servlet implementation class EasybuyUserServlet
 */
@WebServlet("/EasybuyUserServlet")
public class EasybuyUserServlet extends AbstractServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public EasybuyUserServlet() {
		// TODO Auto-generated constructor stub
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

//	/**
//	 * 登录
//	 * */
//	@SuppressWarnings("unused")
//	public ReturnResult loginBtn(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		//根据用户名and密码查询账户状态
//		String names=request.getParameter("name");
//		String pwds=request.getParameter("pwd");
//		String password = SecurityUtils.md5Hex(pwds);
//		EasybuyUserService userService= new EasybuyUserServiceImpl();
//		EasybuyUser user = userService.SelectNameandPwd(names, password);
//		ReturnResult result = new ReturnResult();
//		if(user.getActivated()==1) {
//			EasybuyUserService easybuyuserservice = new EasybuyUserServiceImpl();
//			
//			String name = request.getParameter("name");
//			//加密密碼！！！！！！
//			EasybuyUser easybuyUserLogin = easybuyuserservice.findEasybuyUserInfo(name, password);
//			
//			// 判断用户是否注册过
//			if (easybuyUserLogin != null) {
//				request.getSession().setAttribute("easybuyUserLogin", easybuyUserLogin);
//				return result.returnSuccess("登陆成功 快去邮箱激活账户吧!");
//			} else {
//				return result.returnFail("用户或密码输入有误");
//			}
//		}else {
//			return result.returnFail("登录失败 先去激活账户吧!");
//		}
//		
//		
//	}
//	

	/**
	 * 注册
	 * */
	public ReturnResult addRegister(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ReturnResult result=new ReturnResult();
		String name = request.getParameter("name");
		String password = SecurityUtils.md5Hex(request.getParameter("password"));
		String numName = request.getParameter("numName");
		int sex = Integer.parseInt(request.getParameter("sex"));
		String mem = request.getParameter("mem");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		
		EasybuyUser easybuyuser = new EasybuyUser();
		
		easybuyuser.setLoginName(name);
		easybuyuser.setPassword(password);
		easybuyuser.setUserName(numName);
		easybuyuser.setSex(sex);
		easybuyuser.setIdentityCode(mem);
		easybuyuser.setEmail(email);
		easybuyuser.setMobile(phone);
		easybuyuser.setType(0);
		//刚注册账户状态默认为0
		easybuyuser.setActivated(0); //1激活0未激活
		String codeUrl=CodeUtil.generateUniqueCode()+CodeUtil.generateUniqueCode();
		easybuyuser.setCodeUrl(codeUrl);
		
		EasybuyUserService easybuyuserservice = new EasybuyUserServiceImpl();
		int add = easybuyuserservice.addRegisterInfo(easybuyuser);
		if(add==1) {
			return result.returnSuccess("注册成功 快去邮箱查看激活邮件吧！");
		}else {
			return result.returnFail("注册失败了哦 请联系管理员~.~");
		}
	}
//	/*
//	 * 根据随机码 查询 修改账户状态
//	 * */
//	public void ActiveServlet(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		//接受激活码
//		String codeUrl=request.getParameter("codeUrl");
//		//根据激活码查询用户
//		EasybuyUserService userService= new EasybuyUserServiceImpl();
//		EasybuyUser user = userService.SelectId(codeUrl);
//		//已经查询到，修改用户的状态
//		if(user!=null) {
//			//已经查询到了,修改用户的状态
//			user.setActivated(1);;
//			user.setCodeUrl(null);
//			userService.XiuGaiDome(user);
//			request.setAttribute("msg", "激活成功辣，快去登录吧！");
//			request.getRequestDispatcher("/msg.jsp").forward(request, response);
//			
//		}else {
//			//根据激活码没有查询到该用户
//			request.setAttribute("msg", "你的激活码有误，请重新激活");
//			request.getRequestDispatcher("/msg.jsp").forward(request, response);
//		}
//		
//	}
	/**
	 * 
	 * 查看用户名是否重复
	 * */
	public ReturnResult loginNameCount(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ReturnResult result = new ReturnResult();
		// 获取用户昵称！
		String name = request.getParameter("name");
		EasybuyUserService easybuyuserservice = new EasybuyUserServiceImpl();
		int count= easybuyuserservice.findLoginNameByName(name);
		
		
		// 判断是否注册成功！
		if (count>0) {
			return result.returnFail("该用户名已经存在");
		} else {
			return result.returnSuccess("用户名可用");
		}
	}
	
	/**
	 * 注销
	 * */
	public String remove(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession().invalidate();
		return "pre/Login";
	}
	/*
	 * 加载用户列表
	 * */
	public String user(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//获取当前页数
		String currentPageStr=request.getParameter("currentPage");
		//获取页大小
		String pageSize=request.getParameter("pageSize");
		int rowPerPage = EmptyUtils.isEmpty(pageSize) ? 10 : Integer.parseInt(pageSize);
		int currentPage = EmptyUtils.isEmpty(currentPageStr) ? 1 : Integer.parseInt(currentPageStr);
		//获取总页数
		int count=new EasybuyUserServiceImpl().getTotalCount();
		Pager pager=new Pager(count, rowPerPage, currentPage);
		if (pager.getPageCount() < pager.getCurrentPage()) {
			pager.setCurrentPage(currentPage - 1);
		}
		pager.setUrl("/EasybuyUserServlet?action=user");
		request.setAttribute("pager", pager);
		request.setAttribute("menu", 8);
		//调用三层
		List<EasybuyUser>list=new EasybuyUserServiceImpl().findEasybuyUserAll(pager);
		request.setAttribute("list", list);
		return"backend/user/Member_Packet";
	}
	/*
	 * 用户详情 看不懂
	 * */
	public String index(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//封装成对象
		EasybuyUser easyuyUser=new EasybuyUser();
		request.setAttribute("user",easyuyUser);
		request.setAttribute("menu", 2);
		return"backend/user/Member_User";
	}
	/*
	 * 点击新增用户  看不懂
	 * */
	public String add(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//封装成对象
		EasybuyUser easyuyUser=new EasybuyUser();
		request.setAttribute("user",easyuyUser);
		return"backend/user/toUpdateUser";
	}
	/*
	 * 添加用户信息/修改用户信息
	 * */
	public ReturnResult modify(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ReturnResult result=new ReturnResult();
		//封装成对象
		EasybuyUser easybuyUser=new EasybuyUser();
		//获取用户需要更新的数据
		easybuyUser.setId(EmptyUtils.isEmpty(request.getParameter("thisId")) ? 0 :Integer.parseInt(request.getParameter("thisId")));
		easybuyUser.setLoginName(request.getParameter("name"));
		if(easybuyUser.getId()==0) {
			//调用查询用户名是否相同方法 并赋给count
			int count=new EasybuyUserServiceImpl().findLoginNameByName(easybuyUser.getLoginName());
			//判断count是否有值 有值则给出用户名不能相同提示  
			if(count==1) {
				result.returnFail("用户名已存在");
			}else {
				easybuyUser.setUserName(request.getParameter("userName"));
				easybuyUser.setPassword(SecurityUtils.md5Hex(request.getParameter("password")));
				easybuyUser.setSex(Integer.parseInt(request.getParameter("sex")));
				easybuyUser.setIdentityCode(request.getParameter("identityCode"));
				easybuyUser.setEmail(request.getParameter("email"));
				easybuyUser.setMobile(request.getParameter("mobile"));
				easybuyUser.setType(Integer.parseInt(request.getParameter("user")));
				easybuyUser.setActivated(0); //1激活0未激活
				String codeUrl=CodeUtil.generateUniqueCode()+CodeUtil.generateUniqueCode();
				easybuyUser.setCodeUrl(codeUrl);
				//调用三层
				int modify=new EasybuyUserServiceImpl().addRegisterInfo(easybuyUser);
				//判断是否添加成功
				if(modify==1) {
					result.returnSuccess("添加用户信息成功 快去邮箱激活账户吧");
				}else {
					result.returnFail("添加用户信息失败");
				}
			}
		}else {
			easybuyUser.setUserName(request.getParameter("userName"));
			easybuyUser.setSex(Integer.parseInt(request.getParameter("sex")));
			easybuyUser.setIdentityCode(request.getParameter("identityCode"));
			easybuyUser.setEmail(request.getParameter("email"));
			easybuyUser.setMobile(request.getParameter("mobile"));
			easybuyUser.setType(Integer.parseInt(request.getParameter("user")));
			//调用三层
			int modify=new EasybuyUserServiceImpl().modifyEasybuyUserById(easybuyUser);
			//判断是否修改成功
			if(modify>0) {
				result.returnSuccess("修改用户信息成功");
			}else {
				result.returnFail("修改用户信息失败");
			}
		}
		return result;
	}
	/*
	 * 传递参数到修改页面
	 * */
	public String toUpdateUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//获取用户ID
		String id=request.getParameter("id");
		String currentPage=request.getParameter("currentPage");
		//调用三层
		EasybuyUser user=new EasybuyUserServiceImpl().findEasybuyUserById(Integer.parseInt(id));
		request.setAttribute("user",user);
		request.setAttribute("currentPage",currentPage);
		return"/backend/user/toUpdateUser";
	}
	/*
	 * 添加用户成功后跳转页面
	 * */
	public String adduser(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//获取当前页数
		String currentPageStr=request.getParameter("currentPage");
		//获取页大小
		String pageSize=request.getParameter("pageSize");
		int rowPerPage = EmptyUtils.isEmpty(pageSize) ? 10 : Integer.parseInt(pageSize);
		int currentPage = EmptyUtils.isEmpty(currentPageStr) ? 1 : Integer.parseInt(currentPageStr);
		//获取总页数
		int count=new EasybuyUserServiceImpl().getTotalCount();
		Pager pager=null;
		int pagerCount=(count% rowPerPage==0)?(count/rowPerPage):(count/rowPerPage)+1;
		//获取ID
		String sid=request.getParameter("thisId");
		int id=EmptyUtils.isEmpty(sid) ? 0 : Integer.parseInt(request.getParameter(sid));
		if(id==0) {
			pager=new Pager(count, rowPerPage, pagerCount);
		}else {
			pager=new Pager(count, rowPerPage, currentPage);
		}
		pager.setUrl("/EasybuyUserServlet?action=user");
		//放置对象
		request.setAttribute("pager", pager);
		request.setAttribute("menu", 8);
		//调用三层
		List<EasybuyUser>list=new EasybuyUserServiceImpl().findEasybuyUserAll(pager);
		request.setAttribute("list",list);
		return"/backend/user/Member_Packet";
	}
	/*
	 * 删除
	 * */
	public ReturnResult del(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ReturnResult result=new ReturnResult();
		//获取到要删除的用户Id
		int id=Integer.parseInt(request.getParameter("id"));
		//执行删除时 先判断用户是否有订单
		int find=new OderServiceImpl().getUserByIdOrder(id);
		//执行删除时 先判断用户是否有地址
		int count=new EasybuyUserAddressServiceImpl().getUserByIdAddress(id);
		//声明变量
		int del=-1;
		//判断外键是否有数据
		if(find==0) {
			if(count==0) {
				//调用三层
				del=new EasybuyUserServiceImpl().delEasybuyUserById(id);
				if(del>0) {
					result.returnSuccess("删除成功");
				}
			}else {
				//删除失败
				result.returnFail("该用户下有地址 无法删除");
			}
		}else {
			//删除失败
			result.returnFail("该用户下有订单,无法删除");
		}
		return result;
	}
	@Override
	public Class getServletClass() {
		return EasybuyUserServlet.class;
	}

}
