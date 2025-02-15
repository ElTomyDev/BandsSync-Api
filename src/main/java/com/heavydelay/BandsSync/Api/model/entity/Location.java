package com.heavydelay.BandsSync.Api.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_location")
    private Long idLocation;

    @Column(name = "country", nullable = true)
    private String country;

    @Column(name = "state", nullable = true)
    private String state;

    @Column(name = "city", nullable = true)
    private String city;

    @Column(name = "postal_code", nullable = true)
    private String postalCode;
    
    @Column(name = "address", nullable = true)
    private String address;

}
