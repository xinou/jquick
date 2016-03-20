package cn.jquick.it.common.model.user.req;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * 查询用户请求实体
 * <功能详细描述> 
 * 
 * @author  ouxin 
 * @version  [版本号, 2016年3月20日] 
 */
public class FindUserReq implements Serializable
{
    private static final long serialVersionUID = -6446455533382404973L;
    
    /**
     * 用户名
     */
    private String userName;
    
    /**
     * 密码
     */
    @NotBlank
    private String userPwd;
    
    public FindUserReq()
    {
    }
    
    public FindUserReq(String userName, String userPwd)
    {
        this.userName = userName;
        this.userPwd = userPwd;
    }
    
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
