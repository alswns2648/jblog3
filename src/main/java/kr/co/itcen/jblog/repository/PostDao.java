package kr.co.itcen.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.vo.PostVo;


@Repository
public class PostDao {
	
	@Autowired
	private SqlSession sqlSession;

	//post list를 가져옴
	public List<PostVo> getList(Long categoryno) {
		List<PostVo> list = sqlSession.selectList("post.getList",categoryno);
		return list;
	}

	//post 하나를 선택하여 보여줌
	public PostVo getPost(Long postno, Long categoryno) {
		//hashmap 객체 생성
		Map<String, Long> map = new HashMap<String, Long>();
		//map에 키,값 삽입
		map.put("no", postno);
		map.put("category_no", categoryno);
		//getPost 쿼리를 사용하여 값 하나 꺼내오기
		PostVo vo = sqlSession.selectOne("post.getPost", map);
		return vo;
	}
	
}