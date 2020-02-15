package com.siwan.nulbo.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.siwan.nulbo.controller.BaseController;
import com.siwan.nulbo.service.UserService;

import configuration.RootConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RootConfiguration.class, UserService.class})
public class UserTest {

	protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	@Autowired
	UserService userService;
	
	@Test
	public void selectUser() {
		System.out.println(userService.selectUser().toString());
	}
	
}
