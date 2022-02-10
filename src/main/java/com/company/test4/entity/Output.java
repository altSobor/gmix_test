package com.company.test4.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.data.DbView;

import javax.persistence.*;
import java.util.UUID;

@DbView
@JmixEntity
@Table(name = "OUTPUT_")
@Entity(name = "Output_")
public class Output {
    @InstanceName
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "OUTPUT_")
    @Lob
    protected String output;

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