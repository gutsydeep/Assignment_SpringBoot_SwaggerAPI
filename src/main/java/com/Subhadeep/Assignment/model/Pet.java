package com.Subhadeep.Assignment.model;

import com.Subhadeep.Assignment.converter.StringListConverter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="pet_info")
@ApiModel(description = "This table holds pet information.")
public class Pet
{
    @Id
    @ApiModelProperty(notes="This is a Pet Id. It shall be unique.")
    private Long PetId;
    private String name;

    @Embedded
    private Category category;
    @Embedded
    private Tag tag;

    @Convert(converter = StringListConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<String> photoUrls;


    private String status;

    public Pet() {
    }

    public Pet(Long PetId, String name, Category category, List<String> photoUrls, Tag tag, String status) {
        this.PetId = PetId;
        this.name = name;
        this.category=category;
        this.photoUrls = photoUrls;
        this.tag=tag;
        this.status = status;
    }

    public Long getId() {
        return PetId;
    }

    public void setId(Long PetId) {
        this.PetId = PetId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Enums
    public enum Status {
        available, pending, sold
    }

}

