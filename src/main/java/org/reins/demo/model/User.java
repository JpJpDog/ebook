package org.reins.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.reins.demo.entity.UserE;

@Data
@AllArgsConstructor
public class User {
    String name;
    String password;
    Integer money;

    public User(UserE userE) {
        this.name = userE.getName();
        this.password = userE.getPassword();
        this.money = userE.getMoney();
    }
}
