package model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Supplier {
    private String supid;
    private String supname;
    private String titel;
    private String contact ;
    private String company;
    private String mail;
    private String itemname;
//    private String itemcode;
//    private Integer qty;
}
