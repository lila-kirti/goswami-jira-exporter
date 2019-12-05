package ru.bvg.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String email;
    private String password;
    private String city;
    private Date birthDate;
    private boolean emailSubscriber;
    private String avatar;
    private String spiritual;
}
