package com.ywmobile.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Setter;

@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@Setter
	private String writer;
	
	@Column(nullable = false)
	@Setter
	private String title;
	
	@Column(nullable = false)
	@Setter
	private String contents;
	
	public Question() {
	}

	public Question(String writer, String title, String contents) {
		super();
		this.writer = writer;
		this.title = title;
		this.contents = contents;
	}

	@Override
	public String toString() {
		return "Question [id = " + id + ", writer = " + writer + ", title = " + title + ", contents = " + contents + "]";
	}
}
