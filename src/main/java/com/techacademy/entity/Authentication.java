package com.techacademy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

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
        一般, 管理者
    }

    /** 主キー。社員番号。20桁 */
    @Id
    @Column(length = 20, nullable = false)
    @NotEmpty(message = "{code.notempty}")
    @Length(max=20, message = "{code.length}")
    private String code;

    /** パスワード。255桁*/
    @Column(length = 255, nullable = false)
    @NotEmpty(message = "{password.notempty}")
    @Length(max=255, message = "{password.length}")
    private String password;

    /** 権限 10桁*/
    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    /** 従業員テーブルのID */
    @OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName="id")
    private Employee employee;

    @Column(name = "login_employee")
    private String loginEmployee;
    public String getLoginEmployee() {
        return loginEmployee;
    }
}