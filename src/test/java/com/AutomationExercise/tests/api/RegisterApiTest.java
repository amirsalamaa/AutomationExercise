package com.AutomationExercise.tests.api;
import com.AutomationExercise.apis.UserManagementAPI;
import com.AutomationExercise.tests.BaseTest;
import com.AutomationExercise.utils.TimeManager;
import com.AutomationExercise.utils.dataReader.JsonReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RegisterApiTest extends BaseTest {


    String timeStamp = TimeManager.getTimestamp();
    @Test
    public void ValidRegisterTCApi() {
        new UserManagementAPI().createRegisterUserAccount(
                testData.getJsonData("name"),
                (testData.getJsonData("email") + timeStamp + ("@gmail.com")),
                testData.getJsonData("password"),
                testData.getJsonData("titleMale"),
                testData.getJsonData("day"),
                testData.getJsonData("month"),
                testData.getJsonData("year"),
                testData.getJsonData("firstName"),
                testData.getJsonData("lastName"),
                testData.getJsonData("companyName"),
                testData.getJsonData("address1"),
                testData.getJsonData("address2"),
                testData.getJsonData("country"),
                testData.getJsonData("state"),
                testData.getJsonData("city"),
                testData.getJsonData("zipCode"),
                testData.getJsonData("mobileNumber")
        ).verifyUserCreatedSuccessfully();
    }

    @BeforeClass
    public void preCondition() {
        testData = new JsonReader("registerData");
    }
}

