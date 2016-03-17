package cn.jquick.it.web.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @RequestMapping("/login")
    public String login()
    {
        System.err.println("111111111111111");
        return "user/login";
    }
    
    @RequestMapping("/register")
    public String showRegister()
    {
        return "user/register";
    }
}
