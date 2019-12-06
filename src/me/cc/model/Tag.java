package me.cc.model;

import org.json.simple.JSONObject;

public class Tag {
	private  int no;
	private  String kind;
	private  int start;
	private  int end;
	private  String id;
	private  boolean able = true;

	

	public Tag(int n, String tag) {
		setNo(n);
		setKind(tag);
		setId();
	}

	public Tag(int no, String name, int number0, int number1) {
		this.no = no;
		this.kind = name;
		this.start = Math.min(number0, number1);
		this.end =   Math.max(number0, number1);
		setId();
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
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
		return "Tag [no=" + no + ", kind=" + kind + ", start=" + start + ", end=" + end + "]";
	}

	@SuppressWarnings("unchecked")
	public Object toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("start", start);
		jo.put("end", end);
		jo.put("kind", kind);
		jo.put("set", no);
		return jo;
	}

	public boolean isAble() {
		return able;
	}

	public void setAble(boolean able) {
		this.able = able;
	}
}
