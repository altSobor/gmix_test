package com.company.test4.screen.start;

import com.company.test4.entity.*;
import com.company.test4.screen.lexgraphdataclass.LexgraphDataClassEdit;
import com.company.test4.screen.magnumdataclass.MagNumDataClassEdit;
import io.jmix.core.Metadata;
import io.jmix.ui.Notifications;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.Screens;
import io.jmix.ui.action.Action;
import io.jmix.ui.action.entitypicker.EntityOpenAction;
import io.jmix.ui.action.list.CreateAction;
import io.jmix.ui.action.list.EditAction;
import io.jmix.ui.component.*;
import io.jmix.ui.download.DownloadFormat;
import io.jmix.ui.download.Downloader;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import io.jmix.ui.upload.TemporaryStorage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Integer.parseInt;


@UiController("StartScreen")
@UiDescriptor("start-screen.xml")
public class StartScreen extends Screen {
    @Autowired
    private Metadata metadata;
    @Autowired
    ScreenBuilders screenBuilders;
    @Autowired
    private FileUploadField importBtn;
    @Autowired
    private Button uploadBtn;
    @Autowired
    private Button exportBtn;
    @Autowired
    private Button saveBtn;
    @Autowired
    private Button countBtn;
    @Autowired
    private ComboBox tasksCbx;

    int taskType = 1;
    @Autowired
    private Table lexgraphInputsTable;
    @Autowired
    private Table lexgraphInputsTable1;

    @Autowired
    private CollectionContainer<LexgraphInput> lexgraphInputsDc;
    @Autowired
    private CollectionContainer<LexgraphInput> lexgraphInputsDc_1;
    @Autowired
    private Table outputsTable;
    @Autowired
    private CollectionContainer<Output> outputsDc;
    @Autowired
    private TemporaryStorage temporaryStorage;
    @Autowired
    private Notifications notifications;
    @Autowired
    private CollectionLoader<Output> outputsDl;
    @Autowired
    private CollectionLoader<LexgraphInput> lexgraphInputsDl1;
    @Autowired
    private CollectionLoader<LexgraphInput> lexgraphInputsDl;
    private MagNumDataClass dataM = new MagNumDataClass();
    private LexgraphDataClass dataL = new LexgraphDataClass();
    ArrayList<int[][]> MagNum = new ArrayList<int[][]>();
    ExecutorService executor = Executors.newFixedThreadPool(1);
    CounterThread counterThread = new CounterThread(15);
    List<MagNumInput> inputDataM  = new ArrayList<>();
    List<LexgraphInput> inputSubStrData = new ArrayList<>();
    List<LexgraphInput> inputStrData = new ArrayList<>();
    @Autowired
    private TextField tbInput00;
    @Autowired
    private TextField tbInput01;
    @Autowired
    private TextField tbInput02;
    @Autowired
    private TextField tbInput10;
    @Autowired
    private TextField tbInput11;
    @Autowired
    private TextField tbInput12;
    @Autowired
    private TextField tbInput20;
    @Autowired
    private TextField tbInput21;
    @Autowired
    private TextField tbInput22;
    @Autowired
    private VBoxLayout magicNumOutputField;
    @Autowired
    private TextField tbOutput00;
    @Autowired
    private TextField tbOutput01;
    @Autowired
    private TextField tbOutput02;
    @Autowired
    private TextField tbOutput10;
    @Autowired
    private TextField tbOutput11;
    @Autowired
    private TextField tbOutput12;
    @Autowired
    private TextField tbOutput20;
    @Autowired
    private TextField tbOutput21;
    @Autowired
    private TextField tbOutput22;
    @Autowired
    private VBoxLayout magicNumInputField;
    @Autowired
    private TextField costTF;
    @Autowired
    private Screens screens;
    @Autowired
    private Button addSubString;
    @Autowired
    private Button addString;

    @Named("magnumSave")
    private CreateAction<MagNumDataClass> magnumSave;
    @Named("lexgraphSave")
    private CreateAction<LexgraphDataClass> lexgraphSave;

    @Subscribe
    public void onInit(InitEvent event) {
    /*    executor.execute(counterThread);
        List<String> list = new ArrayList<>();
        list.add("Lexical graf");
        list.add("Magic square");

        tasksCbx.setOptionsList(list);
        tasksCbx.setValue(list.get(0));

        for(int i = 0; i < 9; i++){
            MagNumInput magNumInput = new MagNumInput();
            magNumInput.setInput(i+1);
            inputDataM.add(magNumInput);
        }
        dataM.setTaskType(0);
        dataM.setInputData(inputDataM);
        setMagNumValues();

        dataL = createLexgraphDataClass();
        dataL.setTaskType(1);
        dataL.setInputStrData(inputStrData);
        dataL.setInputSubStrData(inputSubStrData);
*/
    }
/*
    void setMagNumValues(){
        tbInput00.setValue(dataM.getInputData().get(0).getInput());
        tbInput01.setValue(dataM.getInputData().get(1).getInput());
        tbInput02.setValue(dataM.getInputData().get(2).getInput());
        tbInput10.setValue(dataM.getInputData().get(3).getInput());
        tbInput11.setValue(dataM.getInputData().get(4).getInput());
        tbInput12.setValue(dataM.getInputData().get(5).getInput());
        tbInput20.setValue(dataM.getInputData().get(6).getInput());
        tbInput21.setValue(dataM.getInputData().get(7).getInput());
        tbInput22.setValue(dataM.getInputData().get(8).getInput());
    }

    LexgraphDataClass createLexgraphDataClass() {
        return metadata.create(LexgraphDataClass.class);
    }
    MagNumDataClass createMagNumClass() {
        return metadata.create(MagNumDataClass.class);
    }

    @Subscribe("saveBtn")
        protected void  onSaveButtonClick(Button.ClickEvent event) {
        if(taskType == 0){
            screenBuilders.editor(MagNumDataClass.class, this)
                    .newEntity()
                    .withInitializer(savedDataM -> {
                        savedDataM.setDateTime(LocalDateTime.now());
                        savedDataM.setTaskType(dataM.getTaskType());
                        List <MagNumInput> listMI = new ArrayList<MagNumInput>();
                        for(int i = 0; i < 9; i++){
                            MagNumInput mi = metadata.create(MagNumInput.class);
                            mi.setInput(dataM.getInputData().get(i).getInput());
                            mi.setMagNumDataClass(savedDataM);
                            listMI.add(mi);
                        }
                        savedDataM.setInputData(listMI);
                    })
                    .withScreenClass(MagNumDataClassEdit.class)
                    .withOpenMode(OpenMode.DIALOG)
                    .build()
                    .show();
        }
        else if(taskType == 1){
            screenBuilders.editor(LexgraphDataClass.class, this)
                    .newEntity()
                    .withInitializer(savedDataL -> {
                        savedDataL.setDateTime(LocalDateTime.now());
                        savedDataL.setTaskType(dataL.getTaskType());
                        List <LexgraphInput> listSI = new ArrayList<LexgraphInput>();
                        for(int i = 0; i < dataL.getInputStrData().size(); i++){
                            LexgraphInput Lsi = metadata.create(LexgraphInput.class);
                            Lsi.setInput(dataL.getInputStrData().get(i).getInput());
                            Lsi.setInputType("String");
                            Lsi.setLexgraphDataClass(savedDataL);
                            listSI.add(Lsi);
                        }
                        savedDataL.setInputStrData(listSI);
                        List <LexgraphInput> listSSI = new ArrayList<>();
                        for(int i = 0; i < dataL.getInputSubStrData().size(); i++){
                            LexgraphInput Lssi = metadata.create(LexgraphInput.class);
                            Lssi.setInput(dataL.getInputSubStrData().get(i).getInput());
                            Lssi.setInputType("Substring");
                            Lssi.setLexgraphDataClass(savedDataL);
                            listSSI.add(Lssi);
                        }
                        savedDataL.setInputSubStrData(listSSI);
                        savedDataL.setStrCount(dataL.getInputStrData().size());
                        savedDataL.setSubStrCount(dataL.getInputSubStrData().size());
                    })
                    .withScreenClass(LexgraphDataClassEdit.class)
                    .withOpenMode(OpenMode.DIALOG)
                    .build()
                    .show();
        }
    }

    @Autowired
    private Downloader downloader;
    @Subscribe("exportBtn")
    public void onExportBtnClick(Button.ClickEvent event) {
        byte[] str = null;
        if(taskType == 0){
            str = dataM.setSaveStringMagNum().getBytes();
        } else if(taskType == 1){
            str = dataL.setSaveStringLexgreph().getBytes();
        }
        downloader.download(
                str,
                "item.txt",
                DownloadFormat.TEXT
        );
    }
    @Subscribe("countBtn")
    protected void  onCountButtonClick(Button.ClickEvent event) {
        if(taskType == 0){
            countMagNum();
        }
        else if(taskType == 1){
            countLexGraph();
        }
    }

    void countMagNum(){
        List<Output> mnout = dataM.countOutputData(MagNum);
        tbOutput00.setValue(mnout.get(0).getOutput());
        tbOutput01.setValue(mnout.get(1).getOutput());
        tbOutput02.setValue(mnout.get(2).getOutput());
        tbOutput10.setValue(mnout.get(3).getOutput());
        tbOutput11.setValue(mnout.get(4).getOutput());
        tbOutput12.setValue(mnout.get(5).getOutput());
        tbOutput20.setValue(mnout.get(6).getOutput());
        tbOutput21.setValue(mnout.get(7).getOutput());
        tbOutput22.setValue(mnout.get(8).getOutput());
        costTF.setValue(mnout.get(9).getOutput());
    }

    void countLexGraph(){
        outputsDc.getMutableItems().clear();
        List<Output> outputList = dataL.countOutputData();
        for(int i = 0; i < outputList.size(); i++){
            Output lo = metadata.create(Output.class);
            lo.setOutput(outputList.get(i).getOutput());
            outputsDc.getMutableItems().add(lo);
        }
    }

    @Subscribe("tasksCbx")
    public void onTasksCbxValueChange(HasValue.ValueChangeEvent event) {
        if(tasksCbx.getValue().equals("Magic square")){
            if(!counterThread.isActive){
                MagNum = counterThread.getMagQuard();
                executor.shutdownNow();
            }
            taskType = 0;
            magicNumInputField.setVisible(true);
            lexgraphInputsTable.setVisible(false);
            lexgraphInputsTable1.setVisible(false);
            outputsTable.setVisible(false);
            magicNumOutputField.setVisible(true);
            addString.setVisible(false);
            addSubString.setVisible(false);
        }
        else if(tasksCbx.getValue().equals("Lexical graf")){
            taskType = 1;
            lexgraphInputsTable.setVisible(true);
            lexgraphInputsTable1.setVisible(true);
            outputsTable.setVisible(true);
            magicNumInputField.setVisible(false);
            magicNumOutputField.setVisible(false);
        }
    }

    @Subscribe("tbInput00")
    public void onTbInput00ValueChange(HasValue.ValueChangeEvent<Integer> event) {
        inputDataM.get(0).setInput((Integer) tbInput00.getValue());
    }
    @Subscribe("tbInput01")
    public void onTbInput01ValueChange(HasValue.ValueChangeEvent<Integer> event) {
        inputDataM.get(1).setInput((Integer) tbInput01.getValue());
    }
    @Subscribe("tbInput02")
    public void onTbInput02ValueChange(HasValue.ValueChangeEvent<Integer> event) {
        inputDataM.get(2).setInput((Integer) tbInput02.getValue());
    }
    @Subscribe("tbInput10")
    public void onTbInput10ValueChange(HasValue.ValueChangeEvent<Integer> event) {
        inputDataM.get(3).setInput((Integer) tbInput10.getValue());
    }
    @Subscribe("tbInput11")
    public void onTbInput11ValueChange(HasValue.ValueChangeEvent<Integer> event) {
        inputDataM.get(4).setInput((Integer) tbInput11.getValue());
    }
    @Subscribe("tbInput12")
    public void onTbInput12ValueChange(HasValue.ValueChangeEvent<Integer> event) {
        inputDataM.get(5).setInput((Integer) tbInput12.getValue());
    }
    @Subscribe("tbInput20")
    public void onTbInput20ValueChange(HasValue.ValueChangeEvent<Integer> event) {
        inputDataM.get(6).setInput((Integer) tbInput20.getValue());
    }
    @Subscribe("tbInput21")
    public void onTbInput21ValueChange(HasValue.ValueChangeEvent<Integer> event) {
        inputDataM.get(7).setInput((Integer) tbInput21.getValue());
    }
    @Subscribe("tbInput22")
    public void onTbInput22ValueChange(HasValue.ValueChangeEvent<Integer> event) {
        inputDataM.get(8).setInput((Integer) tbInput22.getValue());
    }
    @Install(to = "lexgraphInputsTable", subject = "emptyStateLinkClickHandler")
    private void lexgraphInputsTableEmptyStateLinkClickHandler(Table.EmptyStateClickEvent<LexgraphInput> emptyStateClickEvent) {
        LexgraphInput li = metadata.create(LexgraphInput.class);
        lexgraphInputsDc.getMutableItems().add(li);
        li.setInputType("String");
        dataL.getInputStrData().add(li);
        dataL.setStrCount(lexgraphInputsDc.getMutableItems().size());
    }
    @Install(to = "lexgraphInputsTable1", subject = "emptyStateLinkClickHandler")
    private void lexgraphInputsTable1EmptyStateLinkClickHandler(Table.EmptyStateClickEvent<LexgraphInput> emptyStateClickEvent) {
        LexgraphInput li = metadata.create(LexgraphInput.class);
        lexgraphInputsDc_1.getMutableItems().add(li);
        li.setInputType("Substring");
        dataL.getInputSubStrData().add(li);
        dataL.setSubStrCount(lexgraphInputsDc_1.getMutableItems().size());
    }
    @Subscribe("addSubString")
    public void onAddSubStringClick(Button.ClickEvent event) {
        LexgraphInput li = metadata.create(LexgraphInput.class);
        lexgraphInputsDc_1.getMutableItems().add(li);
        li.setInputType("Substring");
        dataL.getInputSubStrData().add(li);
        dataL.setSubStrCount(lexgraphInputsDc_1.getMutableItems().size());
    }
    @Subscribe("addString")
    public void onAddStringClick(Button.ClickEvent event) {
        LexgraphInput li = metadata.create(LexgraphInput.class);
        lexgraphInputsDc.getMutableItems().add(li);
        li.setInputType("String");
        dataL.getInputStrData().add(li);
        dataL.setStrCount(lexgraphInputsDc.getMutableItems().size());
    }
    @Subscribe("importBtn")
    public void onImportBtnFileUploadSucceed(SingleFileUploadField.FileUploadSucceedEvent event) {
        /*InputStream is = importBtn.getFileContent();
        String imported = "";
        assert is != null;
        byte[] outputData = is.readAllBytes();
        imported = new String(outputData, StandardCharsets.UTF_8);
        if(imported.charAt(1)=='0'){
            dataM = createMagNumClass();
            dataM.getSaveStringMagNum(imported);
            setMagNumValues();
            countMagNum();
        }
        else if(imported.charAt(1)=='1'){
            dataL = createLexgraphDataClass();
            dataL.getSaveStringLexgreph(imported);
            for(int i = 0; i < dataL.getStrCount(); i++){
                LexgraphInput li = metadata.create(LexgraphInput.class);
                lexgraphInputsDc.getMutableItems().add(li);
                li.setInputType("String");
                li.setInput(dataL.getInputStrData().get(i).getInput());
            }
            for(int i = 0; i < dataL.getSubStrCount(); i++){
                LexgraphInput li = metadata.create(LexgraphInput.class);
                lexgraphInputsDc_1.getMutableItems().add(li);
                li.setInputType("Substring");
                li.setInput(dataL.getInputSubStrData().get(i).getInput());
            }
            countLexGraph();
        }
        else{
            notifications.create()
                    .withCaption("Wrong file format")
                    .show();
        }*/
    }
/*
    @Subscribe("uploadBtn")
    public void onUploadBtnClick(Button.ClickEvent event) {
        if(taskType==0){
            screenBuilders.lookup(MagNumDataClass.class, this)
                    .withSelectHandler(MagNumDataClasses -> {
                        dataM = MagNumDataClasses.iterator().next();
                    })
                    .build()
                    .show();
            setMagNumValues();
            countMagNum();
        }
        else if(taskType==1){
            lexgraphInputsDc.getMutableItems().clear();
            lexgraphInputsDc_1.getMutableItems().clear();
            screenBuilders.lookup(LexgraphDataClass.class, this)
                    .withSelectHandler(LexgraphDataClasses -> {
                        dataL = LexgraphDataClasses.iterator().next();
                        for(int i = 0; i < dataL.getInputStrData().size(); i++){

                            if(dataL.getInputStrData().get(i).getInputType().equals("String")){
                                LexgraphInput li = metadata.create(LexgraphInput.class);
                                lexgraphInputsDc.getMutableItems().add(li);
                                li.setInputType("String");
                                li.setInput(dataL.getInputStrData().get(i).getInput());
                            }

                        }
                        for(int i = 0; i < dataL.getInputStrData().size(); i++){

                            if(!dataL.getInputStrData().get(i).getInputType().equals("String")) {
                                dataL.getInputStrData().remove(i);
                                i--;
                            }
                        }
                        for(int i = 0; i < dataL.getInputSubStrData().size(); i++){
                            if(dataL.getInputSubStrData().get(i).getInputType().equals("Substring")){
                                LexgraphInput li = metadata.create(LexgraphInput.class);
                                lexgraphInputsDc_1.getMutableItems().add(li);
                                li.setInputType("Substring");
                                li.setInput(dataL.getInputSubStrData().get(i).getInput());
                            }
                        }
                        for(int i = 0; i < dataL.getInputSubStrData().size(); i++) {
                            if (!dataL.getInputSubStrData().get(i).getInputType().equals("Substring")) {
                                dataL.getInputSubStrData().remove(i);
                                i--;
                            }
                        }
                        countLexGraph();
                    })
                    .build()
                    .show();


        }
    }
}*/