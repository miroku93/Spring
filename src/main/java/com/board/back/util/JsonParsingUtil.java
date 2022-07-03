package com.board.back.util;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Blob;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.board.back.model.Board;
import com.board.back.service.BoardService;

public class JsonParsingUtil {
	@Autowired
	private BoardService boardService;
	
	private JsonParsingUtil() {}
	
	public static JsonParsingUtil getInstance() {
		return JsonParsingUtilHolder.INSTANCE;
	}
	
	private static class JsonParsingUtilHolder {
		private static final JsonParsingUtil INSTANCE = new JsonParsingUtil();
	}
	
	public Board SimpleGetJson(String jsonList, Blob blob) {
		
		Board board  = new Board();
				
		JSONObject jObject = new JSONObject(jsonList);
		int type = jObject.getInt("type");
	    String title = jObject.getString("title");
	    String contents = jObject.getString("contents");
	    int memberno = jObject.getInt("memberNo");
	    
	    board.setType(type);
	    board.setTitle(title);
	    board.setContents(contents);
	    board.setMemberNo(memberno);	    
	    board.setBfile(blob);

	    
//	    for (savedFile : productsBean.getData()) {
//	        byte[] bytes = savedFile.getBytes();
//	        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
//	    }
	    
		System.out.println("type :" + board.getType() + ", title :" + title + ", contents :" + contents + ", memberno :" + memberno);	 
		
		return board;
	}
	
	
	private Blob convertFileToBlob(File file) throws Exception {
		  
	    Blob blob = null;
	    FileInputStream inputStream = null;
	  
	    try {
	        byte[] byteArray = new byte[(int) file.length()];
	        inputStream = new FileInputStream(file);
	        inputStream.read(byteArray);
	   
	        blob = new javax.sql.rowset.serial.SerialBlob(byteArray); 
	   
	    } catch (Exception e) {
	        throw e;
	   
	    } finally {
	        try {
	            if (inputStream != null){
	                inputStream.close();
	            }
	        } catch (Exception e) {
	            inputStream = null;
	        } finally {
	            inputStream = null;
	        }
	    }	  
	    return blob;
	} 

}

