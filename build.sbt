name := "login_model"

version := "1.0"

lazy val `login_model` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq( jdbc , cache , ws   , specs2 % Test )

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

libraryDependencies ++= Seq(
    "com.datastax.cassandra" % "cassandra-driver-core" % "3.0.1" ,
    "com.datastax.cassandra" % "cassandra-driver-mapping" % "3.0.1"
)