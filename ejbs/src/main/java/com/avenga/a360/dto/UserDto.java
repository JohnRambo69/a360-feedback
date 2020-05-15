package com.avenga.a360.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class UserDto {
    private Long id;
    private String role;
    private String userName;
    private String password;
}