package me.cc.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import me.cc.model.Tag;

@ViewScoped
@ManagedBean(name = "commandBean")
public class CommandBean {
	CcPyBean ccPyBean;
	
	@PostConstruct
	public void init()  {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ccPyBean
		    = (CcPyBean)facesContext.getApplication()
		      .createValueBinding("#{ccPyBean}").getValue(facesContext);
	}

	public String annotation_from_here () {
		String ans = ccPyBean.pycl.stdCall("annotation_from_here", ccPyBean.getSpot(), ccPyBean.annotationMarkup, ccPyBean.annotationSets, ccPyBean.String_Type);	
        return "ReaderScreen";

	}
	public String take_it_as_is () {
		String ans = ccPyBean.pycl.stdCall("take_it_as_is", ccPyBean.getSpot(), ccPyBean.annotationMarkup, ccPyBean.annotationSets, ccPyBean.String_Type);	
        return "ReaderScreen";

	}
	public String zero_annotation_selection_first_corpus () {
		String ans = ccPyBean.pycl.stdCall("zero_annotation_selection_first_corpus", ccPyBean.getSpot(), ccPyBean.annotationMarkup, ccPyBean.annotationSets, ccPyBean.String_Type);	

        return "ReaderScreen";

	}
	public String annotation_in_between () {
		String ans = ccPyBean.pycl.stdCall("annotation_in_between", ccPyBean.getSpot(), ccPyBean.annotationMarkup, ccPyBean.annotationSets, ccPyBean.String_Type);	
        return "ReaderScreen";

	}
	public String zero_annotation_selection_second_corpus () {
		String ans = ccPyBean.pycl.stdCall("zero_annotation_selection_second_corpus", ccPyBean.getSpot(), ccPyBean.annotationMarkup, ccPyBean.annotationSets, ccPyBean.String_Type);	
        return "ReaderScreen";

	}
	public String trash () {
        return "ReaderScreen";
	}
	
	public void more_kind (String kind, int no) {
		int size_up_to_now = ccPyBean.annotationSets.get(no).size();
		ccPyBean.annotationSets.get(no).add(new Tag(no, size_up_to_now, kind, 0, ccPyBean.textlen-1));
	}
	public void less_kind (int no, int _i) {
		ccPyBean.annotationSets.get(no).remove(_i);
	}
	
	public void more_set (String kind, int no) {
		int size_up_to_now = ccPyBean.annotationSets.get(no).size();
		ccPyBean.annotationSets.get(no).add(new Tag(no, size_up_to_now, kind, 0, ccPyBean.textlen-1));
	}
	public void less_set (int no, int _i) {
		ccPyBean.annotationSets.get(no).remove(_i);
	}
}