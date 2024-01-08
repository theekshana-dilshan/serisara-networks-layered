package lk.ijse.entity;

import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Payment {
    private String pId;
    private double amount;
    private String status;
    private LocalDate date;
    private String oId;
}
