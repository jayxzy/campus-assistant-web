package com.techthinker.CAWeb.dao;

import org.springframework.stereotype.Repository;

import com.techthinker.CAWeb.idao.IChatroomDao;
import com.techthinker.CAWeb.vo.Chatroom;

@Repository("chatroomDao")
public class ChatroomDao extends BaseDao<Chatroom> implements IChatroomDao {

}
