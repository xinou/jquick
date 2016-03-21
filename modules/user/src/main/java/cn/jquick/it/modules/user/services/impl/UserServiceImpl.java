package cn.jquick.it.modules.user.services.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.jquick.it.common.model.user.req.FindUserReq;
import cn.jquick.it.common.model.user.resp.User;
import cn.jquick.it.common.model.user.table.TUser;
import cn.jquick.it.modules.user.dao.IUserDao;
import cn.jquick.it.modules.user.services.IUserService;

/** 
 * 用户管理实现类
 * <功能详细描述> 
 * 
 * @author  ouxin 
 * @version  [版本号, 2016年3月20日] 
 */
@Named("userServiceImpl")
public class UserServiceImpl implements IUserService
{
    @Inject
    private IUserDao userDao;
    
    @Override
    public User login(FindUserReq req)
        throws Exception
    {
        User user = null;
        Subject subject = SecurityUtils.getSubject();
        //用户已登录,缓存中获取用户信息返回
        if (null != subject && subject.isAuthenticated())
        {
            user = (User)subject.getPrincipal();
        }
        else
        {
            UsernamePasswordToken token = new UsernamePasswordToken(req.getUserName(), req.getUserPwd());
            token.setRememberMe(true);
            subject.login(token);
        }
        return user;
    }
    
    @Override
    public User findUser(FindUserReq req)
        throws Exception
    {
        return userDao.findUser(req);
    }
    
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void insertUser()
        throws Exception
    {
        PageHelper.startPage(1, 2);
        TUser tUser = new TUser();
        List<TUser> list = userDao.insertUser(tUser);
        PageInfo pageInfo = new PageInfo(list);
        System.err.println(pageInfo.getTotal());
    }
}
