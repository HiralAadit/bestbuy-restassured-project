package com.bestbuy.testsuite;

import com.bestbuy.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.*;

/**
 * Created by Jay
 */
public class ProductsAssertionTest extends TestBase {

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

//11. Verify the if the total is equal to 51957
        @Test
        public void test11() {
            response.body("total",equalTo(51957));

        }
//            12. Verify the if the stores of limit is equal to 10
@Test
public void test12() {
    response.body("limit",equalTo(10));

}
//            13. Check the single ‘Name’ in the Array list (Duracell - AAA Batteries (4-Pack))
@Test
public void test13() {
    response.body("data.name",hasItem("Duracell - AAA Batteries (4-Pack)"));
}
//            14. Check the multiple ‘Names’ in the ArrayList (Duracell - AA 1.5V CopperTop Batteries (4-
//            Pack), Duracell - AA Batteries (8-Pack), Energizer - MAX Batteries AA (4-Pack))
@Test
public void test14() {
   response.body("data.name", hasItems("Duracell - AA 1.5V CopperTop Batteries (4-Pack)", "Duracell - AA Batteries (8-Pack)", "Energizer - MAX Batteries AA (4-Pack)"));
}

//            15. Verify the productId=150115 inside categories of the third name is “Household Batteries”
@Test
public void test15() {
    response.body("data[3].id", equalTo(150115))
            .body("data[3]categories[2].name", equalTo("Household Batteries"));
}
//            16. Verify the categories second name = “Housewares” of productID = 333179
@Test
public void test16() {
    response.body("data[3].categories[1].name", equalTo("Housewares"))
            .body("data[8].id",equalTo(333179));
}
//17. Verify the price = 4.99 of forth product
@Test
public void test17() {
    response.body("data[3].price",anything("4.99"));

}
//18. Verify the Product name = Duracell - D Batteries (4-Pack) of 6th product
    @Test
public void test18() {
    response.body("data[5].name",equalTo("Duracell - D Batteries (4-Pack)"));
}
//19. Verify the ProductId = 333179 for the 9th product
@Test
public void test19() {
    response.body("data[8].id",equalTo(333179));
}
//20. Verify the prodctId = 346575 have 5 categories
    @Test
public void test20() {
    response.body("data[9].categories.size",equalTo(5));
}
    }



