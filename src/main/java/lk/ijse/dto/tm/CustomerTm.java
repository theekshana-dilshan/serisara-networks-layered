package lk.ijse.dto.tm;

import lk.ijse.dto.CustomerDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerTm {
    private String cId;
    private String name;
    private String email;
    private String address;
    private String contact;
}
