package cn.jquick.it.framework.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/** 
 * Http返回值处理拦截器
 * <功能详细描述> 
 * 
 * @author  ouxin 
 * @version  [版本号, 2016年3月19日] 
 */
public class HttpResponseInterceptor extends HandlerInterceptorAdapter
{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception
    {
        System.err.println("preHandle");
        return true;
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView)
            throws Exception
    {
        System.err.println(response.getWriter());
        System.err.println("postHandle");
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
        throws Exception
    {
        System.err.println("afterCompletion");
    }
    
    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception
    {
        System.err.println("afterConcurrentHandlingStarted");
    }
}
