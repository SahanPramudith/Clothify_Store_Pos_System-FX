package model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    private String itemcode;
    private String spplierid;
    private String itemname;
    private String supplier;
    private String size;
    private String categorie;
    private Double bynigPrice;
    private Double SellingPrice;
    private Integer Qty;
}
