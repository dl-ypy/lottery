package com.lottery.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.lottery.common.Const;
import com.lottery.common.ServerResponse;
import com.lottery.pojo.NewsModel;
import com.lottery.pojo.UserModel;
import com.lottery.service.INewsService;

@Controller
@RequestMapping("/news")
public class NewsAction {
	@Autowired
	private INewsService iNewsService;
	
	/**
	 * 查询单个新闻信息
	 * @param newsid
	 * @param map
	 * @return
	 */
	@RequestMapping("/queryOne.do")
	public String queryOne(short newsid, Map<String, Object> map) {
		NewsModel news = iNewsService.selectByPrimaryKey(newsid);
		map.put("news", news);
		return "/front/news/details.jsp";
	}
	
	/**
	 * 按条件查询所有新闻
	 * @param session
	 * @param news
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/query.do")
	@ResponseBody
	public ServerResponse query(HttpSession session, NewsModel news, @RequestParam(value="pageNum",defaultValue="1") int pageNum, @RequestParam(value="pageSize",defaultValue="5") int pageSize) {
		news.setNewsid(null);  //设id为空，防止按标题名查询时也查询上id
		PageInfo<NewsModel> pageList = (PageInfo<NewsModel>) iNewsService.queryAll(news, pageNum, pageSize);
		List<NewsModel> newsList = pageList.getList();
		session.setAttribute("page", pageList);
		session.setAttribute("news", newsList);
		session.setAttribute("newsTitle", news.getTitle());
		return ServerResponse.createBySuccessMessage("front/news/news.jsp");
	}
	/**
	 * 按条件查询所有新闻后台
	 * @param session
	 * @param news
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/queryback.do")
	@ResponseBody
	public ServerResponse queryall(HttpSession session, NewsModel news, @RequestParam(value="pageNum",defaultValue="1") int pageNum, @RequestParam(value="pageSize",defaultValue="5") int pageSize) {
		news.setNewsid(null);  //设id为空，防止按标题名查询时也查询上id
		PageInfo<NewsModel> pageList = (PageInfo<NewsModel>) iNewsService.queryAll(news, pageNum, pageSize);
		List<NewsModel> newsList = pageList.getList();
		session.setAttribute("page", pageList);
		session.setAttribute("news", newsList);
		session.setAttribute("newsbTitle", news.getTitle());
		System.out.println("--------------------"+newsList);
		return ServerResponse.createBySuccessMessage("back/news/news-list.jsp");
	}
	/**
	 * 保存新闻
	 * @param session
	 * @param news
	 * @return
	 */
	@RequestMapping("/save.do")
	@ResponseBody
	public ServerResponse save(HttpSession session, NewsModel news) {
		UserModel user = (UserModel)session.getAttribute(Const.CURRENT_MANAGER);
		if (user != null) {
			return iNewsService.insertSelective(news);
		}
		return ServerResponse.createByErrorMessage("需要管理员登录！");
	}
	
	/**
	 * 删除新闻
	 * @param session
	 * @param newsid
	 * @return
	 */
	@RequestMapping("/delete.do")
	@ResponseBody
	public ServerResponse delete(HttpSession session, short newsid) {
		UserModel user = (UserModel)session.getAttribute(Const.CURRENT_MANAGER);
		if (user != null) {
			iNewsService.deleteByPrimaryKey(newsid);
			return ServerResponse.createBySuccessMessage("news/queryback.do");
		}
		return ServerResponse.createByErrorMessage("需要管理员登录！");
	}
	
	/**
	 * 保存新闻
	 * @param session
	 * @param news
	 * @return
	 */
	@RequestMapping("/update.do")
	@ResponseBody
	public ServerResponse update(HttpSession session, NewsModel news) {
		UserModel user = (UserModel)session.getAttribute(Const.CURRENT_MANAGER);
		if (user != null) {
			return iNewsService.insertSelective(news);
		}
		return ServerResponse.createByErrorMessage("需要管理员登录！");
	}
	
	/**
	 * 访问上一篇新闻
	 * @param newsid
	 * @param session
	 * @return
	 */
	@RequestMapping("/previous.do")
	@ResponseBody
	public ServerResponse previous(short newsid, HttpSession session) {
		ServerResponse response = iNewsService.previous(newsid);
		if (response.getStatus() == 0) {
			NewsModel news = (NewsModel) response.getData();
			session.setAttribute("news", news);
			return ServerResponse.createBySuccessMessage("front/news/details.jsp");
		} else {
			return response;
		}
	}
	
	/**
	 * 访问下一篇新闻
	 * @param newsid
	 * @param session
	 * @return
	 */
	@RequestMapping("/next.do")
	@ResponseBody
	public ServerResponse next(short newsid, HttpSession session) {
		ServerResponse response = iNewsService.next(newsid);
		if (response.getStatus() == 0) {
			NewsModel news = (NewsModel) response.getData();
			session.setAttribute("news", news);
			return ServerResponse.createBySuccessMessage("front/news/details.jsp");
		} else {
			return response;
		}
	}
}
