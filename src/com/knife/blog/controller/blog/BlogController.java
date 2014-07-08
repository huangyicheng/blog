package com.knife.blog.controller.blog;




import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.knife.blog.model.Article;
import com.knife.blog.model.Category;

public class BlogController extends Controller{

	public void index() 
	{
		final  Integer pageNum = getParaToInt("p", 1);
		Page<Article> articles = Article.dao.paginate(pageNum, 2, 
				"select id,title,content,tags,category,createDateTime,updateDateTime,viewCount,replyCount", 
				" from article order by id desc");
		setAttr("articles", articles);
		
		List<Record> categorys = Db.find("select c.id,c.name,count(*) num from category c,article a where c.id=a.category group by c.id");
				//Category.dao.find("select id,name,orderby from category");
		setAttr("categorys",categorys);
		//List<Article> hotsView = Article.dao.find("select id,title,viewCount from kf_article order by viewCount desc limit 0,5");
		//setAttr("hotsView", hotsView);
		
		List<Record> records = Db.find("select count(*) num,year(createDateTime) year,month(createDateTime) month from article group by year(createDateTime),month(createDateTime)");
		setAttr("catbytime",records);
		//render("kf_index.html");
		render("/view/blog/index.html");
	}
	
	public void article()
	{
		
		final Integer articleId = getParaToInt(0);
		Article article =Article.dao.findById(articleId);
		Article.dao.clear().set("id", articleId).set("viewCount", article.getInt("viewCount") + 1).update();
		setAttr("article", article);
		
		List<Record> categorys = Db.find("select c.id,c.name,count(*) num from category c,article a where c.id=a.category group by c.id");
		//Category.dao.find("select id,name,orderby from category");
		setAttr("categorys",categorys);
		
		/*List<Article> hotsView = Article.dao.find("select id,title,viewCount from kf_article order by viewCount desc limit 0,5");
		setAttr("hotsView", hotsView);*/
		
		List<Record> records = Db.find("select count(*) num,year(createDateTime) year,month(createDateTime) month from article group by year(createDateTime),month(createDateTime)");
		setAttr("catbytime",records);
		
		render("/view/blog/article.html");
	}
	
}
