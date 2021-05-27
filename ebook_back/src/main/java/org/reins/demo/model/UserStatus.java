package org.reins.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserStatus {
    private String userName;
    private Boolean active;
}
