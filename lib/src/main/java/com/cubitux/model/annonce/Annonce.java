package com.cubitux.model.annonce;

import com.cubitux.model.Category;
import com.cubitux.utils.DateUtil;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.json.simple.JSONObject;

import java.util.Date;

/**
 * A simple class to hold a single Annonce's properties
 * <p/>
 * Created by pierre on 2016-05-12.
 */
public class Annonce {

    /**
     * The unique identifier
     */
    private Long id;

    /**
     * Title
     */
    private String title;

    /**
     * Active or Inactive
     * Default: false
     */
    private Boolean status = false;

    /**
     * The categeroy
     */
    private Category category;

    /**
     * Physical path of the image on the server
     */
    private String image;

    /**
     * A description
     */
    private String description;

    /**
     * Amount of time the annonce have been viewed
     */
    private Long viewed;

    /**
     * The creation's date
     */
    private Date created;

    /**
     * Last time it was modified
     */
    private Date modified;

    /**
     * The price
     */
    private Double price;

    /**
     * Basic Constructor
     */
    public Annonce() {
        this.status = false;
        this.viewed = 0L;
        this.created = new Date();
        this.modified = new Date();
        this.description = "";
        this.title = "";
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String imagePath) {
        this.image = imagePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getViewed() {
        return viewed;
    }

    public void setViewed(Long viewed) {
        this.viewed = viewed;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return "Annonce{" +
                "id=" + id +
                ",title=" + title +
                ",status=" + status +
                ",category=" + category +
                ",image=" + image +
                ",description=" + description +
                ",viewed=" + viewed +
                ",created=" + created +
                ",modified=" + modified +
                ",price=" + price +
                '}';
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("id", this.getId());
        json.put("title", this.getTitle());
        json.put("category", this.getCategory().toString());
        json.put("viewed", this.getViewed());
        json.put("status", this.getStatus());
        json.put("price", this.getPrice());
        json.put("image", this.getImage());
        json.put("created", DateUtil.dateToString(this.getCreated()));
        json.put("modified", DateUtil.dateToString(this.getModified()));
        json.put("description", this.getDescription());
        return json;
    }
}
