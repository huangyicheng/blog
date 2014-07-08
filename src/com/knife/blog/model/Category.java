package com.knife.blog.model;


import com.jfinal.plugin.activerecord.Model;

public class Category extends Model<Category>
{
	public static final Category dao = new Category();

	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String ORDERBY = "orderby";

}
