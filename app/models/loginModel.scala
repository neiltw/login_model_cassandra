package models

import com.datastax.driver.core.Session
import com.datastax.driver.core.querybuilder.QueryBuilder

/**
  * Created by Neil on 6/12/16.
  */

case class user(name:String,password:String)

object loginModel {

  val KEYSPACE = "login"
  val USER_TABLE = "user"

  /**
    * login page check username is exist
    * true: return index page
    * false: return login page
    */
  def authenticate(name:String,password:String,cassandra_session:Session): Boolean= {
    val query_by_user = QueryBuilder.select().from(KEYSPACE,USER_TABLE).where(QueryBuilder.eq("name",name))
    val resultByUser = cassandra_session.executeAsync(query_by_user)
    val data =  resultByUser.get().one()
    cassandra_session.closeAsync()
    if(data == null){
      println("check username is not null ")
      true
    }else{
      if( (name.equals(data.getString("name")) && password.equals(data.getString("password")) ) ){
        println("success good")
        false
      }else{
        println("username and password fail")
        true
      }
    }

  }
}
