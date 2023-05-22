package com.statista.code.challenge.model;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Notification {

    private String emailFrom;
    private String emailTo;
    private String subject;
    private String body;
    private String host;
}
