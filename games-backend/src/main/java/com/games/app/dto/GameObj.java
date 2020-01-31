package com.games.app.dto;

public class GameObj {

	private String gameId;
	private String title;
	private String platform;
	private String score;
	private String genre;
	private String editors_choice;
	
	

	public GameObj() {
	}

	

	public GameObj(String id, String title, String platform, String score, String genre, String editors_choice) {
		super();
		this.gameId = id;
		this.title = title;
		this.platform = platform;
		this.score = score;
		this.genre = genre;
		this.editors_choice = editors_choice;
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
		return "GameObj [id=" + gameId + ", title=" + title + ", platform=" + platform + ", score=" + score + ", genre="
				+ genre + ", editors_choice=" + editors_choice + "]";
	}

}
