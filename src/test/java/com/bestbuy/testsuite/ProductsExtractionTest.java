package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public class ProductsExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }
//21. Extract the limit
@Test
public void test021(){

    int limit= response.extract().path("limit");
    System.out.println("-------StartingTest---------");
    System.out.println("The value of limit is : " +limit);
    System.out.println("-------End of Test---------");
}

//22. Extract the total
public void test022(){

    int total= response.extract().path("total");
    System.out.println("-------StartingTest---------");
    System.out.println("The value of total is : " +total);
    System.out.println("-------End of Test---------");
}
//23. Extract the name of 5th product
@Test
public void test023() {
    String name = response.extract().path("data[4].name");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The name of 5th store is : " + name);
    System.out.println("------------------End of Test---------------------------");

}

//24. Extract the names of all the products
public void test024() {
    List<String> listOfNames=response.extract().path("data.name");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("List of Ids are : "+ listOfNames);
    System.out.println("------------------End of Test---------------------------");

}
//25. Extract the productId of all the products
@Test
public void test025() {
    List<Integer> listOfIds=response.extract().path("data.id");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("List of Ids are : "+ listOfIds);
    System.out.println("------------------End of Test---------------------------");

}
//26. Print the size of the data list
@Test
public void test026() {
    List<Objects> listOfdata=response.extract().path("data");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The size of the data is : "+listOfdata.size());
    System.out.println("------------------End of Test---------------------------");
}
//27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
@Test
public void test027() {
    List<HashMap<String,?>>productname=response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The value of the store where product name = Energizer - MAX Batteries AA (4-Pack): "+productname);
    System.out.println("------------------End of Test---------------------------");
}
//28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
@Test
public void test028() {
    // List<HashMap<String,?>>addressofstorename=response.extract().path("data.findAll{it.addeess == ' Energizer - N Cell E90 Batteries (2-Pack)'}");
    List<String>addressofproductname=response.extract().path("data.findAll{it.name == ' Energizer - N Cell E90 Batteries (2-Pack)'}.address");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The address of the store where store name = Energizer - N Cell E90 Batteries (2-Pack): "+addressofproductname);
    System.out.println("------------------End of Test---------------------------");
}
//29. Get all the categories of 8th products
    @Test
public void test029() {

    List<HashMap<String,?>>productCategories=response.extract().path("data[7].categories");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The  all the services of 8th store : "+productCategories);
    System.out.println("------------------End of Test---------------------------");
}
//30. Get categories of the store where product id = 150115
@Test
    public void test030() {

    List<HashMap<String,?>>productCategories=response.extract().path("data.findAll{it.id==150115}.categories");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The Get categories of the store where product id = 150115 "+productCategories);
    System.out.println("------------------End of Test---------------------------");
}
//31. Get all the descriptions of all the products
@Test
public void test031() {
    List<Integer> listOfAlldescriptions=response.extract().path("data.description");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Get all the storeId of all the store : "+ listOfAlldescriptions);
    System.out.println("------------------End of Test---------------------------");

}
//32. Get id of all the all categories of all the products
@Test
public void test032() {
    List<String> ids = response.extract().path("data.categories.id");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The id of all the all categories of all the products : " + ids);
    System.out.println("------------------End of Test---------------------------");
}
//33. Find the product names Where type = HardGood
@Test
public void test033() {

    List<String>productName=response.extract().path("data.findAll{it.type='HardGood'}.name");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The store names Where state 'ND' are : " +productName);
    System.out.println("------------------End of Test---------------------------");
}

//34. Find the Total number of categories for the product where product name = Duracell - AA
//1.5V CopperTop Batteries (4-Pack)
@Test
public void test034() {

    List<Integer>totalNoOfcategories=response.extract().path("data.findAll{it.name='Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Find the Total number of services for the store where store name = Rochester : " +totalNoOfcategories.size());
    System.out.println("------------------End of Test---------------------------");
}
//35. Find the createdAt for all products whose price < 5.49
@Test
public void test035(){
    List<String>listOfcreatedAt=response.extract().path("data.findAll{it.price=='5.49'}.createdAt");
    // List<?> createdAtWindowsStore = response.extract().path("data.findAll{it.name=='Windows Store'}.services.find{it.createdAt}");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Find the createdAt for all products whose price < 5.49 " +listOfcreatedAt);
    System.out.println("------------------End of Test---------------------------");

}


//            36. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4- Pack)”
@Test
public void test036(){
    List<HashMap<String,?>>listOfcategories=response.extract().path("data.findAll{it.name=='Energizer - MAX Batteries AA (4- Pack)'}.categories.name");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The  Find the name of  categories Where product name = “Energizer - MAX Batteries AA (4- Pack)"+listOfcategories);
    System.out.println("------------------End of Test---------------------------");

}

//            37. Find the manufacturer of all the products
@Test
public void test037(){
    List<HashMap<String,?>>listOfmanufacturer=response.extract().path("data.manufacturer");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The  Find the name of  categories Where product name = “Energizer - MAX Batteries AA (4- Pack)"+listOfmanufacturer);
    System.out.println("------------------End of Test---------------------------");

}
//38. Find the imge of products whose manufacturer is = Energizer
@Test
public void test038(){
    List<String>imageofproduct=response.extract().path("data.findAll{it.manufacturer=='Energizer'}.image");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Find the zip of store name = Roseville "+imageofproduct);
    System.out.println("------------------End of Test---------------------------");
}
//39. Find the createdAt for all categories products whose price > 5.99
@Test
public void test019() {
    List<String> createdAt = response.extract().path("data.findAll{it.price > 5.99}.categories.createdAt");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The createdAt for all categories products whose price > 5.99 : " + createdAt);
    System.out.println("------------------End of Test---------------------------");
}
//            40. Find the uri of all the product
@Test
public void test020(){
    List<Integer>listOfurl=response.extract().path("data.url");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Find the lat of all the stores"+listOfurl);
    System.out.println("------------------End of Test---------------------------");
}

}
