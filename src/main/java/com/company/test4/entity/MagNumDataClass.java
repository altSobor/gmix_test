package com.company.test4.entity;

import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.*;

@JmixEntity
@Entity
public class MagNumDataClass extends DataClass {

    @Column(name = "MIN_COST")
    private Integer minCost;

    @Column(name = "STR_SUM")
    private Integer strSum;

    @Composition
    @OneToMany(mappedBy = "magNumDataClass")
    private List<MagNumInput> inputData;

    public void setStrSum(Integer strSum) {
        this.strSum = strSum;
    }

    public Integer getStrSum() {
        return strSum;
    }

    public List<MagNumInput> getInputData() {
        return inputData;
    }

    public void setInputData(List<MagNumInput> inputData) {
        this.inputData = inputData;
    }

    public Integer getMinCost() {
        return minCost;
    }

    public void setMinCost(Integer minCost) {
        this.minCost = minCost;
    }

    @InstanceName
    @DependsOnProperties({"id"})
    public String getInstanceName() {
        return String.format("%s", getId());
    }

    public List<Output> countOutputData(ArrayList<int[][]> magQuard){
        int count = 0;
        int cost = 0;
        int totalCost = 0;
        for(int n = 0; n < magQuard.size(); n++){
            cost = 0;
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    cost = cost + Math.abs(magQuard.get(n)[i][j] - inputData.get(i*3+j).input);
                }
            }
            if(n==0||cost<totalCost){
                count = n;
                totalCost = cost;
            }
        }
        List<Output> out = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Output magNums = new Output();
                magNums.setOutput(Integer.toString(magQuard.get(count)[i][j]));
                out.add(magNums);
            }
        }
        Output costs = new Output();
        costs.setOutput(Integer.toString(totalCost));
        out.add(costs);
        return out;
    }
}