package com.example.backend.dto;

public class StudentResponseDTO {

    private Integer id;
    private String name;
    private String course;

    public StudentResponseDTO(
            Integer id,
            String name,
            String course) {

        this.id = id;
        this.name = name;
        this.course = course;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }
}