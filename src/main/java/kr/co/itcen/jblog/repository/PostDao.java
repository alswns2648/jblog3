package kr.co.itcen.jblog.repository;


import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.exception.UserDaoException;
import kr.co.itcen.jblog.vo.UserVo;

@Repository
public class PostDao {
	
	@Autowired
	private SqlSession sqlSession;
	
}