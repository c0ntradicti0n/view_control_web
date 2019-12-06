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

import org.json.simple.JSONObject;
import org.primefaces.component.slider.Slider;
import org.primefaces.event.SlideEndEvent;

import me.cc.beans.CcPyBean;
import me.cc.model.Tag;
/* */
@ViewScoped
@ManagedBean(name = "sliderView")
public class SliderView {
	private int number0 = 0;
	private int number1 = 1;
	private int no = 0;
	private String kind;

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
		ccPyBean.getAnnotationSets().get(no).get(kind).setStart(number0);

	}

	public int getNumber1() {
		return number1;
	}

	public void setNumber1(int number1) {
		this.number1 = (int) number1;
		ccPyBean.getAnnotationSets().get(no).get(kind).setEnd(number1);
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

	public void onInputChanged0(ValueChangeEvent event) {

		FacesMessage message = new FacesMessage("Input Changed", "Value: " + event.getNewValue());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void onSlideEnd0(SlideEndEvent event) {

		int n = number0;
		kind = (String) event.getComponent().getAttributes().get("kind");
		no = (int) event.getComponent().getAttributes().get("i");

		System.out.println(kind + no);

		setNumber0((int) event.getValue());
		System.out.println("second before " + n + " after " + number0 + ccPyBean.getAnnotationSets());

		FacesMessage message = new FacesMessage("Slide Ended", "Before n=" + n + " after " + event.getValue());
		FacesContext.getCurrentInstance().addMessage(null, message);
		
		String markup = ccPyBean.pycl.getMarkup(ccPyBean.getText(), ccPyBean.getAnnotationSets());
		System.out.println("'" + markup + "'");
		ccPyBean.setAnnotationMarkup(markup);
	}

	public void onInputChanged1(ValueChangeEvent event) {
		System.out.println("changed" + number1);

		FacesMessage message = new FacesMessage("Input Changed", "Value: " + event.getNewValue());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void onSlideEnd1(SlideEndEvent event) {

		int n = number1;
		kind = (String) event.getComponent().getAttributes().get("kind");
		no = (int) event.getComponent().getAttributes().get("i");

		System.out.println(kind + no);

		setNumber1((int) event.getValue());
		System.out.println("second before " + n + " after " + number1+ ccPyBean.getAnnotationSets());

		FacesMessage message = new FacesMessage("Slide Ended", "Before n=" + n + " after " + event.getValue());
		FacesContext.getCurrentInstance().addMessage(null, message);
		
		String markup = ccPyBean.pycl.getMarkup(ccPyBean.getText(), ccPyBean.getAnnotationSets());
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

}