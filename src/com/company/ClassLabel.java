package com.company;

import org.json.simple.JSONObject;

public class ClassLabel {
	private int id;
	private String text;

	public ClassLabel(int id, String text) {
		this.id = id;
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setText(String text) {
		this.text = text;
	}

	public JSONObject getJsonObject(){
		JSONObject jsonOjb = new JSONObject();
		jsonOjb.put("label id", this.id);
		jsonOjb.put("label text", this.text);
		return jsonOjb;
	}

}
