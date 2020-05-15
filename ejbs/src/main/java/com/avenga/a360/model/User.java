package com.avenga.a360.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "User.getUserByUserName", query = "SELECT u FROM User u where u.userName = :userName"),
//    @NamedQuery(name = "User.deleteUserByUserName", query = "DELETE u FROM User u WHERE u.userName = :userName"),
        @NamedQuery(name = "User.updateUserByUserName", query = "UPDATE User u SET u.role = :role WHERE u.userName = :userName")
})
@NamedNativeQuery(
        name = "User.getAllUsers",
        query = "select * from users",
        resultClass = User.class
)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(name = "password", nullable = false, unique = true)
    private String password;



}
