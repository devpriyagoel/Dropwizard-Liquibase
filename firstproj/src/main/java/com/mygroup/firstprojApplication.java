package com.mygroup;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import com.mygroup.resources.firstprojResource;
//import com.mygroup.health.TemplateHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.mygroup.dao.persondao;
import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.FileSystemResourceAccessor;
import liquibase.resource.ResourceAccessor;
import java.sql.*;
import org.postgresql.ds.PGPoolingDataSource;
import org.skife.jdbi.v2.*;

public class firstprojApplication extends Application<firstprojConfiguration> {
//    private static final Logger LOGGER =
//            LoggerFactory.getLogger(firstprojApplication.class);

    public static void main(final String[] args) throws Exception {
        //String url = "jdbc:postgresql://localhost:5432/sampledb?user=devpriyagoel&password=postgres";

         //server does not support ssl

        new firstprojApplication().run(args);
    }

    @Override
    public String getName() {
        return "firstproj";
    }
    @Override
    public void initialize(final Bootstrap<firstprojConfiguration> bootstrap) {
        //prepares the runtime environment of application

    }


    @Override
    public void run(final firstprojConfiguration configuration,
                    final Environment environment) throws LiquibaseException {
        System.out.println("Application is running");
//         TODO: implement application

        System.out.println("Initing DB...");

        PGPoolingDataSource source = new PGPoolingDataSource();
        source.setDatabaseName("sampledb");
        source.setServerName("localhost");
        source.setUser("devpriyagoel");
        source.setPassword("postgres");
        source.setPortNumber(5432);
        try (Connection c = source.getConnection()) {
            ResourceAccessor resourceAccessor = new FileSystemResourceAccessor();
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(c));
            Liquibase liquibase = new Liquibase("src/main/resources/migrations.xml", resourceAccessor, database);
            //liquibase.dropAll();
            liquibase.clearCheckSums();
            liquibase.update(new Contexts());
            DBI firstprojdbi = new DBI(source);
            final persondao dao = firstprojdbi.onDemand(persondao.class);
            environment.jersey().register(new firstprojResource(dao));
        }catch(SQLException e){
            System.out.println("error occured");
        }

    }

}
