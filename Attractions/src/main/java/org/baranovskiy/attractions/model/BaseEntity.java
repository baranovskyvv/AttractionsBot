package org.baranovskiy.attractions.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;


@MappedSuperclass
@Getter
@Setter
@ToString
public class BaseEntity implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)

    private Long id;

}
