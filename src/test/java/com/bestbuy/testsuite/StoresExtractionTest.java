package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * Created by Jay
 */
public class StoresExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }

//        1. Extract the limit
    @Test
    public void test001(){

        int limit= response.extract().path("limit");
        System.out.println("-------StartingTest---------");
        System.out.println("The value of limit is : " +limit);
        System.out.println("-------End of Test---------");
    }

//2. Extract the total
@Test
public void test002(){

    int total= response.extract().path("total");
    System.out.println("-------StartingTest---------");
    System.out.println("The value of total is : " +total);
    System.out.println("-------End of Test---------");
}

//3. Extract the name of 5th store
@Test
public void test003() {
    String name = response.extract().path("data[4].name");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The name of 5th store is : " + name);
    System.out.println("------------------End of Test---------------------------");

}

//4. Extract the names of all the store
@Test
public void test004() {
    List<String> listOfNames=response.extract().path("data.name");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("List of Ids are : "+ listOfNames);
    System.out.println("------------------End of Test---------------------------");

}
//5. Extract the storeId of all the store
@Test
public void test005() {
    List<Integer> listOfIds=response.extract().path("data.id");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("List of Ids are : "+ listOfIds);
    System.out.println("------------------End of Test---------------------------");

}
//6. Print the size of the data list
    @Test
    public void test006() {
        List<Objects> listOfdata=response.extract().path("data");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of the data is : "+listOfdata.size());
        System.out.println("------------------End of Test---------------------------");
    }

//7. Get all the value of the store where store name = St Cloud
@Test
public void test007() {
List<HashMap<String,?>>storename=response.extract().path("data.findAll{it.name == 'St Cloud'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of the store where store name = St Cloud are: "+storename);
        System.out.println("------------------End of Test---------------------------");
}

//8. Get the address of the store where store name = Rochester
    @Test
public void test008() {
   // List<HashMap<String,?>>addressofstorename=response.extract().path("data.findAll{it.addeess == 'Rochester'}");
    List<String>addressofstorename=response.extract().path("data.findAll{it.name == 'Rochester'}.address");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The address of the store where store name = Rochester : "+addressofstorename);
    System.out.println("------------------End of Test---------------------------");
}


//9. Get all the services of 8th store
    @Test
public void test009() {

        List<HashMap<String,?>>storeServices=response.extract().path("data[7].services");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The  all the services of 8th store : "+storeServices);
    System.out.println("------------------End of Test---------------------------");
}

//10. Get storeservices of the store where service name = Windows Store
@Test
public void test010() {

    List<HashMap<String,?>>storeServices=response.extract().path("data.findAll{it.services=='Windows Store'}.storeServices");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The  Get storeservices of the store where service name = Windows Store "+storeServices);
    System.out.println("------------------End of Test---------------------------");
}
//11. Get all the storeId of all the store
@Test
public void test011() {
    List<Integer> listOfAllStoreId=response.extract().path("data.services.storeservices.storeId");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Get all the storeId of all the store : "+ listOfAllStoreId);
    System.out.println("------------------End of Test---------------------------");

}

//12. Get id of all the store
    @Test
public void test012() {
    List<Integer> listStoreIds=response.extract().path("data.id");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Get id of all the store: "+listStoreIds);
    System.out.println("------------------End of Test---------------------------");

}
//13. Find the store names Where state = ND
@Test
public void test013() {
    //List<String> storeName = response.extract().path("data.findAll{it.state == 'ND'}.name");
    List<String>storeName=response.extract().path("data.findAll{it.state='ND'}");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The store names Where state 'ND' are : " + storeName);
    System.out.println("------------------End of Test---------------------------");
}

//14. Find the Total number of services for the store where store name = Rochester
@Test
public void test014() {

    List<Integer>totalNoOfServices=response.extract().path("data.findAll{it.name='Rochester'}");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Find the Total number of services for the store where store name = Rochester : " +totalNoOfServices.size());
    System.out.println("------------------End of Test---------------------------");
}
    /*public void test014() {
        List<HashMap<String, ?>> services = response.extract().path("data.findAll{it.name == 'Rochester'}.services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Total number of services for the store where store name 'Rochester' are : " + services);
        System.out.println("------------------End of Test---------------------------");
    }

     */

//15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void test015(){
       List<String>listOfcreatedAt=response.extract().path("data.findAll{it.name=='Windows Store'}.createdAt");
       // List<?> createdAtWindowsStore = response.extract().path("data.findAll{it.name=='Windows Store'}.services.find{it.createdAt}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the createdAt for all services whose name = Windows Store " +listOfcreatedAt);
        System.out.println("------------------End of Test---------------------------");

    }
//            16. Find the name of all services Where store name = “Fargo”
@Test
public void test016(){
    List<HashMap<String,?>>listOfServices=response.extract().path("data.findAll{it.name=='Fargo'}.services");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The  Find the name of all services Where store name = Fargo "+listOfServices);
    System.out.println("------------------End of Test---------------------------");

}

//            17. Find the zip of all the store
    @Test
    public void test017(){
        List<Integer>listOfZip=response.extract().path("data.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the zip of all the store = Fargo "+listOfZip);
        System.out.println("------------------End of Test---------------------------");
    }
//18. Find the zip of store name = Roseville
@Test
public void test018(){
    List<Integer>zipOfStore=response.extract().path("data.findAll{it.name=='Roseville'}.zip");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Find the zip of store name = Roseville "+zipOfStore);
    System.out.println("------------------End of Test---------------------------");
}
//19. Find the storeservices details of the service name = Magnolia Home Theater
@Test
public void test019(){
    //List<String>listOfStoreService=response.extract().path("data.findAll{it.name==Magnolia Home Theater}.storeservices");
    List<?> storeservices = response.extract().path("data.findAll{it.name=='Magnolia Home Theater'}.services");
    //List<?>storeservices=response.extract().path("data.find{it.services}.services.findAll{it.name=='Magnolia Home Theater'}");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The storeservices of service name = Magnolia Home Theater: "+storeservices);
    System.out.println("------------------End of Test---------------------------");
}

//20. Find the lat of all the stores
@Test
public void test020(){
    List<Integer>listOflat=response.extract().path("data.lat");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Find the lat of all the stores"+listOflat);
    System.out.println("------------------End of Test---------------------------");
}

}
