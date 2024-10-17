package model;

import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employer {

    private String empid;
    private String titel;
    private String name;
    private String address;
    private String company;
    private String mail;
}
