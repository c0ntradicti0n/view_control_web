package me.cc.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

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

	public void annotation_from_here () {
		//ccPyBean.pycl.stdCall("annotation_from_here", ccPyBean.text);
	}
	public void take_it () {
		//ccPyBean.pycl.stdCall("take_it", ccPyBean.text);

	}
	public void zero_annotation_selection_first_corpus () {
		//ccPyBean.pycl.stdCall("zero_annotation_selection_first_corpus", ccPyBean.text);

	}
	public void annotation_in_between () {
		//ccPyBean.pycl.stdCall("annotation_in_between", ccPyBean.text);

	}
	public void zero_annotation_selection_second_corpus () {
		//ccPyBean.pycl.stdCall("zero_annotation_selection_second_corpus", ccPyBean.text);

	}
	public void trash () {

	}
}