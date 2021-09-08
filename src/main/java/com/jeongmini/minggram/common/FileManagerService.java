package com.jeongmini.minggram.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileManagerService {
	
	private final String FILE_UPLOAD_PATH = "C:\\Users\\opooi\\OneDrive\\바탕 화면\\workspace\\spring_test\\upload\\minggram\\images/";
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public String saveFile(int userId, MultipartFile file) {
		String directoryName = userId + "_" + System.currentTimeMillis() + "/";
		
		//완전한 파일경로
		String filePath = FILE_UPLOAD_PATH + directoryName;
		
		//파일 저장할 디렉토리 생성
		File directory = new File(filePath);
		
		if(directory.mkdir() == false) {
			// 클래스이름 메소드 이름
			logger.error("[FileManagerService saveFile] 디렉토리 생성 실패");
			return null;
		}
		
		//파일저장 byte단위
		byte[] bytes;
		try {
			bytes = file.getBytes();
			Path path = Paths.get(filePath + file.getOriginalFilename());
			Files.write(path, bytes);		
		} catch (IOException e) {
			logger.error("[FileManagerService saveFile] 파일 생성 실패");
			e.printStackTrace();
			return null;
		}
		
		//파일 접근 가능한 path 리턴
		return "/images/" + directoryName + file.getOriginalFilename(); 
		
		
	}
	                         //파일경로
	public void removeFile(String filePath) {
		//삭제할 파일 경로
		//filePath : /images/10_3234132/test.png
		//실제 파일이 저장 된 경로 : C:\\Users\\opooi\\OneDrive\\바탕 화면\\workspace\\spring_test\\upload\\minggram\\images/
		//일치하는 부분만 빼오기 
		
		String realFilePath = FILE_UPLOAD_PATH + filePath.replace("/images/", "");
		
		//파일 먼저 지우고
		Path path = Paths.get(realFilePath); //path는 경로를 관리해주는 객체 거기에 집어넣고
		//해당 파일이 있는지
		if(Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				logger.error("[FileManagerService removeFile] 파일 삭제 실패");
				e.printStackTrace();
			}
		}
		
		//디렉토리(폴더) 지우고
		//폴더 경로 - C:\\Users\\opooi\\OneDrive\\바탕 화면\\workspace\\spring_test\\upload\\minggram\\images/10_2384738
		//     Paths.get(realFilePath)
		path = path.getParent(); 
		
		if(Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				logger.error("[FileManagerService removeFile] directory delete fail");
				e.printStackTrace();
			}
		}
		
		
	}

}
