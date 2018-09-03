package com.andreitop.newco.dto;

import javax.validation.constraints.*;

public class TripDto implements IDto {

    private static final long serialVersionUID = 5914366185889783660L;

    @NotNull
    @PositiveOrZero
    private Long id;

    @NotEmpty
    @Pattern(regexp = "/^[A-Z]{3}$/", message = "airport code should be right format")
    private String origin;

    @NotEmpty
    @Pattern(regexp = "/^[A-Z]{3}$/", message = "airport code should be right format")
    private String destination;

    @NotNull
    @Positive
    private Integer price;

    public TripDto() {
    }

    public TripDto(Long id, String origin, String destination, Integer price) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
