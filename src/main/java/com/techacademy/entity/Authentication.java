package com.techacademy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import lombok.Data;

@Data
@Entity
@Table(name= "authentication")
public class Authentication {

    /** 主キー。社員番号。20桁 */
    @Id
    @Column(length = 20, nullable = false)
    private String code;

    /** パスワード。255桁*/
    @Column(length = 255, nullable = false)
    private String password;

    /** 権限 10桁*/
    @Column(length = 10, nullable = false)
    private String role;

    /** 従業員テーブルのID */
    @OneToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

}