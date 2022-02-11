package com.company.test4.screen.lexgraphinput;

import io.jmix.ui.screen.*;
import com.company.test4.entity.LexgraphInput;

@UiController("LexgraphInput.edit")
@UiDescriptor("lexgraph-input-edit.xml")
@EditedEntityContainer("lexgraphInputDc")
public class LexgraphInputEdit extends StandardEditor<LexgraphInput> {
}