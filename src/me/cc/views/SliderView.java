package me.cc.views;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;

import org.apache.log4j.Logger;
import org.primefaces.component.slider.Slider;
import org.primefaces.event.SlideEndEvent;

import me.cc.beans.CcPyBean;
import me.cc.model.Tag;
import me.cc.restclient.PythonClient;
/* */
@RequestScoped
@ManagedBean(name = "sliderView")
public class SliderView {
	static Logger logger = Logger.getLogger(SliderView.class);

	private int number0 = 0;
	private int number1 = 100;
	private int minlen=0;
	private int maxlen=100;
    private int _i;
	public int get_i() {
		return _i;
	}

	public void set_i(int _i) {
		this._i = _i;
	}

	private int  no = 0;

	private Slider sliderStart;
	private Slider sliderEnd;
	
	CcPyBean ccPyBean;
	
	@PostConstruct
	public void init()  {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ccPyBean
		    = (CcPyBean)facesContext.getApplication()
		      .createValueBinding("#{ccPyBean}").getValue(facesContext);
	}

	public int getNumber0() {
		return number0;
	}

	public void setNumber0(int number0) {
		this.number0 = (int) number0;
		ccPyBean.getAnnotationSets().get(no).get(_i).setStart(number0);

	}

	public int getNumber1() {
		return number1;
	}

	public void setNumber1(int number1) {
		this.number1 = (int) number1;
		ccPyBean.getAnnotationSets().get(no).get(_i).setEnd(number1);
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public void onInputChanged0(ValueChangeEvent event) {

		FacesMessage message = new FacesMessage("Input Changed", "Value: " + event.getNewValue());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void onSlideEnd0(SlideEndEvent event) {

		 int n = number0;
		_i = (int) event.getComponent().getAttributes().get("_i");
		no = (int) event.getComponent().getAttributes().get("i");

		logger.info("updating slider in set "+ no +" number " + _i);

		setNumber0((int) event.getValue());
		System.out.println("second before " + n + " after " + number0 + ccPyBean.getAnnotationSets());

		FacesMessage message = new FacesMessage("Slide Ended", "Before n=" + n + " after " + event.getValue());
		FacesContext.getCurrentInstance().addMessage(null, message);
		
		String markup = ccPyBean.pycl.stdCall("markup", ccPyBean.spot, ccPyBean.annotationMarkup, ccPyBean.annotationSets, ccPyBean.String_Type);
		System.out.println("'" + markup + "'");
		ccPyBean.setAnnotationMarkup(markup);
	}

	public void onInputChanged1(ValueChangeEvent event) {
		System.out.println("changed" + number1);

		FacesMessage message = new FacesMessage("Input Changed", "Value: " + event.getNewValue());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void onSlideEnd1(SlideEndEvent event) {

		 int  n = number1;
		_i = (int) event.getComponent().getAttributes().get("_i");
		no = (int) event.getComponent().getAttributes().get("i");

		logger.info("updating slider in set "+ no +" number " + _i);

		setNumber1((int) event.getValue());
		System.out.println("second before " + n + " after " + number1+ ccPyBean.getAnnotationSets());

		FacesMessage message = new FacesMessage("Slide Ended", "Before n=" + n + " after " + event.getValue());
		FacesContext.getCurrentInstance().addMessage(null, message);
		
		String markup = ccPyBean.pycl.stdCall("markup", ccPyBean.spot, ccPyBean.annotationMarkup, ccPyBean.annotationSets, ccPyBean.String_Type);
		System.out.println("'" + markup + "'");
		ccPyBean.setAnnotationMarkup(markup);
	}

	public Slider getSliderStart() {
		return sliderStart;
	}

	public void setSliderStart(Slider sliderStart) {
		this.sliderStart = sliderStart;
	}

	public Slider getSliderEnd() {
		return sliderEnd;
	}

	public void setSliderEnd(Slider sliderEnd) {
		this.sliderEnd = sliderEnd;
	}
	
	
	
	public void onSlideEnd(SlideEndEvent event) {

		int n = number1;
		_i = (int) event.getComponent().getAttributes().get("_i");
		no = (int)  (double) event.getComponent().getAttributes().get("i");

		logger.info("updating slider in set "+ no +" number " + _i);

		setNumber1((int) event.getValue());
		System.out.println("second before " + n + " after " + number1+ ccPyBean.getAnnotationSets());

		FacesMessage message = new FacesMessage("Slide Ended", "Before n=" + n + " after " + event.getValue());
		FacesContext.getCurrentInstance().addMessage(null, message);
		
		String markup = ccPyBean.pycl.stdCall("markup", ccPyBean.text, ccPyBean.annotationMarkup, ccPyBean.annotationSets, ccPyBean.String_Type);
		System.out.println("'" + markup + "'");
		ccPyBean.setAnnotationMarkup(markup);
	}

	public int getMinlen() {
		return minlen;
	}

	public void setMinlen(int minlen) {
		this.minlen = minlen;
	}

	public int getMaxlen() {
		return maxlen;
	}

	public void setMaxlen(int maxlen) {
		this.maxlen = maxlen;
	}

}