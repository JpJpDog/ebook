package org.reins.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.reins.demo.model.User;

import javax.persistence.*;

@Data
@Table(name = "user")
@Entity
@NoArgsConstructor
public class UserE {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    String name;
    String password;
    Integer money;

    public UserE(User user) {
        this.name = user.getName();
        this.password = user.getPassword();
        this.money = user.getMoney();
    }
}
