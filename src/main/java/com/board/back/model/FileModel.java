package com.board.back.model;

import java.sql.Blob;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


@Entity
@Table(name = "filemodel")
@DynamicInsert
@DynamicUpdate
public class FileModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer no;
	
	@Column(name = "boardno")
	private Integer boardno;
	
	@Column(name = "filename")
	private String filename;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "filesize")
	private Long filesize;
	
	@Lob
	@Column(name = "content")
	private byte[] content;

	public FileModel() {
		super();
	}

	public FileModel(Integer no, Integer boardno, String filename, String type, Long filesize, byte[] content) {
		super();
		this.no = no;
		this.boardno = boardno;
		this.filename = filename;
		this.type = type;
		this.filesize = filesize;
		this.content = content;
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public Integer getBoardno() {
		return boardno;
	}

	public void setBoardno(Integer boardno) {
		this.boardno = boardno;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getFilesize() {
		return filesize;
	}

	public void setFilesize(Long filesize) {
		this.filesize = filesize;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

}