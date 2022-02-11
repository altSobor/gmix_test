package com.company.test4.entity;

import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

@JmixEntity
@Entity(name = "MagNumDataClass")
public class MagNumDataClass extends DataClass {

    @Composition
    @OneToMany(mappedBy = "magNumDataClass")
    private List<MagNumInput> inputData;

    public List<MagNumInput> getInputData() {
        return inputData;
    }

    public void setInputData(List<MagNumInput> inputData) {
        this.inputData = inputData;
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

    public String setSaveStringMagNum(){
        String mnc = "\""+super.getTaskType().toString()+"\";\"";
        for(int i = 0; i < 9; i++){
            mnc = mnc + getInputData().get(i).getInput().toString() + ";";
        }
        mnc = mnc + "\"";
        return mnc;
    }

    public void getSaveStringMagNum(String mnc){
        String[] splitedMNC = mnc.split("\";\"");
        super.setTaskType(parseInt(splitedMNC[0].replace("\"", "")));
        String[] splitedInput = splitedMNC[1].split(";");
        getInputData().clear();
        for(int i = 0; i < 9; i++){
            MagNumInput mi = new MagNumInput();
            mi.setInput(parseInt(splitedInput[i]));
            getInputData().add(mi);
        }
    }
}