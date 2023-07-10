package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.Part;
import org.apache.tika.Tika;

public class EventImageUpload {
    public static boolean upload(String contextPath, Part filePart, int idEvent) throws IOException  {
        final String filePath = contextPath
        		+ File.separator + "images" 
        		+ File.separator + "events"
        		+ File.separator + Integer.toString(idEvent) + ".png";
          
        // declare instances of OutputStream, InputStream, and PrintWriter classes  
        OutputStream otpStream = null;  
        InputStream iptStream = null;   
          
        // try section handles the code for storing file into the specified location  
        try {  
            // initialize instances of OutputStream and InputStream classes  
            otpStream = new FileOutputStream(new File(filePath));
            iptStream = filePart.getInputStream();  
  
            int read = 0;  
              
            // initialize bytes array for storing file data  
            final byte[] bytes = new byte[1024];  
              
            // use while loop to read data from the file using iptStream and write into  the desination folder using writer and otpStream  
            while ((read = iptStream.read(bytes)) != -1) {  
                otpStream.write(bytes, 0, read);  
            } 
        }  
        // catch section handles the errors   
        catch (IOException fne){  
            return false;
        }  
        // finally section will close all the open classes  
        finally {  
    		if (otpStream != null) {  
    			otpStream.close();  
    		}  
    		if (iptStream != null) {  
    			iptStream.close();  
    		}
        }
        
        return true;
    }
    
    public static boolean isImage(Part part) throws IOException {
        Tika tika = new Tika();
        String contentType = tika.detect(part.getInputStream());
        return contentType.equals("image/png");
    }
}
