package com.company.test4.entity;

import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Integer.parseInt;

@JmixEntity
@Entity(name = "LexgraphDataClass")
public class LexgraphDataClass extends DataClass {
    @Column(name = "STR_COUNT")
    private Integer strCount;

    @Composition
    @OneToMany(mappedBy = "lexgraphDataClass")
    private List<LexgraphInput> inputSubStrData;

    @Column(name = "SUB_STR_COUNT")
    private Integer subStrCount;

    @Composition
    @OneToMany(mappedBy = "lexgraphDataClass")
    private List<LexgraphInput> inputStrData;

    public List<LexgraphInput> getInputSubStrData() {
        return inputSubStrData;
    }

    public void setInputSubStrData(List<LexgraphInput> inputSubStrData) {
        this.inputSubStrData = inputSubStrData;
    }

    public List<LexgraphInput> getInputStrData() {
        return inputStrData;
    }

    public void setInputStrData(List<LexgraphInput> inputStrData) {
        this.inputStrData = inputStrData;
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

    public List<Output> countOutputData(){
        List<Output> outputList = new ArrayList<>();
        ArrayList<String> stringList = new ArrayList<>();
        for (LexgraphInput inputStrDatum : inputStrData) {
            int counter = 0;
            for (LexgraphInput inputSubStrDatum : inputSubStrData) {
                if (inputStrDatum.getInput().toLowerCase().contains(inputSubStrDatum.getInput().toLowerCase())) {
                    String str = inputSubStrDatum.getInput();

                    for (String s : stringList) {
                        if (s.equals(str)) {
                            counter++;
                        }
                    }
                    if (counter == 0) {
                        stringList.add(str);
                    }
                }
                //break;
            }
        }
        Collections.sort(stringList);
        for (String s : stringList) {
            Output outputs = new Output();
            outputs.setOutput(s);
            outputList.add(outputs);
        }
        return outputList;
    }

    public String setSaveStringLexgreph(){
        StringBuilder ldc = new StringBuilder("\"" + super.getTaskType().toString() + "\";\"" + getStrCount().toString() + "\";\"" + getSubStrCount().toString() + "\";\"");
        for(int i = 0; i < strCount; i++){
            ldc.append(inputStrData.get(i).getInput()).append(";");
        }
        ldc.append("\";\"");
        for(int i = 0; i < subStrCount; i++){
            ldc.append(inputSubStrData.get(i).getInput()).append(";");
        }
        ldc.append("\"");
        return ldc.toString();
    }

    public void getSaveStringLexgreph(String ldc){
        inputStrData = new ArrayList<>();
        inputSubStrData = new ArrayList<>();
        String[] splitedLDC = ldc.split("\";\"");
        super.setTaskType(parseInt(splitedLDC[0].replace("\"", "")));
        setStrCount(parseInt(splitedLDC[1]));
        setSubStrCount(parseInt(splitedLDC[2]));
        String[] splitedInputStrings = splitedLDC[3].split(";");
        for(int i = 0; i < getStrCount(); i++){
            LexgraphInput li = new LexgraphInput();
            li.setInputType("String");
            li.setInput(splitedInputStrings[i]);
            getInputStrData().add(li);
        }
        String[] splitedInputSubStrings = splitedLDC[4].replace("\"", "").split(";");
        for(int i = 0; i < getSubStrCount(); i++){
            LexgraphInput li = new LexgraphInput();
            li.setInputType("SubString");
            li.setInput(splitedInputSubStrings[i]);
            getInputSubStrData().add(li);
        }
    }
}