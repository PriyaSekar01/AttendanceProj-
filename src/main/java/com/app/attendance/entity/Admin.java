package com.app.attendance.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.app.attendance.auditing.AuditWithBaseEntity;
import com.app.attendance.util.PasswordUtil;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="admin")
public class Admin extends AuditWithBaseEntity implements UserDetails, Serializable{
	
	private static final long serialVersionUID = 1L;
    @Column(name="id")
	private UUID id; 
	
	@Column(name="admin_name")
	private String name;
	
	@Column(name="email")
    private String email;
	
	@Column(name="phone_num")
    private String phoneNumber;
	
	@Column(name="password")
    private String password;
	
	
	public void setAndEncryptPassword(String password) {
		setPassword(PasswordUtil.getEncryptedPassword(password));
	}


	@Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name; // Assuming `name` is the username field
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Return the authorities granted to the user
        return null;
    }
}