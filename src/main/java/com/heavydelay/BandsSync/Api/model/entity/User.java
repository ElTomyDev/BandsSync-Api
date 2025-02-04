package com.heavydelay.BandsSync.Api.model.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.heavydelay.BandsSync.Api.enums.UserStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;
    
    @ManyToOne
    @JoinColumn(name = "id_location")
    private Location location;
    
    @ManyToOne
    @JoinColumn(name = "id_social")
    private SocialLinks socialLinks;
    

    @Column(name = "image_url", nullable = true)
    private String imageURL;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "lastname", nullable = false)
    private String lastname;
    
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    
    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "status", nullable = false)
    @Builder.Default
    private UserStatus status = UserStatus.active;

    @Column(name = "phone_number", nullable = true)
    private String phoneNumber;
    
    @Column(name = "find_bands", nullable = false)
    @Builder.Default
    private Boolean findBands = false;
    
    @Column(name = "last_failed_login", nullable = true)
    private LocalDateTime lastFailedLogin;

    @Column(name = "failed_login_attempts", nullable = false)
    @Builder.Default
    private Integer failedLoginAttempts = 0;

    @Column(name = "last_connection", nullable = true)
    private LocalDateTime lastConnection;

    @CreationTimestamp
    @Column(name = "create_date", nullable = false, updatable = false)
    private LocalDateTime createDate;
}
