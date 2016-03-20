package cn.jquick.it.modules.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.jquick.it.common.model.user.req.FindUserReq;
import cn.jquick.it.common.model.user.resp.User;
import cn.jquick.it.common.model.user.table.TUser;

/** 
 * 用户管理Dao接口
 * <功能详细描述> 
 * 
 * @author  ouxin 
 * @version  [版本号, 2016年3月20日] 
 */
public interface IUserDao
{
    /** 
     *查询用户信息
     *<功能详细描述>
     * @param req
     * @return 
     */
    public User findUser(@Param("user") FindUserReq req);
    
    public List<TUser> insertUser(TUser tUser);
}
