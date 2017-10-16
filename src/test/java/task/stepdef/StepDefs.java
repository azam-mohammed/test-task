package task.stepdef;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import task.CreateTaskRequest;
import task.GetTaskListResponse;
import task.TaskResponse;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.useRelaxedHTTPSValidation;

public class StepDefs {
    private static final String BEARER = "Bearer ";
    /***
     * need to add your token as string variable below
     * Steps are in readme file how to generate token.
     */
    private static final String ACCESS_TOKEN = "";

    private static final String TASKLIST_GET_API = "https://www.googleapis.com/tasks/v1/users/@me/lists";
    private static final String CREATE_TASK_POST_API = "https://www.googleapis.com/tasks/v1/lists/{tasklistId}/tasks";
    private static final String MOVE_TASK_POST_API = "https://www.googleapis.com/tasks/v1/lists/{tasklistId}/tasks/{taskId}/move";

    private GetTaskListResponse taskList;
    private String taskListId;
    private TaskResponse task;


    @Given("^TestingGetTaskList")
    public void testingGetTaskList() {
        useRelaxedHTTPSValidation();
        Response response = given()
                .header("Authorization", BEARER + ACCESS_TOKEN)
//                .log().all()
                .when()
                .urlEncodingEnabled(false)
                .get(TASKLIST_GET_API)
                .andReturn();
        taskList = response.body().as(GetTaskListResponse.class);
        /**
         * by default google creates a sample task list for an account,
         * so this will never be null pointer or index out of bounds
         */
        taskListId = taskList.getItems().get(0).getId();
        System.out.println(taskList);
    }


    @When("CreateTask")
    public void createTask() {
        CreateTaskRequest newTask = new CreateTaskRequest();
        newTask.setTitle("Test Task 1");
        Response response = given()
                .header("Authorization", BEARER + ACCESS_TOKEN)
                .contentType(ContentType.JSON)
//                .log().all()
                .pathParam("tasklistId", taskListId)
                .when()
                .body(newTask)
                .post(CREATE_TASK_POST_API)
                .andReturn();
        task = response.body().as(TaskResponse.class);
        System.out.println(task);

    }

    @Then("^MovingTask")
    public void moveTask() {
        Response response = given()
                .header("Authorization", BEARER + ACCESS_TOKEN)
                .contentType(ContentType.JSON)
//                .log().all()
                .pathParam("tasklistId", taskListId)
                .pathParam("taskId", task.getId())
                .when()
                .post(MOVE_TASK_POST_API)
                .andReturn();
        response.body().as(TaskResponse.class);
    }
}
