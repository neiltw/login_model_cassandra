package cassandraDAO

import com.datastax.driver.core.{Cluster, QueryOptions, Session}

/**
  * Created by Neil on 6/12/16.
  */
trait Cassandra {

    protected def cassandra_session:Session = {
      val cluster = new Cluster.Builder().
        addContactPoint("192.168.99.100").         //       local docker cassandra
        withPort(9042).
        withQueryOptions(new QueryOptions()
        .setConsistencyLevel(QueryOptions.DEFAULT_CONSISTENCY_LEVEL)
      ).build
      val session = cluster.connect()
      session
    }
  }