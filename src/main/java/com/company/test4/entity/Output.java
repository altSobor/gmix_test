package com.company.test4.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.util.UUID;

@JmixEntity
@Table(name = "OUTPUT_")
@Entity(name = "Output_")
public class Output {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "OUTPUT_")
    @Lob
    protected String output;
    @JoinColumn(name = "DATA_CLASS_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
private DataClass dataClass;

public DataClass getDataClass() {
        return dataClass;
        }

public void setDataClass(DataClass dataClass) {
        this.dataClass = dataClass;
        }

public String getOutput() {
        return output;
        }

public void setOutput(String output) {
        this.output = output;
        }

public UUID getId() {
        return id;
        }

public void setId(UUID id) {
        this.id = id;
        }
        }