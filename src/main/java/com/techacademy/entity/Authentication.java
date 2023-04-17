package com.techacademy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Data;

@Data
@Entity
@Table(name= "authentication")
public class Authentication {

    public static enum Role {
        一般,管理者
    }

    /** 主キー。社員番号。20桁 */
    @Id
    @Column(length = 20, nullable = false)
    private String code;

    /** パスワード。255桁*/
    @Column(length = 255, nullable = false)
    private String password;

    /** 権限 10桁*/
    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    /** 従業員テーブルのID */
    @OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName="id", nullable = false)
    private Employee employee;

}