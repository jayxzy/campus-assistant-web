package com.techthinker.CAWeb.dao;

import org.springframework.stereotype.Repository;

import com.techthinker.CAWeb.idao.IGradeDao;
import com.techthinker.CAWeb.persistence.Grade;

@Repository("gradeDao")
public class GradeDao extends BaseDao<Grade> implements IGradeDao {


}
