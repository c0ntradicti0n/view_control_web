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
}
