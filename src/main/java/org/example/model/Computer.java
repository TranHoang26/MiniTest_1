package org.example.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Computer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @Pattern(regexp = "^AB-\\w{3}$" , message = "Sai định dạng mã máy , vui lòng nhập theo định dạng sau :AB-XXX")
    private String code;
    @NotEmpty(message = "Tên không được để trống")
    private String name;
    private String producer;
    @Min(value = 500 , message = "Giá thấp nhất phải trên 500")
    private double price;

    @ManyToOne
    private Type type;


}