package cn.jquick.it.common.model.user.resp;

import java.io.Serializable;

/** 
 * 用户信息实体
 * <功能详细描述> 
 * 
 * @author  ouxin 
 * @version  [版本号, 2016年3月20日] 
 */
public class User implements Serializable
{
    private static final long serialVersionUID = 8367172713548600951L;
    
    /**
     * 用户名
     */
    private String userName;
    
    /**
     * 用户密码
     */
    private String userPwd;

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserPwd()
    {
        return userPwd;
    }

    public void setUserPwd(String userPwd)
    {
        this.userPwd = userPwd;
    }
}
