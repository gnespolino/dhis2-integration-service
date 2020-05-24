# DHIS2 Integration Service

run the service using:

    ./mvnw spring-boot:run
    
You can customize the service setting the following environment variables before running it:

| Environment Variable | Default value |
|----------------------|---------------|
|`DHIS2_URL`|`https://play.dhis2.org/2.33.4`|
|`DHIS2_USERNAME`|`admin`|
|`DHIS2_PASSWORD`|`district`|
|`DHIS2_RESET_CACHE_EVERY`|`PT15M`|
|`DHIS2_API_USERNAME`|`admin`|
|`DHIS2_API_PASSWORD`|`district`|
    
After launching the service, Swagger-UI is available at:

http://localhost:8080/swagger-ui.html
    