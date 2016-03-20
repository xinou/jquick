package cn.jquick.it.web.controller.user;

import javax.validation.Valid;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jquick.it.common.model.user.req.FindUserReq;

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
    public String login(@RequestBody @Valid FindUserReq req, BindingResult result)
    {
        Subject subject = SecurityUtils.getSubject();
        //已经登录,跳转到首页
        if(null != subject && subject.isAuthenticated()){
            return "user/register";
        }
        UsernamePasswordToken token = new UsernamePasswordToken(req.getUserName(), req.getUserPwd());
        token.setRememberMe(true);
        subject.login(token);
        return null;
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
