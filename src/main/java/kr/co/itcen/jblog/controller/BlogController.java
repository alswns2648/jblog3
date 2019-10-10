package kr.co.itcen.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.itcen.jblog.security.AuthUser;
import kr.co.itcen.jblog.service.BlogService;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.PostVo;
import kr.co.itcen.jblog.vo.UserVo;

@Controller
@RequestMapping("/blog/{id:(?!assets).*}")//assets밑에 있는 파일을 제외하고 모두 받는다
public class BlogController {

	@Autowired
	private BlogService blogService;

	// 블로그 메인
	@RequestMapping({"","/{path1}","/{path1}/{path2}" })
	public String blogMain(@PathVariable String id, @PathVariable Optional<Long> path1, @PathVariable Optional<Long> path2,@AuthUser UserVo vo,  Model model ) {
		List<CategoryVo> list = blogService.getList(id);
		model.addAttribute("list",list);
		Long categoryno= list.get(0).getNo();
		Long postno = 0L;
		if(path2.isPresent()) {
			categoryno=path1.get();
			postno=path2.get();
		}else if(path1.isPresent()) {
			categoryno=path1.get();
		}	
		List<PostVo> postList= blogService.getList(categoryno);
		model.addAttribute("postList",postList);
		PostVo postvo;
		if(postno==0L && (!postList.isEmpty())) {
			postno=postList.get(0).getNo();
			postvo=blogService.getPost(postno,categoryno);
		}else {
			postvo= blogService.getPost(postno,categoryno);
		}
		BlogVo blogvo = blogService.get(id);
		model.addAttribute("vo",blogvo);
		model.addAttribute("post",postvo);
		model.addAttribute("id",id);
		if(id.equals(vo.getId()))
			model.addAttribute("isMe",true);
		return "blog/blog-main";
	}
}