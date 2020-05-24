package org.nespolino.dhis2integrationservice.client;

import org.nespolino.dhis2integrationservice.security.AuthProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "dhis2")
class Dhis2AuthProperties extends AuthProperties {
}
