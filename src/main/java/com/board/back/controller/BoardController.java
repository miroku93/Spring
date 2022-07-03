package com.board.back.controller;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.board.back.util.JsonParsingUtil;
import com.board.back.model.Board;
import com.board.back.service.BoardService;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
@CrossOrigin(origins = "http://localhost:3000")
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
		
		return boardService.getPagingBoard(p_num);
	}

	@PostMapping("/upload") 
    public String form(@RequestPart MultipartFile file, @RequestPart("jsonList") String jsonList) throws IOException, SerialException, SQLException {
	//public String form(@RequestPart MultipartFile file, @RequestPart("jsonList") String jsonList) throws IOException {
	//public String form(@RequestPart MultipartFile file, @RequestPart Board board) throws IOException {
		System.out.println("-------------/upload----------------");
		String fileName = file.getName();
		String originFileName = file.getOriginalFilename();
		byte[] bytes = file.getBytes();
		long filesize = file.getSize();		
		Blob blob = new SerialBlob(file.getBytes());		
		
		
		System.out.println(originFileName +":"+ filesize);
//		System.out.println(file);
		
		System.out.println(blob);
		
//	    JSONObject jObject = new JSONObject(jsonList);
//	    int type = jObject.getInt("type");
//	    String title = jObject.getString("title");
//	    String contents = jObject.getString("contents");
//	    int memberno = jObject.getInt("memberNo");

		
	    Board board = jUtil.SimpleGetJson(jsonList, blob);	    
	    System.out.println("-----------------------form------------------------");	 
		//System.out.println("type :" + board.getType() + ", title :" + board.getTitle() + ", contents :" + board.getContents() + ", memberno :" + board.getMemberNo());	 
		
		
	    System.out.println("-----------------------createBoard------------------------");	 
		boardService.createBoard(board);
        return "upload-test";
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
	public ResponseEntity<Map<String, Boolean>> deleteBoardByNo(
			@PathVariable Integer no) {
		
		return boardService.deleteBoard(no);
	}
	
	
}
