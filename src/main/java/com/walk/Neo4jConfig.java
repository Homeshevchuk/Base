package com.walk;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.server.Neo4jServer;

import org.springframework.data.neo4j.server.RemoteServer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableNeo4jRepositories(basePackages = "hello")
@EnableTransactionManagement
public class Neo4jConfig extends Neo4jConfiguration {

    @Bean
    public Neo4jServer neo4jServer() {
        return new RemoteServer("http://localhost:7474/", "neo4j", "123123123");
    }

    @Bean
    public SessionFactory getSessionFactory() {
        // with domain entity base package(s)
        return new SessionFactory("com.walk.Entity");
    }

    // needed for session in view in web-applications
    @Bean
    public Session getSession() throws Exception {
        return super.getSession();
    }
}

