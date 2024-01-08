package lk.ijse.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Item {
    private String itemId;
    private String itemName;
    private String qtyOnHand;
    private String cost;
    private String unitPrice;
}
