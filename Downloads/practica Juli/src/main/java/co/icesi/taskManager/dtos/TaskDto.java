package co.icesi.taskManager.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class TaskDto {
    private String name, description, notes;
    private  Long priority,id;
}
