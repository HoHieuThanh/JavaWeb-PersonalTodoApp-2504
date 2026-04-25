package com.ex.personaltodoapp.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nội dung không được để trống")
    private String content;

    @FutureOrPresent(message = "Ngày phải từ hôm nay trở đi")
    private LocalDate dueDate;

    private String status;
    private String priority;
}
