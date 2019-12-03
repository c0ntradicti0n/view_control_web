package me.cc.views;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;

import org.json.simple.JSONObject;
import org.primefaces.component.slider.Slider;
import org.primefaces.event.SlideEndEvent;

import me.cc.model.Tag;

@SessionScoped
@ManagedBean(name="sliderView")
public class SliderView {
    private int number0 = 0;
    private int number1 = 1;
    private int no = 0;
    private String name;


    
    private Slider sliderStart;
    private Slider sliderEnd;
    JSONObject spans = new JSONObject();

  
    public int getNumber0() {
		return number0;
	}

	public void setNumber0(int number0) {
		this.number0 = (int) number0;
		Tag tag = new Tag(no, name, number0, number1);
        spans.put(tag.getId(), tag.toJSON());
	}

	public int getNumber1() {
		return number1;
	}

	public void setNumber1(int number1) {
		System.out.println(number1);
		if(number1 > this.number0)  {
			this.number1 = (int) number1;
		}
	}

	public void onInputChanged0(ValueChangeEvent event) {
		
        FacesMessage message = new FacesMessage("Input Changed", "Value: " + event.getNewValue());
        FacesContext.getCurrentInstance().addMessage(null, message);
    } 
     
    public void onSlideEnd0(SlideEndEvent event) {
    	setNumber0((int) event.getValue());
		System.out.println("first" + number0);

    	FacesMessage message = new FacesMessage("Slide Ended", "Value: " + event.getValue());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    

	public void onInputChanged1(ValueChangeEvent event) {
		System.out.println("changed" + number1);
		
        FacesMessage message = new FacesMessage("Input Changed", "Value: " + event.getNewValue());
        FacesContext.getCurrentInstance().addMessage(null, message);
    } 
     
    public void onSlideEnd1(SlideEndEvent event) {
    	int n = number1;
    	setNumber1((int) event.getValue());
		System.out.println("second before " + n + " after " + number1);

    	FacesMessage message = new FacesMessage("Slide Ended", "Before n="+n +  " after " + event.getValue());
        FacesContext.getCurrentInstance().addMessage(null, message);
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