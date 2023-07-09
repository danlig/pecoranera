package controllers;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GenericController {
	public static boolean Add(Object obj, HttpServletRequest request, HttpServletResponse response) throws IOException {
		for (Field field : obj.getClass().getDeclaredFields()) {
    	   String fieldName = field.getName();
    	   String fieldType = field.getType().toString();
    	   
    	   // Non considerare attributo id
    	   if (fieldName.equals("id")) continue;
    	   
    	   String param = request.getParameter(fieldName);
    	   
    	   // Controllo se il parametro è stato inserito
    	   if (param == null || param.trim().equals("")) {
    		   response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Inserisci parametro: " + fieldName);
    		   return false;
    	   }
    	   
    	   String setMethod = "set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
    	   
    	   // Parametro string
    	   if (fieldType.contains("String")) {
    		   callSetter(obj, setMethod, param); 
    		   continue;
    	   }
    	   
    	   // Parametro double
    	   if (fieldType.contains("double")) {
    		   double paramDouble;
    		   
	   			try {
					paramDouble = Double.parseDouble(param);
				} catch(NumberFormatException e) {
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, 
							"Errore formato del parametro: " + fieldName);
					return false;
				}
    		   
    		   callSetter(obj, setMethod, paramDouble);
    		   continue;
    	   }
    	   
    	   // Parametro intero
    	   if (fieldType.contains("int")) {
    		   int paramInt;
    		   
	   			try {
					paramInt = Integer.parseInt(param);
				} catch(NumberFormatException e) {
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, 
							"Errore formato del parametro: " + fieldName);
					return false;
				}
    		   
    		   callSetter(obj, setMethod, paramInt);
    		   continue;
    	   }
        }      
	
	   return true;
	}
	
	private static void callSetter(Object obj, String methodName, Object value) {
        try {
            // Get the method with the specified name and parameter type
            Method method = obj.getClass().getMethod(methodName, value.getClass());
            
            // Invoke the setter method on the object
            method.invoke(obj, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
