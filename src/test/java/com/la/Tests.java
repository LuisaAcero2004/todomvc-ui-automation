package com.la;

import com.la.todo.components.TaskComponent;
import com.la.todo.pages.ListPage;
import com.la.utilities.Constants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.assertj.core.api.Assertions.assertThat;


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

        listPage.createNewTask("Search a new cake recipe in the internet");

        assertThat(listPage.getAllTasks())
                .describedAs("Only one task should be created with the expected text")
                .hasSize(1)
                .first()
                .extracting(TaskComponent::getTaskName)
                .isEqualTo("Search a new cake recipe in the internet");

        assertThat(listPage.getTasksCounterValue())
                .describedAs("The remaining tasks counter should be one")
                .hasSize(1);

    }

    @Test
    public void completeTask(){
        listPage.createNewTask("Search a new cake recipe in the internet")
                .getAllTasks()
                .get(0)
                .completeTask();

        assertThat(listPage.getTasksCounterValue())
                .describedAs("The remaining tasks counter should be zero")
                .isEqualTo("0");
    }

    @Test
    public void deleteTask(){
        listPage.createNewTask("Search a new cake recipe in the internet")
                .getAllTasks()
                .get(0)
                .deleteTask();

        assertThat(listPage.getTasksCounterValue())
                .describedAs("The remaining tasks counter should be empty")
                .isEqualTo("");

    }

    @Test
    public void editTaskName(){
        listPage.createNewTask("Search a recipe to bake a")
                .getAllTasks()
                .get(0)
                .editTaskName(" chocolate cake");

        assertThat(listPage.getAllTasks())
                .describedAs("The new task name should be the expected text")
                .first()
                .extracting(TaskComponent::getTaskName)
                .isEqualTo("Search a recipe to bake a chocolate cake");

    }

    @Test
    public void filterCompleted(){
        listPage.createNewTask("Search a new cake recipe in the internet")
                .createNewTask("Make a list")
                .createNewTask("Go to the shopping and buy the ingredients")
                .createNewTask("Bake the cake")
                .getAllTasks()
                .get(1)
                .editTaskName(" of the ingredients")
                .getPage()
                .getAllTasks()
                .get(2)
                .completeTask()
                .getPage()
                .filterCompletedTasks();

        assertThat(listPage.getAllTasks())
                .describedAs("Only one completed task should be visible")
                .hasSize(1);

    }

    @Test
    public void filterAll(){

        listPage.createNewTask("Search a new cake recipe in the internet")
                .createNewTask("Make a list of the ingredients")
                .createNewTask("Go to the shopping and buy the ingredients")
                .createNewTask("Bake the cake")
                .getAllTasks()
                .get(0)
                .completeTask()
                .getPage()
                .filterCompletedTasks()
                .filterAllTasks();

        assertThat(listPage.getAllTasks())
                .describedAs("The four remaining and completed tasks should be visible")
                .hasSize(4);
    }

    @Test
    public void filterActive(){
        listPage.createNewTask("Search a new cake recipe in the internet")
                .createNewTask("Make a list of the ingredients")
                .createNewTask("Go to the shopping and buy the ingredients")
                .createNewTask("Bake the cake")
                .getAllTasks()
                .get(3)
                .deleteTask()
                .getAllTasks()
                .get(2)
                .completeTask()
                .getPage()
                .filterActiveTasks();

        assertThat(listPage.getAllTasks())
                .describedAs("Only the two remaining tasks should be visible")
                .hasSize(2);
    }

    @Test
    public void completeAllAndClearCompleted(){
        listPage.createNewTask("Search a new cake recipe in the internet")
                .createNewTask("Make a list of the ingredients")
                .createNewTask("Go to the shopping and buy the ingredients")
                .createNewTask("Bake the cake")
                .completeAllTasks()
                .clearCompletedTasks();

        assertThat(listPage.getTasksCounterValue())
                .describedAs("The remaining tasks counter should be empty")
                .isEqualTo("");
    }

    @AfterEach
    public void finish(){

        if(driver != null && !driver.toString().contains("null")){
            driver.quit();
        }

    }




}
