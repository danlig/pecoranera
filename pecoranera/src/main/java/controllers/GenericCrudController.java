package controllers;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BasicCrudDao;

public class GenericCrudController {
	private static final Logger LOGGER = LogManager.getLogger(GenericCrudController.class);
	
	// Tipi di operazioni CRUD
	public static enum operation {
		ADD_MODE, EDIT_MODE, REMOVE_MODE
	}
	
	public static <T> boolean Add(Class<T> cls, HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Istanzia oggetto
		T obj = null;
		try {
			obj = cls.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			return false;
		}
		
		// Verifica validità di tutti i parametri
		if (!Validate(obj, operation.ADD_MODE, request, response))
			return false;
	
		// Salva oggetto
		BasicCrudDao<T> dao = new BasicCrudDao<>(cls);
		dao.doSave(obj);
		
		return true;
	}
	
	public static <T> boolean Remove(Class<T> cls, HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Converti id in int
		int id;
		
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch(NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Errore formato id");
			return false;
		}
		
		// Cancella l'oggetto
		BasicCrudDao<T> dao = new BasicCrudDao<>(cls);
		dao.doDeleteByKey(id);
		
		return true;
	}
	
	public static <T> boolean Edit(Class<T> cls, HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Converti id in int
	    int id;
	   
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch(NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Errore formato id");
			return false;
		}
	
		// Ottieni oggetto
		BasicCrudDao<T> dao = new BasicCrudDao<>(cls);
		T obj = dao.doRetrieveByKey(id);
		
		if (obj == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "l'elemento non esiste");
			return false;
		}
		
		// Verifica validità di tutti i parametri
		if (!Validate(obj, operation.EDIT_MODE, request, response))
			return false;
	
		// Salva oggetto
		dao.doSave(obj);
		
		return true;
	}
	
	public static <T> boolean Validate(Object obj, operation mode, HttpServletRequest request, HttpServletResponse response) throws IOException {
		for (Field field : obj.getClass().getDeclaredFields()) {
	    	   String fieldName = field.getName();
	    	   String fieldType = field.getType().toString();
	    	   
	    	   // Non considerare attributo id
	    	   if (fieldName.equals("id")) continue;
	    	   if (fieldType.contains("Set")) continue;
	    	   
	    	   String param = request.getParameter(fieldName);
	    	   
	    	   // Controllo se il parametro è stato inserito
	    	   if (param == null || param.trim().equals("")) {
	    		   if (mode == operation.EDIT_MODE) continue;
	    		   
	    		   if (mode == operation.REMOVE_MODE && fieldType.contains("String")) continue;
	    		   
	    		   // Controllo solo per eventi	    		   
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
	    	   
	    	   // Parametro data
	    	   if (fieldType.contains("Date")) {
	    		   Date paramDate;
	    		   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ITALIAN);
	    		   
		   	   		try {
						paramDate = formatter.parse(request.getParameter("date"));
					} catch (ParseException e) {
						response.sendError(HttpServletResponse.SC_BAD_REQUEST, 
								"Errore formato del parametro: " + fieldName);
						return false;
					}
		   	   		
	    		   callSetter(obj, setMethod, paramDate);
	    		   continue;
	    	   }
	    	   
	    	   // Parametro oggetto custom
	    	   Object paramObj = GenericCrudController.doRetrieveByKeyAndValidate(field.getType(), param, response);
	    	   if (paramObj == null) 
	    		   return false;
	    	   
	    	   callSetter(obj, setMethod, paramObj);
	        }
		
		return true;
	}
	
	public static <T> Object doRetrieveByKeyAndValidate(Class<?> cls, String value, HttpServletResponse response) throws IOException {
		Object obj = null;
		BasicCrudDao<?> dao = new BasicCrudDao<>(cls);
		
		try {
			int id = Integer.parseInt(value);
			obj = dao.doRetrieveByKey(id);
		}
		catch (Exception e){
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Formato id non valido");
			return null;
		}
		
		if (obj == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Oggetto inesistente");
		}
		
		return obj;
	}
	
	private static void callSetter(Object obj, String methodName, Object value) {
        try {
            // Get the method with the specified name and parameter type
            Method method = obj.getClass().getMethod(methodName, value.getClass());
            
            // Invoke the setter method on the object
            method.invoke(obj, value);
        } catch (Exception e) {
            LOGGER.error("Error occurred in context", e);
        }
    }
}
