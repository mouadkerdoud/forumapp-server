package com.duodrek.forumappserver.model;

import javax.persistence.*;

@Entity
@Table(name="docs")
public class Doc {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String docName;
    private String docType;

    @Lob
    private byte[] data;

    private Long userId;

    private Long eventId;

    private Long postId;

    public Doc() {}

    public Doc(String docName, String docType, byte[] data, Long userId) {
        super();
        this.docName = docName;
        this.docType = docType;
        this.data = data;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}