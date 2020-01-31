
package com.games.app.dto;

import java.util.List;

public class GameResponse extends BaseResponse {
	
	private String Id;
	
	private List<GameObj> list;
	

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public List<GameObj> getList() {
		return list;
	}

	public void setList(List<GameObj> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "GameResponse [Id=" + Id + ", list=" + list + "]";
	}

	
	
}
