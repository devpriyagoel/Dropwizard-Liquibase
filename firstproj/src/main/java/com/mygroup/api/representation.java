package com.mygroup.api;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

public class representation<T>{
    private long code;
    private T data;
    public representation() {
    }

    public representation(long code, T data) {
        this.code = code;
        this.data = data;
    }

    @JsonProperty
    public long getCode() {
        return code;
    }

    @JsonProperty
    public T getData() {
        return data;
    }

}