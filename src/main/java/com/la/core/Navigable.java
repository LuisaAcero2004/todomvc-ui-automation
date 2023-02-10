package com.la.core;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public abstract class Navigable {

    private final static Duration defaultTimeWait = Duration.ofSeconds(5L);
    private final WebDriver webDriver;
    private final WebDriverWait webDriverWait;
    public Navigable(WebDriver webDriver){
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(getWebDriver(),defaultTimeWait);
    }

    public <V> V WaitFor(Function<? super WebDriver, V> isTrue){
        return webDriverWait.until(isTrue);
    }

    public WebDriver getWebDriver(){
        return webDriver;
    }


}
