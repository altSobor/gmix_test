package com.company.test4.entity;

import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

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

    public ArrayList<int[][]> countMagicQuard(){
        ArrayList<int[][]> magQuard = new ArrayList<int[][]>();
        for(int i = 123456789; i <= 987654321; i++){
            ArrayList<Integer> intlist = new ArrayList<Integer>();
            intlist.add(i/100000000);
            intlist.add((i/10000000)%10);
            intlist.add((i/1000000)%100);
            intlist.add((i/100000)%1000);
            intlist.add((i/10000)%10000);
            intlist.add((i/1000)%100000);
            intlist.add((i/100)%1000000);
            intlist.add((i/10)%10000000);
            intlist.add(i%100000000);
            if(intlist.contains(1)&&intlist.contains(2)&&intlist.contains(3)&&intlist.contains(4)&&intlist.contains(5)
                    &&intlist.contains(6)&&intlist.contains(7)&&intlist.contains(8)&&intlist.contains(9)&&
                    intlist.get(0)+intlist.get(1)+intlist.get(2)==strSum&&
                    intlist.get(3)+intlist.get(4)+intlist.get(5)==strSum&&
                    intlist.get(6)+intlist.get(7)+intlist.get(8)==strSum&&
                    intlist.get(0)+intlist.get(3)+intlist.get(6)==strSum&&
                    intlist.get(1)+intlist.get(4)+intlist.get(7)==strSum&&
                    intlist.get(2)+intlist.get(5)+intlist.get(8)==strSum){
                int[][] mQ = new int[3][3];
                mQ[0][0] = intlist.get(0);
                mQ[1][0] = intlist.get(1);
                mQ[1][0] = intlist.get(2);
                mQ[0][1] = intlist.get(3);
                mQ[1][1] = intlist.get(4);
                mQ[2][1] = intlist.get(5);
                mQ[0][2] = intlist.get(6);
                mQ[1][2] = intlist.get(7);
                mQ[2][2] = intlist.get(8);
                magQuard.add(mQ);
            }
        }
        return magQuard;
    }

    public List<Output> countOutputData(List<MagNumInput> inputData, ArrayList<int[][]> magQuard){
        int count = 0;
        int cost = 0;
        int totalCost = 0;
        for(int n = 0; n < magQuard.size(); n++){
            cost = 0;
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    cost =+ Math.abs(magQuard.get(n)[i][j] - inputData.get(i*3+j).input);
                }
            }
            if(n==0||cost<totalCost){
                count = n;
                totalCost = cost;
            }
        }
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Output magNums = new Output();
                magNums.output = Integer.toString(magQuard.get(count)[i][j]);
                super.outputData.add(magNums);
            }
        }
        Output costs = new Output();
        costs.output = Integer.toString(totalCost);
        super.outputData.add(costs);
        return super.outputData;
    }
}