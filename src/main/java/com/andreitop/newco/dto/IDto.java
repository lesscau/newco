package com.andreitop.newco.dto;

import java.io.Serializable;

public interface IDto extends Serializable {
    void setId(Long i);

    Long getId();
}
