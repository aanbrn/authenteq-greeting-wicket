package com.authenteq.greeting.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class Credentials implements Serializable {

    private String username;

    private String password;

}
