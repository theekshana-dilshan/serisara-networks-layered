package lk.ijse.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerDto {
    private String cId;
    private String name;
    private String email;
    private String address;
    private String contact;
    private String userId;
}
