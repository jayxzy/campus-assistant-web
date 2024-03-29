package com.techthinker.CAWeb.dao;


import org.springframework.stereotype.Repository;

import com.techthinker.CAWeb.idao.IUsertypeDao;
import com.techthinker.CAWeb.persistence.Usertype;

@Repository("usertypeDao")
public class UsertypeDao extends BaseDao<Usertype> implements IUsertypeDao {

}
