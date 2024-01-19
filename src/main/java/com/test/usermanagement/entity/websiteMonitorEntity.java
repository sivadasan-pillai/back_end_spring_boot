package com.test.usermanagement.entity;

import javax.persistence.*;


@Entity
@Table(name="website_monitor_status_info")
public class websiteMonitorEntity {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(name="url")
    private String url;

@Column(name = "status")
    private String status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
