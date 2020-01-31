/**
 * 
 */
package com.games.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Game")

public class Game {
	
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "TITLE")
	private String title;
	
	@Column(name = "PLATEFORM")
	private String platform;
	
	@Column(name = "SCORE")
	private String score;
	
	@Column(name = "GENRE")
	private String genre;
	
	@Column(name = "EDITORS_CHOICE")
	private String editors_choice;

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getEditors_choice() {
		return editors_choice;
	}

	public void setEditors_choice(String editors_choice) {
		this.editors_choice = editors_choice;
	}

	@Override
	public String toString() {
		return "Game [title=" + title + ", platform=" + platform + ", score=" + score + ", genre=" + genre
				+ ", editors_choice=" + editors_choice + "]";
	}
	
	

}
