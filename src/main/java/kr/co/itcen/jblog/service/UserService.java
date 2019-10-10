package kr.co.itcen.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.jblog.repository.BlogDao;
import kr.co.itcen.jblog.repository.CategoryDao;
import kr.co.itcen.jblog.repository.UserDao;
import kr.co.itcen.jblog.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CategoryDao categoryDao;

	public void join(UserVo vo) {
		userDao.insert(vo);
		blogDao.insert(vo.getId());
		categoryDao.insert(vo.getId());
	}
	
	//사용자 중복 확인
	public Boolean existUser(String id) {
		return userDao.get(id) != null;
	}
	
	//session내 회원 정보
	public UserVo getUser(UserVo vo) {
		return userDao.get(vo);
	}


}