package com.znothings.upload;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
public class User {
    /**
     * 行id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    /**
     * 用户id
     */
    @Column(unique = true)
    String uid;
    /**
     * 用户名
     */
    @Column
    String username;
    @Column
    Integer age;
    @Column
    Date birthday;
    @Column
    byte[] photo;

}
