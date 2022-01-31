package com.company.test4.entity;

import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@JmixEntity
@Entity
public class LexgraphDataClass extends DataClass {
    @Column(name = "STR_COUNT")
    private Integer strCount;

    @Column(name = "SUB_STR_COUNT")
    private Integer subStrCount;

    @Composition
    @OneToMany(mappedBy = "lexgraphDataClass")
    private List<LexgraphInput> inputData;

    public List<LexgraphInput> getInputData() {
        return inputData;
    }

    public void setInputData(List<LexgraphInput> inputData) {
        this.inputData = inputData;
    }

    public Integer getSubStrCount() {
        return subStrCount;
    }

    public void setSubStrCount(Integer subStrCount) {
        this.subStrCount = subStrCount;
    }

    public Integer getStrCount() {
        return strCount;
    }

    public void setStrCount(Integer strCount) {
        this.strCount = strCount;
    }

    @InstanceName
    @DependsOnProperties({"id"})
    public String getInstanceName() {
        return String.format("%s", getId());
    }

    public List<Output> countOutputData(List<LexgraphInput> inputData){
        for(int i = 0; i < strCount; i++){
            for(int j = strCount; j < subStrCount; j++){
                if(inputData.get(i).input.toLowerCase().contains(inputData.get(j).input.toLowerCase())){
                    Output outputs = new Output();
                    outputs.output = inputData.get(j).input;
                    super.outputData.add(outputs);
                    break;
                }
            }
        }
        return super.outputData;
    }
}