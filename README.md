# Cassandra Nosql for docker

## Requirements
1. Docker image cassandra
2. JAVA -> java8
3. Play Framework 2.5
4. Scala 2.11
 
# create cassandra table 
### Create Keyspace
CREATE KEYSPACE IF NOT EXISTS login
WITH replication = {
	'class' : 'SimpleStrategy',
	'replication_factor' : 3
};

### Create table
CREATE TABLE IF NOT EXISTS login.user (
	id uuid,
	name text,
	password text,
	PRIMARY KEY (id)
);
### Create index for search user name
CREATE INDEX IF NOT EXISTS by_name ON login.user (name);

### insert user data
INSERT into user(id,name,password)VALUES(now(),'neil','1234');


## using project
git cloen project 

### download docker cassandra
<h5>docker pull cassandra</h5>

#### run image:
open cassandra for docker port 

docker run -it --name cassandra_login -p 7199:7199 -p 8888:8888 -p 9016:9016 -p 9042:9042 -p 9160:9160 -p 50031:50031 -p 61620:61620 -p 61621:61621  cassandra


test code using intellij IDEA Framework 

### Success
check cassandra user table username and password is true

success return to index show message "user name"

### Failed
Loing Failed return message "Invalid name or password"
