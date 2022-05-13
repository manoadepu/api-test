package util.enums;

/**
 * Endpoints that can be used accross all the tests
 */
public enum Endpoint {
    CLEANING_SESSIONS("/cleaning-sessions"),
    FUTURE_API("/future-api");

    private String endpoint;

    Endpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
