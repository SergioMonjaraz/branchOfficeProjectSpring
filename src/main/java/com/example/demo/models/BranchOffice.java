package com.example.demo.models;

import com.example.demo.responseHandler.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "branchoffice")
public class BranchOffice implements Serializable {

    @Id
    @Column(updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    @JsonView(Views.BranchOfficeView.class)
    private UUID id;

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(50)")
    @JsonView(Views.BranchOfficeView.class)
    private String name;

    @Column(name = "address", nullable = false, columnDefinition = "VARCHAR(50)")
    @JsonView(Views.BranchOfficeView.class)
    private String address;

    @Column(name = "latitude", nullable = false, columnDefinition = "DOUBLE")
    @JsonView(Views.BranchOfficeView.class)
    private Double latitude;

    @Column(name = "longitude", nullable = false, columnDefinition = "DOUBLE")
    @JsonView(Views.BranchOfficeView.class)
    private Double longitude;


}
