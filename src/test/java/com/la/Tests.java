package com.la;

import com.la.pages.ListPage;
import com.la.utilities.Constants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static com.la.utilities.Constants.*;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class Tests {
    private ListPage listPage;
    private WebDriver driver;

    @BeforeEach
    public void setUp(){
        driver = new ChromeDriver();
        listPage = new ListPage(driver);
        driver.get(Constants.URL);
    }

    @Test()
    public void createNewTask(){

        listPage.createNewTask(TASK_NAME);
        assertThat(listPage.getNumberTasks(),equalTo("1"));

    }

    @Test
    public void completeTask(){
        listPage.createNewTask(TASK_NAME);
        listPage.completeTask();
        assertThat(listPage.getNumberTasks(),equalTo("0"));
    }

    @Test
    public void deleteTask(){
        listPage.createNewTask(TASK_NAME);
        listPage.deleteTask();
        assertThat(listPage.getNumberTasks(),equalTo(""));
    }

    @Test
    public void editTaskName(){
        listPage.createNewTask(TASK_NAME);
        listPage.editTaskName(NEW_TASK_NAME);
        assertThat(listPage.getTaskName(),equalTo(TASK_NAME + NEW_TASK_NAME));
    }

    @Test
    public void filterCompleted(){
        listPage.createNewTask("first task");
        listPage.createNewTask("second task");
        listPage.createNewTask("third task");
        listPage.createNewTask("fourth task");
        listPage.editTaskName(" edited",1);
        listPage.completeTask(2);
        listPage.filterCompletedTasks();
        assertThat(listPage.getNumberTasks(),equalTo(1));
    }

    @Test
    public void filterAll(){
        listPage.createNewTask("first task");
        listPage.createNewTask("second task");
        listPage.createNewTask("third task");
        listPage.completeTask();
        listPage.filterCompletedTasks();
        listPage.filterAllTasks();
        assertThat(listPage.getNumberTasks(),equalTo(3));
    }

    @Test
    public void filterActive(){
        listPage.createNewTask("first task");
        listPage.createNewTask("second task");
        listPage.createNewTask("third task");
        listPage.createNewTask("fourth task");
        listPage.deleteTask(3);
        listPage.completeTask(2);
        listPage.filterActiveTasks();
        assertThat(listPage.getNumberTasks(),equalTo(2));
    }

    @Test
    public void completeAllAndClearCompleted() throws InterruptedException {
        listPage.createNewTask("first task");
        listPage.createNewTask("second task");
        listPage.createNewTask("third task");
        listPage.createNewTask("fourth task");
        listPage.completeAllTasks();
        listPage.clearCompletedTasks();
        assertThat(listPage.getNumberRemainingTasks(),equalTo(""));
        assertThat(listPage.getNumberTasks(),equalTo(0));
    }

    @AfterEach
    public void finish(){

        if(driver != null && !driver.toString().contains("null")){
            driver.quit();
        }

    }




}
