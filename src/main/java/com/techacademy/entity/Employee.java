package com.techacademy.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name= "employee")
public class Employee {

    /** 主キー。自動生成 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** 名前。20桁 */
    @Column(length = 20, nullable = false)
    private String name;

    /** 削除フラグ */
    @Column(name = "delete_flag", nullable = false)
    private int deleteFlag;

    /** 登録日時 */
    @Column(name = "created_at", nullable = false)
    private String createdAt;

    /** 更新日時 */
    @Column(name = "updated_at", nullable = false)
    private String updatedAt;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private Authentication authentication;

}