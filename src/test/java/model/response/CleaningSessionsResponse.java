package model.response;

import lombok.Data;

import java.util.List;

/**
 * This is cleaning-session response body pojo
 */
@Data
public class CleaningSessionsResponse {
    private List<Integer> coords;
    private int patches;
}
