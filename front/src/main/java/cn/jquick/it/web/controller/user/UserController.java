package cn.jquick.it.web.controller.user;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jquick.it.common.model.user.table.TUser;

/** 
 * 用户管理控制器
 * <功能详细描述> 
 * 
 * @author  ouxin 
 * @version  [版本号, 2016年3月13日] 
 */
@Controller
@RequestMapping("/user")
public class UserController
{
    /** 
     *跳转登录页面 
     *<功能详细描述>
     * @return 
     */
    //@RequestMapping("/login.html")
    public String showLogin()
    {
        System.err.println("login.html");
        return "user/login";
    }
    
    /** 
     *用户登录 
     *<功能详细描述>
     * @return 
     */
    @Produces(MediaType.APPLICATION_JSON)
    @ResponseBody
    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public Map<String, String> login()
    {
        System.err.println("login.do");
        Map<String, String> map = new HashMap<String,String>();
        map.put("a", "11111");
        return map;
    }
    
    /** 
     *跳转注册页面 
     *<功能详细描述>
     * @return 
     */
    @RequestMapping("/register.html")
    public String showRegister()
    {
        return "user/register";
    }
}
