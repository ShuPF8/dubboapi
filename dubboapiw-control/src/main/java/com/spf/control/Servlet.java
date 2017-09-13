package com.spf.control;

import com.spf.facede.api.DemoService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Auther SPF
 * @Create 2017/8/8
 */
public class Servlet extends HttpServlet {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    //初始化
    public void init() throws ServletException {
        System.out.println("我是init()方法！用来进行初始化工作");
    }
    //处理GET请求
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("我是doGet()方法！用来处理GET请求");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("<HTML>");
        out.println("<BODY>");
       /*
        * 通过Spring提供的工具类获取ApplicationContext对象
        */
        //ServletContext sc = this.getServletContext(); //和下面一行一样，都能获取ServletContext
        ServletContext sc = request.getSession().getServletContext();
        //第一种获取bean方法，获取失败时抛出异常
        ApplicationContext ac1 = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
        DemoService demoService1 = (DemoService)ac1.getBean("demoService");
        String name1 = demoService1.getName("tom", "Edison");
        out.println(name1);
        out.println("<br>");
        //第二种获取bean方法，获取失败时返回null
        ApplicationContext ac2 = WebApplicationContextUtils.getWebApplicationContext(sc);
        DemoService demoService2 = (DemoService)ac2.getBean("demoService");
        String name2 = demoService2.getName("tom", "Edison");
        out.println(name2);
        out.println("<br>");
        //第三种获取bean方法
        WebApplicationContext wac = (WebApplicationContext)sc.getAttribute(
                WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        DemoService demoService3 = (DemoService)wac.getBean("demoService");
        String name3 = demoService3.getName("tom", "Edison");
        out.println(name3);
        out.println("<br>");
    }
    //处理POST请求
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("我是doPost()方法！用来处理POST请求");
        doGet(request, response);
    }
    //销毁实例
    public void destroy() {
        super.destroy();
        System.out.println("我是destroy()方法！用来进行销毁实例的工作");
    }
}
