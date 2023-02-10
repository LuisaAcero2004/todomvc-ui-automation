package com.la.todo.pages;

import com.la.core.Page;
import com.la.todo.components.TaskComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfAllElementsLocatedBy;

import java.util.ArrayList;
import java.util.List;


public class ListPage extends Page {

    private final static By task = By.className("todo");
    private final static By newTaskNameTextbox = By.cssSelector("input[placeholder='What needs to be done?']");
    private final static By qtyTasks = By.cssSelector("[class='todo-count'] strong");
    private final static By allTasksButton = By.cssSelector("a[href='#/all']");
    private final static By activeTasksButton = By.cssSelector("a[href='#/active']");
    private final static By completedTasksButton = By.cssSelector("a[href='#/completed']");
    private final static By bulkCompleteTasksButton = By.cssSelector("label[for='toggle-all']");
    private final static By clearCompletedButton = By.cssSelector("button[class='clear-completed']");

    public ListPage(WebDriver driver){
        super(driver);
    }

   public List<TaskComponent<ListPage>> getAllTasks(){

       List<TaskComponent<ListPage>> tasksList = new ArrayList<>();

        for (WebElement webElement:
            WaitFor(presenceOfAllElementsLocatedBy(task))) {
            tasksList.add(new TaskComponent<>(getWebDriver(),this,webElement));
       }
        
        return tasksList;

   }
    public ListPage createNewTask(String taskName){

        WaitFor(presenceOfElementLocated(newTaskNameTextbox)).sendKeys(taskName , Keys.ENTER);

        return this;

    }

    public ListPage filterAllTasks(){

        WaitFor(presenceOfElementLocated(allTasksButton)).click();

        return this;

    }

    public ListPage filterActiveTasks(){

        WaitFor(presenceOfElementLocated(activeTasksButton)).click();

        return this;

    }

    public ListPage filterCompletedTasks(){

        WaitFor(presenceOfElementLocated(completedTasksButton)).click();

        return this;

    }

    public ListPage completeAllTasks(){

        WaitFor(presenceOfElementLocated(bulkCompleteTasksButton)).click();

        return this;

    }

    public ListPage clearCompletedTasks(){

        WaitFor(presenceOfElementLocated(clearCompletedButton)).click();

        return this;

    }

    public String getTasksCounterValue(){

        return   WaitFor(presenceOfElementLocated(qtyTasks)).getText();

    }







}
