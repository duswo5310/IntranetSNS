package FTPUpload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FTPUploader {
	public boolean sendFtpServer(String ip, int port, String id, String password, String folder, String localPath, ArrayList<String> files) {
		boolean isSuccess = false;
		FTPClient ftp = null;
		int reply;
		try {
			ftp = new FTPClient();
			ftp.setControlEncoding("UTF-8");
			ftp.connect(ip, port);
			System.out.println("Connected to " + ip + " on " + ftp.getRemotePort());
			
			reply = ftp.getReplyCode();
			if(!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				System.err.println("FTP server refused connection");
				System.exit(1);
			}
			
			if(!ftp.login(id, password)) {
				ftp.logout();
				throw new Exception("ftp ������ �α������� ���߽��ϴ�.");
			}
			
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.enterLocalPassiveMode();
			
			System.out.println(ftp.printWorkingDirectory());
			try {
				ftp.makeDirectory(folder);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ftp.changeWorkingDirectory(folder);
			System.out.println(ftp.printWorkingDirectory());
			
			for(int i = 0; i < files.size(); i++) {
				String sourceFile = localPath + files.get(i);
				File uploadFile = new File(sourceFile);
				FileInputStream fis = null;
				try {
					fis = new FileInputStream(uploadFile);
					System.out.println(sourceFile + ": ���� ���� => ");
					isSuccess = ftp.storeFile(files.get(i), fis);
					System.out.println(sourceFile + ": ���� ��� => " + isSuccess);
				} catch (IOException e) {
					e.printStackTrace();
					isSuccess = false;
				} finally {
					if(fis != null) {
						try {
							fis.close();
						} catch (IOException e2) {}
					}
				}
			}
			ftp.logout();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(ftp != null && ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException e) {	}
			}
		}
		return isSuccess;
	}
}