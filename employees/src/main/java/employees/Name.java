package employees;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Name {
    private String forename;
    private String surename;
}
