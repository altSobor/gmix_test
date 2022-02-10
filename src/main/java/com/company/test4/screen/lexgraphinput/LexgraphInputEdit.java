package com.company.test4.screen.lexgraphinput;

import io.jmix.ui.component.TextArea;
import io.jmix.ui.screen.*;
import com.company.test4.entity.LexgraphInput;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("sampler_LexgraphInput.edit")
@UiDescriptor("lexgraph-input-edit.xml")
@EditedEntityContainer("lexgraphInputDc")
@PrimaryEditorScreen(LexgraphInput.class)
public class LexgraphInputEdit extends StandardEditor<LexgraphInput> {

}