package com.board.board.entity;

import org.apache.tomcat.jni.Local;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@EntityListeners(value={AuditingEntityListener.class})
@MappedSuperclass
public class BaseEntity {
    @CreatedDate
    @Column(name="regdate",updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name="modadate")
    private LocalDateTime modDate;

}