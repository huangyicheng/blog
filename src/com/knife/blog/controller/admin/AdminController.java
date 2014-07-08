package com.knife.blog.controller.admin;



import java.util.Date;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.aop.ClearInterceptor;
import com.jfinal.aop.ClearLayer;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.knife.blog.interceptor.SessionInterceptor;
import com.knife.blog.model.Article;
import com.knife.blog.model.Category;
import com.knife.blog.model.User;

@Before(SessionInterceptor.class)
public class AdminController extends Controller
{
	private User user;
	private Article article;
    public void index()
    {
    	Object user = getSessionAttr("user");
    	if(user != null) 
		{
			//redirect("/admin");
    		render("/view/admin/index.html");
		} 
		else 
		{
			render("/view/admin/sign-in.html");
		}
    }
    
    @ClearInterceptor(ClearLayer.ALL)
	public void login()
	{
		user = getModel(User.class);
		user = User.dao.findFirst(
				"select * from user where username=? and password=?",
				user.getStr(User.USERNAME), user.getStr(User.PASSWORD));
		if (user == null) 
		{
			setAttr("error", 1);
			setAttr("msg", "用户名或密码错误");
		} 
		else 
		{
			setSessionAttr("user", user);
			setSessionAttr("userid",user.get("id"));
			setAttr("error", 0);
			setAttr("msg", "登陆成功");
		}
		renderJson();
	}
    
    public void write()
    {
    	List<Category> categroys = Category.dao.find("select id,name,orderby from category");
    	setAttr("categorys",categroys);
    	render("/view/admin/write.html");
    }
	
    public void post()
    {
    	article = getModel(Article.class);
    	article.set("createDateTime", new Date());
    	article.set("updateDateTime", new Date());
    	article.set("finish", 1);
    	article.save();
		renderJson();
    }
    
    public void list()
    {
    	final  Integer pageNum = getParaToInt("p", 1);
		Page<Article> articles = Article.dao.paginate(pageNum, 10, 
				"select id,title,content,tags,category,createDateTime,updateDateTime,viewCount,replyCount", 
				" from article order by id desc");
		setAttr("articles",articles);
		render("/view/admin/list.html");
    }

}
