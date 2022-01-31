package com.company.test4.entity;

import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@JmixEntity
@Entity
public class MagNumDataClass extends DataClass {

    @Column(name = "MIN_COST")
    private Integer minCost;

    @Column(name = "STR_SUM")
    private String strSum;

    @Composition
    @OneToMany(mappedBy = "magNumDataClass")
    private List<MagNumInput> inputData;

    public List<MagNumInput> getInputData() {
        return inputData;
    }

    public void setInputData(List<MagNumInput> inputData) {
        this.inputData = inputData;
    }

    public String getStrSum() {
        return strSum;
    }

    public void setStrSum(String strSum) {
        this.strSum = strSum;
    }

    public Integer getMinCost() {
        return minCost;
    }

    public void setMinCost(Integer minCost) {
        this.minCost = minCost;
    }
}