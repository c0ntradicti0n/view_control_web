package me.cc.model;

public class Distinction extends BaseClass {

	private  int no; // set
	private  int _i; // position in set
	private  String kind;
	private  float start;
	public int get_i() {
		return _i;
	}

	public void set_i(int _i) {
		this._i = _i;
	}

	private  float end;
	private  String id;
	private  boolean able = true;


	public Distinction() {

	}

	public Distinction(int n, String tag) {
		setNo(n);
		setKind(tag);
		setId();
	}

	public Distinction(int no, int _i, String kind, int number0, int number1) {
		this.no = no;
		this._i = _i;
		this.kind = kind;
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

	public float getStart() {
		return start;
	}

	public void setStart(float start) {
		this.start = start;
	}

	public float getEnd() {
		return end;
	}

	public void setEnd(float end) {
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
		return "Tag [no=" + no + ", _i=" + _i + ", kind=" + kind + ", start=" + start + ", end=" + end + ", id=" + id
				+ ", able=" + able + "]";
	}

	public boolean isAble() {
		return able;
	}

	public void setAble(boolean able) {
		this.able = able;
	}
}
