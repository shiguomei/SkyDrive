package skyDriveServer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * ���û��ϴ����ļ����ڱ���
 * @author lichuang
 * 
 * @date 2015/8/30
 *
 */


public class DownLoad {
		
	private FileOutputStream fileOutputStream = null;
	private InputStream inputStream = null;
	private byte[] data = null;

	public DownLoad(Socket socket,String file) {
		try {
			fileOutputStream = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			close();
		}
		try {
			inputStream = socket.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
			close();
		}
	}
	
	public boolean start() {
		try {
			while((inputStream.read(data)) != -1) {
				fileOutputStream.write(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
			close();
			return false;
		}
		close();
		return true;
	}
	
	private void close() {
		if(fileOutputStream != null) {
			try {
				fileOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(inputStream != null) {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}