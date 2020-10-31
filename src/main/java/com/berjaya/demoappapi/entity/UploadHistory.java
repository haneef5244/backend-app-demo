package com.berjaya.demoappapi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "UploadHistory", schema = "public")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class UploadHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "moduleName")
    private String moduleName;

    @Column(name = "majorType")
    private String majorType;

    @Column(name = "mainType")
    private String mainType;

    @Column(name = "fileType")
    private String fileType;

    @Column(name = "fileSize")
    private Long fileSize;

    @Column(name = "fileName")
    private String fileName;

    @Lob
    @Column(name = "data")
    private byte[] data;

    @Column(name = "email")
    private String email;
}
