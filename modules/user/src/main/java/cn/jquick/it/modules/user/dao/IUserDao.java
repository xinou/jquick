package cn.jquick.it.modules.user.dao;

import java.util.List;

import cn.jquick.it.common.model.user.table.TUser;

public interface IUserDao
{
    public List<TUser> insertUser(TUser tUser);
}
