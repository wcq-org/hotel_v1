package cn.itcast.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.factory.BeanFactory;
import cn.itcast.service.IDinnerTableService;
import cn.itcast.service.IFoodService;
import cn.itcast.service.IFoodTypeService;
import cn.itcast.service.IOrderDetailService;
import cn.itcast.service.IOrdersService;
import cn.itcast.utils.WebUtils;

/**
 * ��Ŀ��ͨ�õ�Servlet��ϣ�����е�servlet���̳д���
 * @author Jie.Yuan
 *
 */
public abstract class BaseServlet extends HttpServlet {
	
	
	// ����Service
	protected IDinnerTableService tableService = BeanFactory.getInstance(
			"dinnerTableService", IDinnerTableService.class);
	protected IFoodTypeService foodTypeService = BeanFactory.getInstance(
			"foodTypeService", IFoodTypeService.class);
	protected IFoodService foodService = BeanFactory.getInstance("foodService",
			IFoodService.class);
	protected IOrdersService ordersService = BeanFactory.getInstance("ordersService",
			IOrdersService.class);
	protected IOrderDetailService orderDetailService = BeanFactory.getInstance("orderDetailService",
			IOrderDetailService.class);

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// (������ת����Դ)  ��������ֵ
		Object returnValue = null;
		
		// ��ȡ��������;  ��Լ�� > �׳ɣ� �������͵�ֵ�������Ӧservlet�еķ������ơ�
		String methodName = request.getParameter("method");  // listTable
		
		try {
			// 1. ��ȡ��ǰ��������ֽ���
			Class clazz = this.getClass();
			// 2. ��ȡ��ǰִ�еķ�����Method����
			Method method = clazz.getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			// 3. ִ�з���
			returnValue = method.invoke(this, request,response);
		} catch (Exception e) {
			e.printStackTrace();
			returnValue = "/error/error.jsp";
		}
		
		// ��ת
		WebUtils.goTo(request, response, returnValue);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
