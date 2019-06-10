package com.example.pharmacy.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
@Entity
public class Role  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    //@Enumerated(EnumType.STRING)
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }
}
