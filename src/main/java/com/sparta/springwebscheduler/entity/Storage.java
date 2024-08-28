package com.sparta.springwebscheduler.entity;

import jakarta.persistence.*;
import lombok.Setter;

@Entity
@Table(name = "storages")
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "storage_id")
    private Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Setter
    @ManyToOne
    @JoinColumn(name ="schedule_id")
    private Schedule schedule;

}
