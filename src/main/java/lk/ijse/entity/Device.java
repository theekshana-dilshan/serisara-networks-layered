package lk.ijse.entity;

import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Device {
    private String deviceId;
    private String dName;
    private String problem;
    private String status;
    private String cost;
    private LocalDate date;
    private String cId;
}
