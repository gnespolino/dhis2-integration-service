package org.nespolino.dhis2integrationservice.api;

import org.nespolino.dhis2integrationservice.security.AuthProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "dhis2.api-security")
class ApiAuthProperties extends AuthProperties {
}
