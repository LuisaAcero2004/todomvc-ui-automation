package com.la.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;


public class ListPage extends BasePage{


    private By newTaskNameTextbox = By.cssSelector("input[placeholder='What needs to be done?']");
    private By completeTaskCheckbox = By.cssSelector("input[class='toggle']");
    private By deleteTaskButton = By.cssSelector("button[class='destroy']");
    private By editTaskName = By.cssSelector("div.view label");
    private By editTaskNameTextbox = By.cssSelector("input[type='text']");
    private By qtyTasks = By.cssSelector("[class='todo-count'] strong");


    public ListPage(WebDriver driver){
        super(driver);
    }

    public void createNewTask(String taskName){

        driver.findElement(newTaskNameTextbox).sendKeys(taskName + Keys.ENTER);

    }

    public void completeTask(){

        driver.findElement(completeTaskCheckbox).click();

    }

    public void deleteTask(){
        driver.findElement(editTaskName).click();
        driver.findElement(deleteTaskButton).click();

    }

    public void editTaskName(String newTaskName) {

        actions.doubleClick(driver.findElement(editTaskName)).perform();
        driver.findElement(editTaskNameTextbox).sendKeys(newTaskName + Keys.ENTER);

    }

    public String getNumberTasks(){

        return  driver.findElement(qtyTasks).getText();

    }

    public String getTaskName(){

        return driver.findElement(editTaskName).getText();
    }



}
