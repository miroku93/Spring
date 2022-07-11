package com.board.back.controller;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.board.back.util.JsonParsingUtil;
import com.board.back.model.Board;
import com.board.back.model.FileModel;
import com.board.back.service.BoardService;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;



@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	JsonParsingUtil jUtil = JsonParsingUtil.getInstance();
	
	// get paging board 
	@GetMapping("/board")	
	public ResponseEntity<Map> getAllBoards(@RequestParam(value = "p_num", required=false) Integer p_num) {
		
		System.out.println("-------------getAllBoards in----------------");
		if (p_num == null || p_num <= 0) p_num = 1;

		// 초기화 확인	
//		List<File> fileList = new ArrayList<File>();
//		File tfile = new File("");
//		fileList.add(tfile);
//		
//		List<FileModel> dtos = new ArrayList<FileModel>();
//		FileModel dto = new FileModel();		
//		dtos.add(dto);	
//		test (fileList,  dtos);		
		
		return boardService.getPagingBoard(p_num);
	}

	public void test (List<File> fileList, List<FileModel> dtos){
		System.out.println("-----------------------test------------------------");	
		System.out.println("fileList.size():" + fileList.size());	
		System.out.println("fileList.get(0).getName():" + fileList.get(0).getName());	
		
		dtos.get(0).getFilename();
		System.out.println("dtos.size():" + dtos.size());	
		System.out.println("dtos.get(0).getFilename():" + dtos.get(0).getFilename());		
	}	
	
	//@PostMapping("/board/formData")
	@PostMapping("/upload") 
    public Board createBoardF(@RequestPart List<MultipartFile> files, @RequestPart("jsonList") String jsonList) throws IOException, SerialException, SQLException {
	//public Board form(@RequestPart MultipartFile files, @RequestPart("jsonList") String jsonList) throws IOException {
	//public String form(@RequestPart MultipartFile file, @RequestPart Board board) throws IOException {
		System.out.println("-------------/upload----------------");
		
		int fileCnt = files.size();
		System.out.println("fileCnt:" +fileCnt);
//		
//		String fileName[] = {""};
//		String originFileName[] ={""};		
		
//		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
//		for(MultipartFile file : files) {		    	
//		   	map.add(file.getOriginalFilename(), new ByteArrayResource(file.getBytes()));
//		   	map.add(file.getOriginalFilename(), new String(file.getContentType()));
//		   	map.add(file.getOriginalFilename(), new Long(file.getSize()));
//		}

		

		List<File> fileList = new ArrayList<File>();


		List<FileModel> dtos = new ArrayList<FileModel>();
	
		for (MultipartFile file : files) {
			FileModel dto = new FileModel();
			String originalfileName = file.getOriginalFilename();
			File dest = new File("C:/Image/" + originalfileName);
			long filesize = file.getSize();
			System.out.println("---------------------------------------");
			System.out.println("originalfileName:" +file.getOriginalFilename());
			System.out.println("filesize:" +file.getSize());
			System.out.println("type:" +file.getContentType());
			System.out.println("type:" +file.getBytes());
						
			dto.setFilename(originalfileName);
			dto.setContent(file.getBytes());
			dto.setType(file.getContentType());
			dto.setFilesize(file.getSize());			
			dtos.add(dto);	
		}
//		
//		int i = 0;
//		for( String filename : map.keySet() ){
//			List<Object> value = map.get(filename);
//			System.out.println( filename +":"+ value );
//			dto.setFilename(filename);
//			try {
//				dto.setContent(convertObjectToBytes(value.get(0)));
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			dto.setType(String.valueOf(value.get(1)));
//			dto.setFilesize(Long.valueOf(String.valueOf(value.get(2))));
//			
//			dtos.add(i,dto);
//			i++;
//		}

		System.out.println("jsonList:" +jsonList);
		
	    JSONObject jObject = new JSONObject(jsonList);
	    int type1 = jObject.getInt("type");
	    String title = jObject.getString("title");
	    String contents = jObject.getString("contents");
	    int memberno = jObject.getInt("memberNo");
		
		Board board  = new Board();    
	    board.setType(type1);
	    board.setTitle(title);
	    board.setContents(contents);
	    board.setMemberNo(memberno);	    
	    //board.setBfile(bytes);
	    
	    
	    //FileModel fileModel = jUtil.SimpleGetJson(jsonList, map);	    
	    System.out.println("-----------------------form------------------------");	 
		//System.out.println("type :" + board.getType() + ", title :" + board.getTitle() + ", contents :" + board.getContents() + ", memberno :" + board.getMemberNo());	 
		
		
	    System.out.println("-----------------------createBoard------------------------");	 
		return boardService.createBoard(board);
    }
	
	// get all board 
//	@GetMapping("/board")
//	public List<Board> getAllBoards() {		
//		return boardService.getAllBoard();
//	}

	// create board
	@PostMapping("/board")
	public Board createBoard(@RequestBody Board board) {
		System.out.println("@PostMapping(\"/board\")");
		System.out.println(board.toString());
		return boardService.createBoard(board);
	}
	
	// get board
	@GetMapping("/board/{no}")
	public ResponseEntity<Board> getBoardByNo(
			@PathVariable Integer no) {
		
		return boardService.getBoard(no);
	}
	
	// update board
	@PutMapping("/board/{no}")
	public ResponseEntity<Board> updateBoardByNo(
			@PathVariable Integer no, @RequestBody Board board){
		
		return boardService.updateBoard(no, board);
	}
	
	// delete board
	@DeleteMapping("/board/{no}")
	public ResponseEntity<Map<String, Boolean>> deleteBoardByNo(@PathVariable Integer[] no){
		System.out.println("-----------------------@DeleteMapping------------------------");	
		
		Map<String, Boolean> response = new HashMap<>();
		for (Integer i : no) {
			System.out.println(i);
			boardService.deleteBoard(i);
			response.put("Deleted Board Data by id : ["+i+"]", Boolean.TRUE);
		 }
			
		return ResponseEntity.ok(response);
	}
	
	public static byte[] convertObjectToBytes(Object obj) throws IOException {
	    ByteArrayOutputStream boas = new ByteArrayOutputStream();
	    try (ObjectOutputStream ois = new ObjectOutputStream(boas)) {
	        ois.writeObject(obj);
	        return boas.toByteArray();
	    }
	}
	
}
