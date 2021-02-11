package com.duodrek.forumappserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    private String eventName;

    private String eventDescription;

    private String startDate;

    private String finishDate;

    @ManyToMany(mappedBy = "attendedEvents")
    private Set<User> attendees;
}
