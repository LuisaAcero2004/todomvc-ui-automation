package com.la.core;

import org.openqa.selenium.WebDriver;


public abstract class Page extends Navigable {

    public Page(WebDriver webDriver){
        super(webDriver);
    }

}
