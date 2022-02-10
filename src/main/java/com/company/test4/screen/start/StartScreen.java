package com.company.test4.screen.start;

import com.company.test4.entity.*;
import com.company.test4.screen.lexgraphdataclass.LexgraphDataClassEdit;
import com.company.test4.screen.lexgraphinput.LexgraphInputEdit;
import io.jmix.core.Metadata;
import io.jmix.core.MetadataTools;
import io.jmix.ui.Notifications;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.Screens;
import io.jmix.ui.UiComponents;
import io.jmix.ui.action.list.CreateAction;
import io.jmix.ui.component.*;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import io.jmix.ui.upload.TemporaryStorage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@UiController("StartScreen")
@UiDescriptor("start-screen.xml")
public class StartScreen extends Screen {
    @Autowired
    private Metadata metadata;
    @Autowired
    ScreenBuilders screenBuilders;
    @Autowired
    private Button importBtn;
    @Autowired
    private FileStorageUploadField uploadBtn;
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
    private MagNumDataClass dataM;
    private LexgraphDataClass dataL;
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
    @Subscribe
    public void onInit(InitEvent event) {
        executor.execute(counterThread);
        List<String> list = new ArrayList<>();
        list.add("Magic square");
        list.add("Lexical graf");

        tasksCbx.setOptionsList(list);
        tasksCbx.setValue("Lexical graf");

        for(int i = 0; i < 9; i++){
            MagNumInput magNumInput = new MagNumInput();
            magNumInput.setInput(i+1);
            inputDataM.add(magNumInput);
        }
        dataM.setInputData(inputDataM);
        tbInput00.setValue(dataM.getInputData().get(0).getInput());
        tbInput01.setValue(dataM.getInputData().get(1).getInput());
        tbInput02.setValue(dataM.getInputData().get(2).getInput());
        tbInput10.setValue(dataM.getInputData().get(3).getInput());
        tbInput11.setValue(dataM.getInputData().get(4).getInput());
        tbInput12.setValue(dataM.getInputData().get(5).getInput());
        tbInput20.setValue(dataM.getInputData().get(6).getInput());
        tbInput21.setValue(dataM.getInputData().get(7).getInput());
        tbInput22.setValue(dataM.getInputData().get(8).getInput());

        dataL = createLexgraphDataClass();
        dataL.setInputStrData(inputStrData);
        dataL.setInputSubStrData(inputSubStrData);
    }

    LexgraphDataClass createLexgraphDataClass() {
        return metadata.create(LexgraphDataClass.class);
    }
    MagNumDataClass createMagNumClass() {
        return metadata.create(MagNumDataClass.class);
    }

/*    @Install(to = "lexgraphDataCreate", subject = "initializer")
    private void lexgraphDataCreateInitializer(LexgraphDataClass lexgraphDataClass) {
        lexgraphDataClass.setInputStrData(dataL.getInputStrData());
        lexgraphDataClass.setInputSubStrData(dataL.getInputSubStrData());
        lexgraphDataClass.setStrCount(dataL.getInputStrData().size());
        lexgraphDataClass.setSubStrCount(dataL.getInputSubStrData().size());
        lexgraphDataClass.setTaskType(0);
        //lexgraphDataClass.setDateTime(timeSource.currentTimestamp());
        //dataL.getInputSubStrData().add(lexgraphInput);
    }*/

    @Subscribe("saveBtn")
        protected void  onSaveButtonClick(Button.ClickEvent event) {
        if(tasksCbx.getValue().equals("Magic square")){

        }
        else if(tasksCbx.getValue().equals("Lexical graf")){

        }
    }
    @Subscribe("uploadBtn")
    public void onUploadBtnFileUploadStart(UploadField.FileUploadStartEvent event) {
        /*File file = temporaryStorage.getFile(uploadBtn.getFileId());
        if (file != null) {
            notifications.create()
                    .withCaption("File is uploaded to temporary storage at " + file.getAbsolutePath())
                    .show();
        }
        file = null;*/
    }
    @Subscribe("importBtn")
    protected void  onImportButtonClick(Button.ClickEvent event) {

    }
    @Subscribe("exportBtn")
    protected void  onExportButtonClick(Button.ClickEvent event) {

    }
    @Subscribe("countBtn")
    protected void  onCountButtonClick(Button.ClickEvent event) {
        if(tasksCbx.getValue().equals("Magic square")){
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
        else if(tasksCbx.getValue().equals("Lexical graf")){
            List<Output> outputList = dataL.countOutputData();
            for(int i = 0; i < outputList.size(); i++){
                Output lo = metadata.create(Output.class);
                lo.setOutput(outputList.get(i).getOutput());
                outputsDc.getMutableItems().add(lo);
            }
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
    }
    @Install(to = "lexgraphInputsTable1", subject = "emptyStateLinkClickHandler")
    private void lexgraphInputsTable1EmptyStateLinkClickHandler(Table.EmptyStateClickEvent<LexgraphInput> emptyStateClickEvent) {
        LexgraphInput li = metadata.create(LexgraphInput.class);
        lexgraphInputsDc_1.getMutableItems().add(li);
        li.setInputType("Substring");
        dataL.getInputSubStrData().add(li);
    }
    @Subscribe("addSubString")
    public void onAddSubStringClick(Button.ClickEvent event) {
        LexgraphInput li = metadata.create(LexgraphInput.class);
        lexgraphInputsDc_1.getMutableItems().add(li);
        li.setInputType("Substring");
        dataL.getInputSubStrData().add(li);
    }
    @Subscribe("addString")
    public void onAddStringClick(Button.ClickEvent event) {
        LexgraphInput li = metadata.create(LexgraphInput.class);
        lexgraphInputsDc.getMutableItems().add(li);
        li.setInputType("String");
        dataL.getInputStrData().add(li);
    }
}