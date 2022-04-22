package com.app.handler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


@Component
public class FileUploadHelper {
	
	 //public final String UPLOAD_DIR="D:\\Spring Boot Workspace 1\\SocialMediaApp\\src\\main\\resources\\static\\image";

	 public final String UPLOAD_DIR=new ClassPathResource("static/image/").getFile().getAbsolutePath();
	
	 
	 public FileUploadHelper () throws IOException
	 {
		 
		 
	 }
	 
	 //to upload a file in target location
	 public boolean uploadFile(MultipartFile multipartFile)
	 {
		 boolean f=false;
		 try 
		 {
	 	
				/*
				 * InputStream is= multipartFile.getInputStream(); byte data[]=new
				 * byte[is.available()]; is.read(data); //write FileOutputStream fos=new
				 * FileOutputStream(UPLOAD_DIR+"\\"+ multipartFile.getOriginalFilename());
				 * fos.write(data); fos. flush(); fos.close();
				 */
	         // Copy file to the target location (Replacing existing file with the same name)

			 Files.copy (multipartFile.getInputStream(),Paths.get(UPLOAD_DIR+File.separator+multipartFile.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
			 f=true;
		 
		 } catch (Exception e) {
			 	e.printStackTrace();
		 }
		 return f;
	 }
}
