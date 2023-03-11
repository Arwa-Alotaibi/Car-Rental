package com.example.carrental.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MyUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

   // @Column(unique = true)
    @NotNull
    private String username;



  @Pattern(regexp = "(Owner|Customer|Admin)",message = "Role must be in owner or customer only")
  private String role;

    private String password;

    @OneToOne(mappedBy = "myUser", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Customer customer;
    @OneToOne(mappedBy = "myUser", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
   private CarOwner carOwner;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
