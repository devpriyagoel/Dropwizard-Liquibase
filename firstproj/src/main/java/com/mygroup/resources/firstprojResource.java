package com.mygroup.resources;
import com.mygroup.api.representation;
import com.codahale.metrics.annotation.Timed;
import com.mygroup.dao.persondao;
import com.mygroup.firstprojApplication;
import com.mygroup.model.Person;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Optional;



@Path("/firstproj")
@Produces(MediaType.APPLICATION_JSON)
public class firstprojResource {
    private persondao dao;
    public  firstprojResource(persondao dao){
        this.dao = dao;
//        dao.insertNamed( "user1","alias1" );
//        dao.insertNamed( "user2","alias2" );
//        dao.insertNamed( "user3","alias3" );
    }
    @GET
    @Path("/{id}")
    public representation<Person> sayHello(@PathParam("id") int id) {

        Person person = new Person(id, dao.findNameById(id), dao.findAliasById(id));
        representation<Person> ans = new representation<>(0, person);
        return ans;
    }
}