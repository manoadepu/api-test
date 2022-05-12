package model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This is cleaning session request body pojo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CleaningSessions {

    private List<Integer> roomSize;
    private List<Integer> coords;
    private List<List<Integer>> patches;
    private String instructions;

}