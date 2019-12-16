package me.cc.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnyAnswer {
	String done;

	public String getDone() {
		return done;
	}

	public void setDone(String done) {
		this.done = done;
	}
	
	public static ArrayList<AnyAnswer> listFactory()  {
		return new ArrayList<AnyAnswer>();
	}

	@Override
	public String toString() {
		return "AnyAnswer [done=" + done + "]";
	}
}
