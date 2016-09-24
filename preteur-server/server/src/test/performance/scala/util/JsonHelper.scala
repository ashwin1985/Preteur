package util

import com.fasterxml.jackson.databind.ObjectMapper

object JsonHelper {

  def json(bean : Object) : String = new ObjectMapper().writeValueAsString(bean)

}
