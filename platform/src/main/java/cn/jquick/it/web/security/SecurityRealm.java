package cn.jquick.it.web.security;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import cn.jquick.it.common.model.user.req.FindUserReq;
import cn.jquick.it.common.model.user.resp.User;
import cn.jquick.it.modules.user.services.IUserService;

/** 
 * 用户身份验证,授权 Realm 组件
 * <功能详细描述> 
 * 
 * @author  ouxin 
 * @version  [版本号, 2016年3月17日] 
 */
@Named("securityRealm")
public class SecurityRealm extends AuthorizingRealm
{
    
    private static final Log log = LogFactory.getLog(SecurityRealm.class);
    
    @Inject
    private IUserService userService;
    
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
    {
        return null;
    }
    
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
        throws AuthenticationException
    {
        //用户名
        String userName = String.valueOf(token.getPrincipal());
        //密码
        String userPwd = String.valueOf((char[])token.getCredentials());
        User user = null;
        try
        {
            user = userService.findUser(new FindUserReq(userName, userPwd));
        }
        catch (Exception e)
        {
            log.error("用户登录失败");
        }
        if (null == user)
        {
            throw new AuthenticationException("用户名或密码错误.");
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, userPwd, getName());
        return authenticationInfo;
    }
    
}
