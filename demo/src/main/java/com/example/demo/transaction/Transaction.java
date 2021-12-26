package com.example.demo.transaction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.example.demo.type.Type;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @SequenceGenerator(
        name = "transaction_sequence",
            sequenceName = "transaction_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "transaction_sequence"
            )
    private Long id;

    @Column(nullable = false)
    private Double sum;

    @Column(nullable = false)
    private String target;

    @ManyToOne
    @JoinColumn(name="type_id")
    private Type type;

    

    public Transaction(Long id, Double sum, String target, Type type) {
        this.id = id;
        this.sum = sum;
        this.target = target;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }




}