package com.techthinker.CAWeb.dao;

import org.springframework.stereotype.Repository;

import com.techthinker.CAWeb.idao.IUserDao;
import com.techthinker.CAWeb.persistence.User;

@Repository("userDao")
public class UserDao extends BaseDao<User> implements IUserDao {

}
