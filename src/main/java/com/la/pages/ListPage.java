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
    private final static By allTasksButton = By.cssSelector("a[href='#/all']");
    private final static By activeTasksButton = By.cssSelector("a[href='#/active']");
    private final static By completedTasksButton = By.cssSelector("a[href='#/completed']");
    private final static By bulkCompleteTasksButton = By.cssSelector("label[for='toggle-all']");
    private final static By clearCompletedButton = By.cssSelector("button[class='clear-completed']");

    // private WebElement clearCompletedButton;


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

    public void filterAllTasks(){
        driver.findElement(allTasksButton).click();
    }

    public void filterActiveTasks(){
        driver.findElement(activeTasksButton).click();
    }

    public void filterCompletedTasks(){
        driver.findElement(completedTasksButton).click();
    }

    public void completeAllTasks(){
        driver.findElement(bulkCompleteTasksButton).click();
    }

    public void clearCompletedTasks(){
        driver.findElement(clearCompletedButton).click();
    }

    public String getNumberRemainingTasks(){

        return  driver.findElement(qtyTasks).getText();

    }

    public int getNumberTasks(){
        return driver.findElements(task).size();
    }

    public String getTaskName(){

        return driver.findElement(editTaskName).getText();
    }



}
