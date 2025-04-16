package com.asthana.Redis.Implementation.Entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Users implements Serializable {

    private String userId;
    private String name;
    private String phone;
    private String email;
}
