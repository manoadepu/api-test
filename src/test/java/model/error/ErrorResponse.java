package model.error;

import lombok.Data;

/**
 * This is error response pojo
 */
@Data
public class ErrorResponse {
    String timestamp;
    String path;
    Integer status;
    String error;
    String message;
}
