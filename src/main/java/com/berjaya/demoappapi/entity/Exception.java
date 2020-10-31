package com.berjaya.demoappapi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "Exception", schema = "public")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class Exception {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "reason")
    private String reason;
}
