package simulation

import config.Config
import feeder.User
import io.gatling.core.Predef._

class SignupSimulation extends Simulation with Config {

  setUp(scenario("Sign up")
    .exec(User.postUser)
    .inject(atOnceUsers(numberOfUsers)))
    .protocols(httpConf)

}
