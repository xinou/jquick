package cn.jquick.it.modules.user.services.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.jquick.it.common.model.user.table.TUser;
import cn.jquick.it.modules.user.dao.IUserDao;
import cn.jquick.it.modules.user.services.IUserService;

@Named("userServiceImpl")
public class UserServiceImpl implements IUserService
{
    @Inject
    private IUserDao userDao;
    
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
