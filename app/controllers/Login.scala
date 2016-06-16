package controllers

import cassandraDAO.Cassandra
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{Action, Controller}

/**
  * Created by Neil on 6/12/16.
  */
class Login extends Controller with Cassandra{

  val loginForm = Form(
    mapping(
      "name" -> text,
      "password" -> text
    )(models.user.apply)(models.user.unapply)
      verifying ("Invalid name or password", result => result match {
      case user => models.loginModel.authenticate(user.name,user.password,cassandra_session) == false
    })
  )


  def login = Action { implicit request =>
    println("login: "+request.body.asFormUrlEncoded)
    Ok(views.html.login(loginForm))
  }

  def logout = Action {
    Redirect(routes.Login.login).withNewSession.flashing(
      "success" -> "You are now logged out."
    )
  }

  def authenticate = Action { implicit request =>
    loginForm.bindFromRequest.fold(
      formWithErrors => BadRequest(views.html.login(formWithErrors)),
      user => Redirect(routes.Application.index()).withSession("user" -> user.name )
    )
  }

}
