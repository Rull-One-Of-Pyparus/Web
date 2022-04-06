package com.example.web.Config.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "status")
public class Status {
    @Id
    private Long id;
    private String name;
}
