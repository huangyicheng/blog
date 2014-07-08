package com.knife.blog.core;


import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.knife.blog.controller.admin.AdminController;
import com.knife.blog.controller.blog.BlogController;
import com.knife.blog.handler.HtmlExtensionHandler;
import com.knife.blog.model.Article;
import com.knife.blog.model.Category;
import com.knife.blog.model.User;


public class CoreConfig extends JFinalConfig {
	

	public void configConstant(Constants me) {
		loadPropertyFile("a_little_config.txt");
		me.setDevMode(getPropertyToBoolean("devMode", false));
	}
	

	public void configRoute(Routes me) {
		me.add("/", BlogController.class);
		//me.add("/blog", BlogController.class);
		//me.add("/",LoginController.class);
		//me.add("/bookmark",BookMarkController.class);
		me.add("/admin", AdminController.class);
		//me.add("/login", LoginController.class);

	}
	

	public void configPlugin(Plugins me) {

		C3p0Plugin c3p0Plugin = new C3p0Plugin(getProperty("jdbcUrl"), getProperty("user"), getProperty("password").trim());
		me.add(c3p0Plugin);
		

		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		me.add(arp);
		//arp.addMapping("webtype", Webtype.class);	
		arp.addMapping("user", User.class);
		arp.addMapping("article", Article.class);
		arp.addMapping("category", Category.class);
	}
	
	public void configInterceptor(Interceptors me) {
		
	}
	

	public void configHandler(Handlers me) {
		me.add(new HtmlExtensionHandler());
	}

}
