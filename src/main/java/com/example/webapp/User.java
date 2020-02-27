package com.example.webapp;


import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    @Column(name = "first_name")
    private String firstname;
    @Column(name = "last_name")
    private String lastname;
    //private String interest;
    //private int budget_ID;

    public User() {
    }

    public User(String username, String password, String email, String firstname, String lastname) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;

    }

    @Override
    public String toString() {
        return " Username: " + username  +
                " Password: " + password  +
                " Email: " + email  +
                " Firstname: " + firstname +
                " Lastname: "  + lastname;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getUsername() {
        return username;
    }

    public String getLastname() {
        return lastname;
    }

    /*public String getInterest() {
        return interest;
    }

    /*public int getBudget_ID() {
        return budget_ID;
    }*/
}
