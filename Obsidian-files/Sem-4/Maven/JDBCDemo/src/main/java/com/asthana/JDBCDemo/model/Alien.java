package com.asthana.JDBCDemo.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Alien {

private Integer Id;
private String Name;
private String Tech;
public Integer getId() {
    return Id;
}
public void setId(Integer id) {
    Id = id;
}
public String getName() {
    return Name;
}
public void setName(String name) {
    Name = name;
}
public String getTech() {
    return Tech;
}
public void setTech(String tech) {
    Tech = tech;
}

@Override
public String toString() {
    return "Alien { id = " + getId() + ", name = \"" + getName() + "\", tech = '" + getTech() + "' }";
}

}
