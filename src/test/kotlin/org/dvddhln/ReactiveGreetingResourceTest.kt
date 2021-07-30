package org.dvddhln

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class ReactiveGreetingResourceTest {

    @Test
    fun testTodosEndpoint() {
        given()
          .`when`().get("/v1/todos")
          .then()
             .statusCode(200)
    }

}