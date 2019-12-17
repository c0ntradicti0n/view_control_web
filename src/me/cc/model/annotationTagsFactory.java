package me.cc.model;

import java.util.ArrayList;
import java.util.List;

public class annotationTagsFactory {
	public static ArrayList<ArrayList<Tag>> produce(int n, List<String> tags) {
		ArrayList<ArrayList<Tag>> annotationSets = new ArrayList<ArrayList<Tag>>();

		for (int i = 0; i < n; i++) {
			ArrayList<Tag> annotationSet = new ArrayList<Tag>();
			for (String tag : tags) {
				annotationSet.add(new Tag(i, tag));
			}
			annotationSets.add(annotationSet);
		}
		return annotationSets;
	}
	
	public static void renumerate( ArrayList<ArrayList<Tag>> aSs)  { 
		int _set = 0;
		
		for (ArrayList<Tag> aS : aSs)  {
		    int _a=0;
		    for (Tag t : aS)  {
		    	t.setNo(_set);
		    	t.set_i(_a);
		    	_a += 1;

		    }
		    _set += 1;
		}
	}
}
