package cn.jquick.it.modules.user.services;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import cn.jquick.it.common.model.user.req.FindUserReq;
import cn.jquick.it.common.model.user.resp.User;

/** 
 * 用户管理接口
 * <功能详细描述> 
 * 
 * @author  ouxin 
 * @version  [版本号, 2016年3月20日] 
 */
public interface IUserService
{
    /** 
     *查询用户信息 
     *<功能详细描述>
     * @param req
     * @return
     * @throws Exception 
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/find")
    public User findUser(FindUserReq req)
        throws Exception;
        
    /** 
     *新增用户 
     *<功能详细描述>
     * @throws Exception 
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/add")
    public void insertUser()
        throws Exception;
}
