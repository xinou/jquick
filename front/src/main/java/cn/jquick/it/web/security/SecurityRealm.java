package cn.jquick.it.web.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.jquick.it.common.model.user.req.FindUserReq;
import cn.jquick.it.framework.utils.HttpClientUtil;

/** 
 * 用户身份验证,授权 Realm 组件
 * <功能详细描述> 
 * 
 * @author  ouxin 
 * @version  [版本号, 2016年3月17日] 
 */
public class SecurityRealm extends AuthorizingRealm
{
    
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
    {
        System.err.println("111111");
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
        String json = HttpClientUtil.doHttpRequest("/user/login", new FindUserReq(userName, userPwd));
        JSONObject jsonObj = JSON.parseObject(json);
        if (null != jsonObj)
        {
            throw new AuthenticationException("用户名或密码错误.");
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, userPwd, getName());
        return authenticationInfo;
    }
}
