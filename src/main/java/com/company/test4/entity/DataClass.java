package com.company.test4.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "DATA_CLASS")
@Entity
public class DataClass {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "TASK_TYPE")
    private Integer taskType;

    @Composition
    @OneToMany(mappedBy = "dataClass")
    protected List<Output> outputData;

    public List<Output> getOutputData() {
        return outputData;
    }

    public void setOutputData(List<Output> outputData) {
        this.outputData = outputData;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}