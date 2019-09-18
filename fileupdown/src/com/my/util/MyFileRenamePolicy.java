package com.my.util;

import java.io.File;
import java.text.SimpleDateFormat;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy{

	@Override
	public File rename(File file) {
		// TODO Auto-generated method stub
		System.out.println("rename(" + file+")호출");
		//1.파일이름 얻기
		System.out.println(file.getName() + ", " + file.getAbsoluteFile());
		//String filePath = file.getAbsolutePath();
		
		String parent = file.getParent();
		String oldName = file.getName();
		//2.파일 이름 바꾸기
		//2-1) 확장자제외한 파일이름 얻기
		int extIdx = oldName.lastIndexOf(".");
		String name = oldName.substring(0,extIdx);
		
		//2-2) 이름에 yyMMddHHmmss이어붙이기
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		String now = sdf.format(new java.util.Date());
		name += now;
		
		//2-3) 이름과 확장자 이어붙이기
		String newName = name + "." + oldName.substring(extIdx);
		
		//3.새파일 생성후 반환
		File f = new File(parent, newName);
	
		return f;
	}

}
