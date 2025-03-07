package com.heavydelay.BandsSync.Api.model.dto.user.location;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class LocationRequestDto {

    public interface CountryView {}
    public interface StateView {}
    public interface CityView {}
    public interface PostalCodeView {}
    public interface AddressView {}
    public interface LocationView extends CountryView, StateView, CityView, PostalCodeView, AddressView {}

    @JsonView(CountryView.class)
    @NotBlank(groups = {CountryView.class}, message = "Country cannot be blank")
    @Size(groups = {CountryView.class}, min = 3, max = 100, message = "Country cannot exceed 100 characters and cannot be less than 3 characters")
    private String country;
    
    @JsonView(StateView.class)
    @NotBlank(groups = {StateView.class}, message = "State cannot be blank")
    @Size(groups = {StateView.class}, min = 2, max = 100, message = "State cannot exceed 100 characters and cannot be less than 2 characters")
    private String state;
    
    @JsonView(CityView.class)
    @NotBlank(groups = {CityView.class}, message = "City cannot be blank")
    @Size(groups = {CityView.class}, min = 2, max = 100, message = "City cannot exceed 100 characters and cannot be less than 2 characters")
    private String city;
    
    @JsonView(PostalCodeView.class)
    @NotBlank(groups = {PostalCodeView.class}, message = "Postal code cannot be blank")
    @Size(groups = {PostalCodeView.class}, min = 4, max = 20, message = "Postal code cannot exceed 20 characters and cannot be less than 4 characters")
    private String postalCode;
    
    @JsonView(AddressView.class)
    @NotBlank(groups = {AddressView.class}, message = "Addres cannot be blank")
    @Size(groups = {AddressView.class}, min = 5, max = 255, message = "Addres cannot exceed 255 characters and cannot be less than 5 characters")
    private String address;
    
}
