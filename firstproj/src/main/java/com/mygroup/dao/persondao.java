package com.mygroup.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface persondao{
    @SqlUpdate("INSERT INTO person(name, alias) VALUES (?, ?)")
    void insertPositional(String name, String alias);
    @SqlUpdate("INSERT INTO person(name, alias) VALUES (:name, :alias)")
    void insertNamed(@Bind("name") String name, @Bind("alias") String alias);
    @SqlQuery("SELECT name from person WHERE id = :id")
    String findNameById(@Bind("id") int id);
    @SqlQuery("SELECT alias from person WHERE id = :id")
    String findAliasById(@Bind("id") int id);
}