package com.Subhadeep.Assignment.model;

import javax.persistence.Embeddable;

@Embeddable
public class Tag {
    private Long ID;
    private String TagName;

    public Tag() {
    }

    public Tag(Long tagID, String tagName) {
        this.ID = tagID;
        this.TagName = tagName;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long tagID) {
        this.ID = tagID;
    }

    public String getTagName() {
        return TagName;
    }

    public void setTagName(String tagName) {
        this.TagName = tagName;
    }
}

