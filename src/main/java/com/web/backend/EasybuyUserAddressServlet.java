package com.web.backend;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.entity.EasybuyUserAddress;
import com.service.address.Impl.EasybuyUserAddressServiceImpl;
import com.web.AbstractServlet;

/**
 * 地址信息控制类！
 * Servlet implementation class EasybuyUserAddressServlet
 */
@WebServlet("/EasybuyUserAddressServlet")
public class EasybuyUserAddressServlet extends AbstractServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EasybuyUserAddressServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 获取地址信息|！
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String address(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取登陆用户信息ID！
		// 调用三层信息！
		List<EasybuyUserAddress> easybuyUserAddress = new EasybuyUserAddressServiceImpl().getEasybuyUserAddressAll(10);
		// 把该对象存储在内置对象中！
		request.setAttribute("easybuyUserAddress", easybuyUserAddress);
		return "backend/user/Member_Address";
	}

	@Override
	public Class getServletClass() {
		// TODO Auto-generated method stub
		return EasybuyUserAddressServlet.class;
	}

}
