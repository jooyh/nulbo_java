package com.siwan.nulbo.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.SocketException;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FTPUtil {
	@Value("${ftp.uploadpath}")
	private String uploadpath; 
	@Value("${ftp.hosturl}")
	private String hosturl; 
	@Value("${ftp.userid}")
	private String userid; 
	@Value("${ftp.userpw}")
	private String userpw; 
	
	public FTPClient getConnection() {
		FTPClient ftp = new FTPClient();
		ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
		int reply;
		try {
			ftp.connect(hosturl);
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				throw new Exception("Exception in connecting to FTP Server");
			}
			ftp.login(userid, userpw);
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.enterLocalPassiveMode();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ftp;
	}
	
	public void disconnect(FTPClient ftp){
		if (ftp == null) return;
		if (ftp.isConnected()) {
			try {
				ftp.logout();
				ftp.disconnect();
			} catch (IOException f) {
				f.printStackTrace();
			}
		}
	}
	
	
}
