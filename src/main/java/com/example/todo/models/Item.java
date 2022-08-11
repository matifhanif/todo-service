package com.example.todo.models;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table
public class Item {

    @Id
    @SequenceGenerator(
            name = "item_seq",
            sequenceName = "item_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "item_seq"
    )
    private Long id;

    @NotBlank (message = "Invalid description")
    private String desc;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dtCreated;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dtDue;

    private LocalDateTime dtCompleted;



    public Item(String desc, Status status, LocalDateTime dtCreated, LocalDateTime dtDue, LocalDateTime dtCompleted) {
        this.desc = desc;
        this.status = status;
        this.dtCreated = dtCreated;
        this.dtDue = dtDue;
        this.dtCompleted = dtCompleted;
    }

    public Item() {
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getDtCreated() {
        return dtCreated;
    }

    public void setDtCreated(LocalDateTime dtCreated) {
        this.dtCreated = dtCreated;
    }

    public LocalDateTime getDtDue() {
        return dtDue;
    }

    public void setDtDue(LocalDateTime dtDue) {
        this.dtDue = dtDue;
    }

    public LocalDateTime getDtCompleted() {
        return dtCompleted;
    }

    public void setDtCompleted(LocalDateTime dtCompleted) {
        this.dtCompleted = dtCompleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", desc='" + desc + '\'' +
                ", status=" + status +
                ", dtCreated=" + dtCreated +
                ", dtDue=" + dtDue +
                ", dtCompleted=" + dtCompleted +
                '}';
    }
}
