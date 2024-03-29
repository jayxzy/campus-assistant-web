package com.techthinker.CAWeb.iservice;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.techthinker.CAWeb.persistence.User;
import com.techthinker.CAWeb.util.PageObject;

public interface IUserService {

	/**
	 * 添加用户
	 * 判断用户名是否唯一，如果不唯一抛出异常
	 * @param u 要添加的用户
	 */
	public void add(User u);
	/**
	 * 更新用户
	 * 不能更新用户名
	 * @param u 要更新的用户
	 */
	public void update(User u);
	/**
	 * 删除用户
	 * 删除用户首先需要删除用户所发表的所有评论和留言
	 * @param id
	 */
	public void delete(int id);
	/**
	 * 根据用户id来获取相应的用户
	 * @param id 需要获取用户的主键id
	 * @return 需要获取的用户
	 */
	public User load(int id);
	/**
	 * 获取所有用户列表
	 * @return 所有用户列表
	 */
	public List<User> list();
	/**
	 * 用户 登录
	 * @param username 用户的登录用户名
	 * @param pwd 用户的登录密码
	 * @return
	 */
	public User login(String userName,String pwd);
	/**
	 * 根据分页获取用户信息
	 * @param pageOffset 从哪一个位置开始查询
	 * @param pageSize 每页显示多少条
	 * @return
	 */
	public PageObject<User> find(String userName);
	/**
	 * 根据用户名获取某个用户
	 * @param userId
	 * @return
	 */
	public User loadByUsername(String userName);
	/**
	 * 检查用户是否存在
	 * @param username
	 * @return
	 */
	public boolean checkUser(String userName);
	/**
	 * 从输入流中批量读取User信息，并插入数据库
	 * @param inputStream 输入流
	 * @throws IOException IO异常
	 */
	public void addUserFromInputStream(InputStream inputStream)throws IOException;
}
