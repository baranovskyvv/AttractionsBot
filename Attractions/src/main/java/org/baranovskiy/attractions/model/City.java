package org.baranovskiy.attractions.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="cities")
@Getter
@Setter
@ToString
public class City extends BaseEntity implements Serializable {
    @Column(name="city")
    private String city;
    @Column(name="description")
    private  String description;
}
