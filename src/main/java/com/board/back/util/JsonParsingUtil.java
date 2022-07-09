package com.board.back.util;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import com.board.back.model.Board;
import com.board.back.model.FileModel;
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
	
	public FileModel SimpleGetJson(String jsonList, MultiValueMap<String, Object> map) {
		
		Board board  = new Board();
	    FileModel fileModel = new FileModel();
//	    List<FileModel> mFile= new <List>FileModel(fileModel);
//	     
	    
		JSONObject jObject = new JSONObject(jsonList);
		int type = jObject.getInt("type");
	    String title = jObject.getString("title");
	    String contents = jObject.getString("contents");
	    int memberno = jObject.getInt("memberNo");
	    
	    board.setType(type);
	    board.setTitle(title);
	    board.setContents(contents);
	    board.setMemberNo(memberno);	    
	    //board.setBfile(blob);

//	    for(String key : map.keySet()) {
//            String value = (String) map.get(key);
//            System.out.println(key + " : " + value);
//        }

	    
//	    for (savedFile : productsBean.getData()) {
//	        byte[] bytes = savedFile.getBytes();
//	        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
//	    }
	    
		//System.out.println("type :" + board.getType() + ", title :" + title + ", contents :" + contents + ", memberno :" + memberno);	 
		
		return fileModel;
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

