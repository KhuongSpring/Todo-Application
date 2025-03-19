package com.example.pet_project.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Category {
    STUDY("study", "Học tập", "#FF9800", "#FFFFFF"),
    WORKING("working", "Làm việc", "#4CAF50", "#FFFFFF"),
    LIVING("living", "Sinh hoạt", "#2196F3", "#FFFFFF"),
    OTHER("other", "Khác", "#9E9E9E", "#FFFFFF");

    private String value;
    private final String displayName;
    private final String color;
    private final String textColor;

    public static Category fromValue(String value) {
        for (Category category : Category.values()) {
            if (category.getValue().equalsIgnoreCase(value)) return category;
        }
        return null;
    }

}
