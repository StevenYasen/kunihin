package com.kunikhin.kata313.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rolename;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    public Role() {
    }

    public Role(String rolename) {
        this.rolename = rolename;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    @Override
    public String getAuthority() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (!id.equals(role.id)) return false;
        if (!rolename.equals(role.rolename)) return false;
        return users.equals(role.users);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + rolename.hashCode();
        result = 31 * result + users.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", rolename='" + rolename + '\'' +
                ", users=" + users +
                '}';
    }

}
