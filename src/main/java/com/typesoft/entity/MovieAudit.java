package com.typesoft.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity(name = "MOVIE_AUDIT")
public class MovieAudit {

    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "movieName")
    private String movieName;

    @Column(name = "requestTimestamp")
    private LocalDateTime requestTimestamp;

    @Column(name = "response")
    private String response;

    public MovieAudit(String username, String movieName, String response) {
        this.username = username;
        this.movieName = movieName;
        this.response = response;
        this.requestTimestamp = LocalDateTime.now();
    }
}
