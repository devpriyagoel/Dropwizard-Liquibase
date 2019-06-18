package com.mygroup.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

public class Person {
    private long id;

    @Length(max = 100)
    private String name;

    @Length(max=10)
    private String alias;

    public Person(long id, String name, String alias){
        this.id = id;
        this.name = name;
        this.alias = alias;
    }
    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }
    @JsonProperty
    public String getAlias() {
        return alias;
    }
}