package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


import javax.servlet.GenericServlet;
import javax.servlet.http.Part;

public class FileManager {
	private static final String PATH_DIR = File.separator + "static" + File.separator + "imgs";
	
	public static boolean addImg(String path, String dir, String newName, Part partFile) {
		String fileName = newName + getFileExtension(partFile);
		String savePath = path + PATH_DIR + File.separator + dir + File.separator + fileName;
	
		File file = new File(savePath);
		
		try (
			InputStream inputStream = partFile.getInputStream();
	        OutputStream outputStream = new FileOutputStream(file)
	    ) {
			byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
		
		return true;
	}
	
	public static String getFileExtension(Part file) {
		return file.getSubmittedFileName().substring(file.getSubmittedFileName().lastIndexOf("."));
	}
}
