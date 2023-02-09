package com.la.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;


public class ListPage extends BasePage{


    private final static By task = By.className("todo");
    private final static By newTaskNameTextbox = By.cssSelector("input[placeholder='What needs to be done?']");
    private final static By completeTaskCheckbox = By.cssSelector("input[class='toggle']");
    private final static By deleteTaskButton = By.cssSelector("button[class='destroy']");
    private final static By editTaskName = By.cssSelector("div.view label");
    private final static By editTaskNameTextbox = By.cssSelector("input[type='text']");
    private final static By qtyTasks = By.cssSelector("[class='todo-count'] strong");


    public ListPage(WebDriver driver){
        super(driver);
    }

    public void createNewTask(String taskName){
        driver.findElement(newTaskNameTextbox).sendKeys(taskName + Keys.ENTER);
    }

    public void completeTask(){
        driver.findElement(completeTaskCheckbox).click();
    }

    public void completeTask(int taskPosition){
        driver.findElements(task).get(taskPosition).findElement(completeTaskCheckbox).click();
    }

    public void deleteTask(){
        driver.findElement(editTaskName).click();
        driver.findElement(deleteTaskButton).click();
    }

    public void deleteTask(int taskPosition){
        driver.findElements(task).get(taskPosition).findElement(editTaskName).click();
        driver.findElements(task).get(taskPosition).findElement(deleteTaskButton).click();
    }

    public void editTaskName(String newTaskName) {
        Actions actions = new Actions(driver);
        actions.doubleClick(driver.findElement(editTaskName)).perform();
        driver.findElement(editTaskNameTextbox).sendKeys(newTaskName + Keys.ENTER);
    }

    public void editTaskName(String newTaskName, int taskPosition) {
        Actions actions = new Actions(driver);
        actions.doubleClick(driver.findElements(task).get(taskPosition).findElement(editTaskName)).perform();
        driver.findElements(task).get(taskPosition).findElement(editTaskNameTextbox).sendKeys(newTaskName + Keys.ENTER);
    }

    public String getNumberTasks(){

        return  driver.findElement(qtyTasks).getText();

    }

    public String getTaskName(){

        return driver.findElement(editTaskName).getText();
    }



}
