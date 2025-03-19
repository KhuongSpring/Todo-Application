package com.example.pet_project.dtos.responses;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskResponse {
    Long id;
    String taskName;
    Boolean completed;
    LocalDateTime createdAt;
    String categoryName;
    String categoryColor;
}
