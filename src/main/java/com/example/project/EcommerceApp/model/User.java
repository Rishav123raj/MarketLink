package com.example.project.EcommerceApp.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {

    public User(User user) {
        // TODO Auto-generated constructor stub
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(nullable = false)
    private String firstname;

    private String lastname;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    @ManyToMany
    @JoinTable(name = "user_role", // Name of the join table in the database
            joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") }, // Column in the join table
                                                                                          // that references the User
                                                                                          // entity
            inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") } // Column in the join
                                                                                                // table that references
                                                                                                // the Role entity
    )
    private List<Role> roles;
}
