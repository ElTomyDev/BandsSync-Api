package com.heavydelay.BandsSync.Api.model.entity;

import java.time.LocalDateTime;

import com.heavydelay.BandsSync.Api.enums.UserStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/*
    Esta es la entidad del usuario que pertenece a la tabla 'users'

    que falta:
        - vincular las claves foraneas de roles, locations, social_links
          (estas tablas todavia no estan listas por eso no se agregaron).
*/

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

    // crear clave foranea vinculada a 'role'
    // crear clave foranea vinculada a 'location'
    // crear clave foranea vinculada a 'social'

    @Column(name = "image_url", nullable = true)
    private String imageUrl;

    @Column(name = "name", nullable = false)
    @Size(max = 100)
    private String name;

    @Column(name = "lastname", nullable = false)
    @Size(max = 100)
    private String lastname;
    
    @Column(name = "username", nullable = false, unique = true)
    @Size(min = 4, max = 50)
    private String username;
    
    @Column(name = "description", nullable = true)
    @Size(max = 360)
    private String description;

    @Column(name = "status")
    @Builder.Default
    private UserStatus status = UserStatus.active;

    @Column(name = "phone_number", nullable = true)
    @Size(max = 25)
    @Builder.Default
    private String phoneNumber = null;
    
    @Column(name = "find_bands")
    @Builder.Default
    private Boolean findBands = false;
    
    @Column(name = "last_failed_login", nullable = true)
    @Builder.Default
    private LocalDateTime lastFailedLogin = null;

    @Column(name = "failed_login_attempts")
    @Builder.Default
    private Integer failedLoginAttempts = 0;

    @Column(name = "last_connection", nullable = true)
    @Builder.Default
    private LocalDateTime lastConnection = null;

    @Column(name = "create_date")
    @Builder.Default
    private LocalDateTime createDate = LocalDateTime.now();
}
