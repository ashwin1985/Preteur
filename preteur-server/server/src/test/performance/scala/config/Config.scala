package config

import io.gatling.core.Predef._
import io.gatling.http.Predef._

trait Config {

  val baseUrl = "http://107.170.86.37:5050"
  val numberOfUsers = 1000;
  val httpConf = http.baseURL(baseUrl)
    .contentTypeHeader("application/json")

}
