package com.company.test4.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.util.UUID;

@JmixEntity
@Table(name = "MAG_NUM_INPUT")
@Entity
public class MagNumInput {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "INPUT_")
    protected Integer input;

    @JoinColumn(name = "MAG_NUM_DATA_CLASS_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private MagNumDataClass magNumDataClass;

    public MagNumDataClass getMagNumDataClass() {
        return magNumDataClass;
    }

    public void setMagNumDataClass(MagNumDataClass magNumDataClass) {
        this.magNumDataClass = magNumDataClass;
    }

    public Integer getInput() {
        return input;
    }

    public void setInput(Integer input) {
        this.input = input;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}