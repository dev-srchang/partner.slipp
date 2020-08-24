package com.ywmobile.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
	private User writer;
	
	private String title;
	private String contents;
	private LocalDateTime createdDate;
	
	public Question() {
	}

	public Question(User writer, String title, String contents) {
		super();
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.createdDate = LocalDateTime.now();
	}
	
	public String getFormattedCreatedDate() {
		if (createdDate == null) {
			return "";
		}
		
		return createdDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
	}
	
	public void update(String title, String contents) {
		this.title = title;
		this.contents = contents;
	}

	@Override
	public String toString() {
		return "Question [id = " + id + ", writer = " + writer + ", title = " + title + ", contents = " + contents + "]";
	}
}
