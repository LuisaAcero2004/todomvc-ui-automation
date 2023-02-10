package com.la.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class Component <P extends Page> extends Navigable {

    private P page;
    private WebElement rootElement;

    public Component(WebDriver webDriver,P page, WebElement rootElement){
        super(webDriver);
        this.page = page;
        this.rootElement = rootElement;
    }

    public WebElement getRootElement(){
        return this.rootElement;
    }

    public P getPage(){
        return page;
    }


}
