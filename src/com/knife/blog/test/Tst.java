package com.knife.blog.test;

import com.knife.blog.util.markdown.txtmark.Processor;

public class Tst {
	
	public static void main(String[] args)
	{
		String test =  " #mardown \n "
				+ "``public void main(){}`` \n##hello";
		String result = Processor.process(test);
		System.out.println(result);
	}


}
