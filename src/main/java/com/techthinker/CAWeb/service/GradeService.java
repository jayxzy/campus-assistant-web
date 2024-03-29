package com.techthinker.CAWeb.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.techthinker.CAWeb.exception.GradeException;
import com.techthinker.CAWeb.idao.IGradeDao;
import com.techthinker.CAWeb.idao.IMajorDao;
import com.techthinker.CAWeb.idao.ITempIndexDao;
import com.techthinker.CAWeb.iservice.IGradeService;
import com.techthinker.CAWeb.persistence.Grade;
import com.techthinker.CAWeb.persistence.Major;
import com.techthinker.CAWeb.persistence.TempIndex;
import com.techthinker.CAWeb.util.IndexUtil;
import com.techthinker.CAWeb.util.PageObject;

@Service("gradeService")
public class GradeService implements IGradeService {

	private IGradeDao gradeDao;
	private IMajorDao majorDao;
	private ITempIndexDao tempIndexDao;

	/**
	 * @return the tempIndexDao
	 */
	public ITempIndexDao getTempIndexDao() {
		return tempIndexDao;
	}

	/**
	 * @param tempIndexDao
	 *            the tempIndexDao to set
	 */
	@Resource
	public void setTempIndexDao(ITempIndexDao tempIndexDao) {
		this.tempIndexDao = tempIndexDao;
	}

	public IMajorDao getMajorDao() {
		return majorDao;
	}

	@Resource
	public void setMajorDao(IMajorDao majorDao) {
		this.majorDao = majorDao;
	}

	public IGradeDao getGradeDao() {
		return gradeDao;
	}

	@Resource
	public void setGradeDao(IGradeDao gradeDao) {
		this.gradeDao = gradeDao;
	}

	@Override
	public void add(Grade grade) {
		Grade tg = this.loadByGradename(grade.getGradeName());
		if (tg != null)
			throw new GradeException("要添加的班级已经存在，不能添加");
		this.gradeDao.add(grade);

	}

	@Override
	public void update(Grade grade) {
		this.gradeDao.update(grade);

	}

	@Override
	public void delete(int id) {
		this.gradeDao.delete(id);

	}

	@Override
	public Grade load(int id) {
		return this.gradeDao.load(id);
	}

	@Override
	public List<Grade> list() {
		return this.gradeDao.list("from Grade");
	}

	@Override
	public PageObject<Grade> find(String gradeName) {
		if (gradeName == null || "".equals(gradeName.trim())) {
			return gradeDao.find("from Grade");
		} else {
			return gradeDao.find("from Grade where gradeName like ?",
					new Object[] { "%" + gradeName + "%" });
		}
	}

	@Override
	public Grade loadByGradename(String gradeName) {
		return this.gradeDao.loadByHql("from Grade where gradeNaem = ?",
				gradeName);
	}

	@Override
	public boolean checkGrade(String gradeName) {
		long count = (Long) gradeDao.loadObjByHQL(
				"select count(*) from User where gradeName=?", gradeName);
		return count > 0 ? true : false;
	}

	@Override
	public void addGradeFromInputStream(InputStream inputStream)
			throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				inputStream));
		String r = br.readLine();

		Major m = new Major();
		String majorName;
		Grade g = null;

		r = br.readLine();
		while (r != null) {
			if (r.contains("----------")) {// "------------"表示一个专业的开始标识
				majorName = br.readLine();
				m = majorDao.loadByHql("from Major where majorName=? ",
						majorName);
			} else {
				g=new Grade();
				g.setMajor(m);
				g.setCollege(m.getCollege());
				g.setGradeName(r.trim());
				g.setDescription(br.readLine().trim());
				gradeDao.add(g);
				TempIndex tempIndex = new TempIndex();
				tempIndex.setAdd();
				tempIndex.setObjId(g.getGradeId());
				tempIndex.setObjType(IndexUtil.ACTION_GRADE);
				tempIndexDao.add(tempIndex);
			}
			r = br.readLine();
		}
	}

}
