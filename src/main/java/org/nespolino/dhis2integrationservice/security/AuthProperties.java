package org.nespolino.dhis2integrationservice.security;

import lombok.Data;

@Data
public abstract class AuthProperties {
    private String username;
    private String password;

}
