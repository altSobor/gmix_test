package com.company.test4.screen.main;

import com.company.test4.entity.*;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.*;
import io.jmix.ui.component.data.TableItems;
import io.jmix.ui.component.data.table.ContainerTableItems;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.navigation.Route;
import io.jmix.ui.screen.*;
import io.jmix.ui.upload.TemporaryStorage;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@UiController("MainScreen")
@UiDescriptor("main-screen.xml")
@Route(path = "main", root = true)
public class MainScreen extends Screen {
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
    private CollectionContainer<MagNumInput> magNumInputsDc;
    @Autowired
    private Table<MagNumInput> magNumInputsTable;
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
    private CollectionLoader<MagNumInput> magNumInputsDl;
    @Autowired
    private CollectionLoader<LexgraphInput> lexgraphInputsDl1;
    @Autowired
    private CollectionLoader<LexgraphInput> lexgraphInputsDl;

    private MagNumDataClass dataM = new MagNumDataClass();
    private LexgraphDataClass dataL = new LexgraphDataClass();
    ArrayList<int[][]> MagNum = new ArrayList<int[][]>();
    ExecutorService executor = Executors.newFixedThreadPool(1);
    CounterThread counterThread = new CounterThread(15);
    List<MagNumInput> inputDataM;
    List<LexgraphInput> inputDataL;

    @Subscribe
    public void onInit(InitEvent event) {
        executor.execute(counterThread);
        List<String> list = new ArrayList<>();
        list.add("Magic square");
        list.add("Lexical graf");
        tasksCbx.setOptionsList(list);
        lexgraphInputsTable.setItems(new ContainerTableItems<>(lexgraphInputsDc));
        lexgraphInputsTable1.setItems(new ContainerTableItems<>(lexgraphInputsDc_1));


    }

    @Install(to = "lexgraphInputsTable", subject = "emptyStateLinkClickHandler")
    private void lexgraphInputsTableEmptyStateLinkClickHandler(
            Table.EmptyStateClickEvent<LexgraphInput> emptyStateClickEvent) {
        LexgraphInput newL = new LexgraphInput();
        newL.setInput("");
        newL.setInputType("String");
        lexgraphInputsDc.setItem(newL);
        lexgraphInputsTable.setItems((TableItems) lexgraphInputsDc);
    }

    @Subscribe("saveBtn")
    protected void  onSaveButtonClick(Button.ClickEvent event) {

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
            dataM.countOutputData(inputDataM, MagNum);

        }
        else if(tasksCbx.getValue().equals("Lexical graf")){
            dataL.countOutputData(inputDataL);
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
            lexgraphInputsTable.setVisible(false);
            lexgraphInputsTable1.setVisible(false);
            magNumInputsTable.setVisible(true);
            ///for(int i = 0; i < 9; i++){
                MagNumInput newmn = new MagNumInput();
                newmn.setId(null);
                newmn.setInput(1);
                newmn.setMagNumDataClass(dataM);
                //magNumInputsDl.
                magNumInputsDc.setItem(newmn);
                magNumInputsTable.setItems(new ContainerTableItems<>(magNumInputsDc));
                //inputDataM.add(newmn);
            //}

        }
        else if(tasksCbx.getValue().equals("Lexical graf")){
            taskType = 1;
            lexgraphInputsTable.setVisible(true);
            lexgraphInputsTable1.setVisible(true);
            magNumInputsTable.setVisible(false);
        }

    }
}
