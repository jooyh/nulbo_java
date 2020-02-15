package com.siwan.nulbo.test;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.siwan.nulbo.utils.FTPUtil;

import configuration.RootConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RootConfiguration.class , FTPUtil.class})
public class FTPTest {
	
	@Autowired
	FTPUtil ftp;
	
	
	@Test
	public void connAndDisconn() throws IOException {
		FTPClient conn = ftp.getConnection();
		conn.disconnect();
	}
}
