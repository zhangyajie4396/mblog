package com.zyj.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Resource {
    private Integer id;

    private Integer pid;

    @JsonProperty(value = "text")
    private String name;

    private String url;

    private String icon;

    private Integer seq;

    private String permission;

    @JsonProperty(value = "nodes")
    private List<Resource> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getPermission() {
        return permission;
    }

    public List<Resource> getChildren() {
        return children;
    }

    public void setChildren(List<Resource> children) {
        this.children = children;
    }

    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }
}