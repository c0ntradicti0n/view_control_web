package me.cc.treenav;

import java.io.Serializable;
 
public class Document implements Serializable {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private boolean isTopic = false;
    
    public Document(String name) {
    	this.name = name;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return name;
    }

    public boolean getIsTopic() {
        return isTopic;
    }

    public void setTopic(boolean topic) {
        isTopic = topic;
    }
}