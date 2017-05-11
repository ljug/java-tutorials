package net.cofares.jsf.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ExampleManagedBean {

    private int actionCounter = 0;

    private String exampleProperty = "Property Value";

    public String getExampleProperty() {
        return exampleProperty;
    }

    public void exampleAction() {
        this.exampleProperty += "######" + actionCounter++;
    }

}