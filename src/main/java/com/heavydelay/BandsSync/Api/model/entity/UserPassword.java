package com.heavydelay.BandsSync.Api.model.entity;


import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "user_passwords")
public class UserPassword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_password")
    private Long idPassword;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "password_reset_token", nullable = true, unique = true)
    private String passwordResetToken;

    @Column(name = "password_reset_expiry", nullable = true)
    private String passwordResetExpiry;

    @CreationTimestamp
    @Column(name = "create_date", updatable = false, nullable = false)
    private String createDate;

}
