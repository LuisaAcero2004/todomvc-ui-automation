package com.la.todo.components;

import com.la.core.Component;
import com.la.core.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfNestedElementLocatedBy;

public class TaskComponent<P extends Page> extends Component<P> {

    private final static By completeTaskCheckbox = By.cssSelector("input[class='toggle']");
    private final static By deleteTaskButton = By.cssSelector("button[class='destroy']");
    private final static By task = By.cssSelector("div.view label");
    private final static By editTaskNameTextbox = By.cssSelector("input[type='text']");

    public TaskComponent(WebDriver webDriver, P page, WebElement rootElement){
        super(webDriver, page, rootElement);
    }

    public TaskComponent<P> completeTask(){

        WaitFor(presenceOfNestedElementLocatedBy(getRootElement(),completeTaskCheckbox)).click();

        return this;
    }

    public P deleteTask(){

        WaitFor(presenceOfNestedElementLocatedBy(getRootElement(),task)).click();
        WaitFor(presenceOfNestedElementLocatedBy(getRootElement(),deleteTaskButton)).click();

        return getPage();
    }

    public TaskComponent<P> editTaskName(String newTaskName) {

        Actions actions = new Actions(getWebDriver());
        actions.doubleClick(WaitFor(presenceOfNestedElementLocatedBy(getRootElement(),task))).perform();

        WaitFor(presenceOfNestedElementLocatedBy(getRootElement(),editTaskNameTextbox)).sendKeys(newTaskName , Keys.ENTER);

        return this;
    }

    public String getTaskName(){

        return WaitFor(presenceOfNestedElementLocatedBy(getRootElement(),task)).getText();

    }




}
