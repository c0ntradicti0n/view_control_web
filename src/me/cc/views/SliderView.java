package me.cc.views;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;

import org.primefaces.event.SlideEndEvent;

@ViewScoped
@ManagedBean(name="sliderView")
public class SliderView {
    private int number0 = 0;
    private int number1 = 1;
  
    public int getNumber0() {
		return number0;
	}

	public void setNumber0(int number0) {
		System.out.println(number0);

		if(number0<this.number1)  {
			this.number0 = (int) number0;
		}	
	}

	public int getNumber1() {
		return number1;
	}

	public void setNumber1(int number1) {
		System.out.println(number1);
		if(number1>this.number0)  {
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
		
        FacesMessage message = new FacesMessage("Input Changed", "Value: " + event.getNewValue());
        FacesContext.getCurrentInstance().addMessage(null, message);
    } 
     
    public void onSlideEnd1(SlideEndEvent event) {
    	setNumber1((int) event.getValue());
		System.out.println("second" + number1);

    	FacesMessage message = new FacesMessage("Slide Ended", "Value: " + event.getValue());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}