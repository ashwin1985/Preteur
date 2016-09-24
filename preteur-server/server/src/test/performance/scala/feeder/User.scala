package feeder

import io.gatling.core.Predef._
import io.gatling.core.body.StringBody
import io.gatling.http.Predef._
import com.preteur.server.dto.User
import util.JsonHelper

object User {

    var postUser = http("Create user")
      .post("/signup").body(getPostBody)

    def getPostBody : StringBody = StringBody(session => getRandomUserRequest)

    def getRandomUserRequest : String = {
      val user = new User("fname", "lname", "1234567890", "name@abc.com", "password")
      JsonHelper.json(user)
    }

}
