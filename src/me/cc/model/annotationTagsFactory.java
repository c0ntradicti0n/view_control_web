package me.cc.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class annotationTagsFactory {
	public static ArrayList<HashMap<String,Tag>> produce(int n, List<String> tags) {
		ArrayList<HashMap<String,Tag>> annotationSets = new ArrayList<HashMap<String,Tag>>();

		for (int i = 0; i < n; i++) {
			HashMap<String, Tag> annotationSet = new HashMap<String, Tag>();
			for (String tag : tags) {
				annotationSet.put(tag, new Tag(i, tag));
			}
			annotationSets.add(annotationSet);

		}

		return annotationSets;

	}

}
