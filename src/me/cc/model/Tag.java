package me.cc.model;

import org.json.simple.JSONObject;

public class Tag {
	private  int no;
	private  String name;
	private  int start;
	private  int end;
	private  String id;
	

	public Tag(int n, String tag) {
		setNo(n);
		setName(tag);
	}

	public Tag(int no, String name, int number0, int number1) {
		this.no = no;
		this.name = name;
		this.start = Math.min(number0, number1);
		this.end =   Math.max(number0, number1);
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public String getId() {
		return id;
	}

	public void setId() {
        this.id = toString();
	}

	@Override
	public String toString() {
		return name+no;
	}

	@SuppressWarnings("unchecked")
	public Object toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("start", start);
		jo.put("end", end);
		jo.put("name", name);
		jo.put("set", no);
		return jo;
	}
}
