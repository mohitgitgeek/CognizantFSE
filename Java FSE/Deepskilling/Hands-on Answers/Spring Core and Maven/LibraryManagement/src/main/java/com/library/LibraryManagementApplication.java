package com.library; import org.springframework.context.support.ClassPathXmlApplicationContext; import com.library.service.BookService;
public class LibraryManagementApplication { public static void main(String[] args){try(var c=new ClassPathXmlApplicationContext("applicationContext.xml")){System.out.println(c.getBean(BookService.class).addAndFind("978-1","Spring in Action"));}} }
