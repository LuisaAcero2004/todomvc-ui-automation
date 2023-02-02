import com.la.pages.ListPage;
import com.la.utilities.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class Tests {
    private ListPage listPage;
    private WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        listPage = new ListPage(driver);
        driver.get(Constants.URL);
    }

    @Test()
    public void createNewTask(){

        listPage.createNewTask(Constants.TASK_NAME);
        Assert.assertEquals(listPage.getNumberTasks(),"1");

    }

    @Test
    public void completeTask(){
        listPage.createNewTask(Constants.TASK_NAME);
        listPage.completeTask();
        Assert.assertEquals(listPage.getNumberTasks(),"0");
    }

    @Test
    public void deleteTask(){
        listPage.createNewTask(Constants.TASK_NAME);
        listPage.deleteTask();
        Assert.assertEquals(listPage.getNumberTasks(),"");
    }

    @Test
    public void editTaskName(){
        listPage.createNewTask(Constants.TASK_NAME);
        listPage.editTaskName(Constants.NEW_TASK_NAME);
        Assert.assertEquals(listPage.getTaskName(),Constants.TASK_NAME + Constants.NEW_TASK_NAME);
    }

    @AfterMethod
    public void finish(){
        driver.quit();
    }




}
