package com.tuukkal.talousappi.UserManagement.domain;

import com.tuukkal.talousappi.UserManagement.domain.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "typetags")
public class Typetag {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String tag;
    @ManyToOne(fetch = FetchType.EAGER)
    private Type type;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

}