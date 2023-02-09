package com.la;

import com.la.pages.ListPage;
import com.la.utilities.Constants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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

        listPage.createNewTask(Constants.TASK_NAME);
        assertThat(listPage.getNumberTasks(),equalTo("1"));

    }

    @Test
    public void completeTask(){
        listPage.createNewTask(Constants.TASK_NAME);
        listPage.completeTask();
        assertThat(listPage.getNumberTasks(),equalTo("0"));
    }

    @Test
    public void deleteTask(){
        listPage.createNewTask(Constants.TASK_NAME);
        listPage.deleteTask();
        assertThat(listPage.getNumberTasks(),equalTo(""));
    }

    @Test
    public void editTaskName(){
        listPage.createNewTask(Constants.TASK_NAME);
        listPage.editTaskName(Constants.NEW_TASK_NAME);
        assertThat(listPage.getTaskName(),equalTo(Constants.TASK_NAME + Constants.NEW_TASK_NAME));
    }

    @AfterEach
    public void finish(){

        if(driver != null && !driver.toString().contains("null")){
            driver.quit();
        }

    }




}
