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
@Table(name = "board")
@DynamicInsert
@DynamicUpdate
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer no;
	
	@Column(name = "type")
	private int type;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "contents")
	private String contents;
	
	@Column(name = "member_no")
	private Integer memberNo;
	
	@Column(name = "created_time")
	private Date createdTime;
	
	@Column(name = "updated_time")
	private Date updatedTime;
	
	@Column(name = "likes")
	private Integer likes;
	
	@Column(name = "counts")
	private Integer counts;	
	
	@Lob
	@Column(name = "bfile")
	private byte[] bfile;

	public Board() {
		super();
	}

	public Board(Integer no, int type, String title, String contents, Integer memberNo, Date createdTime,
			Date updatedTime, Integer likes, Integer counts, byte[] bfile) {
		super();
		this.no = no;
		this.type = type;
		this.title = title;
		this.contents = contents;
		this.memberNo = memberNo;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.likes = likes;
		this.counts = counts;
		this.bfile = bfile;
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Integer getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public Integer getCounts() {
		return counts;
	}

	public void setCounts(Integer counts) {
		this.counts = counts;
	}

	public byte[] getBfile() {
		return bfile;
	}

	public void setBfile(byte[] bfile) {
		this.bfile = bfile;
	}

	@Override
	public String toString() {
		return "Board [no=" + no + ", type=" + type + ", title=" + title + ", contents=" + contents + ", memberNo="
				+ memberNo + ", createdTime=" + createdTime + ", updatedTime=" + updatedTime + ", likes=" + likes
				+ ", counts=" + counts + "]";
	}	
	
	
}
