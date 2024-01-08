package lk.ijse.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {
    private String cId;
    private String name;
    private String email;
    private String address;
    private String contact;
    private String userId;
}
