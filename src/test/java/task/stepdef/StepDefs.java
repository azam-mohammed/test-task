package task.stepdef;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import task.CreateTaskRequest;
import task.GetTaskListResponse;
import task.TaskResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

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
    private Response apiResponse;


    @Given("^I am validation HTTP response for get task list$")
    public void testingGetTaskList() {
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


    @And("I am creating task list on the app$")
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

    @Then("^I am moving created task list$")
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

    @Given("^I am trying to access api with wrong access token$")
    public void unAuthToken() {


        apiResponse = given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", BEARER + ACCESS_TOKEN + "WRONG")
                .urlEncodingEnabled(false)
                .when()
                .get(TASKLIST_GET_API).andReturn();
                /*
                .statusCode(401)
                .body("error.message", is("Invalid Credentials"))
                .body("error.code", is(401))
                .body("error.errors[0].domain", is("global"));
                */
    }

    @Then("^I get api access error$")
    public void unAuthResponse() {
        System.out.println(apiResponse.prettyPrint());
        assertThat(apiResponse.statusCode(), is(401));
        assertThat(apiResponse.body().jsonPath().getString("error.message"), is("Invalid Credentials"));
        assertThat(apiResponse.body().jsonPath().getString("error.errors[0].domain"), is("global"));
    }


}
