package controllers

import play.api.mvc._

class Application extends Controller {


  def index = Action { request =>
    val user = request.session.get("user").get
    Ok(views.html.index("Welcome  "+ user))
  }

}