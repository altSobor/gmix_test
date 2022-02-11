package com.company.test4.screen.lexgraphdataclass;

import io.jmix.ui.screen.*;
import com.company.test4.entity.LexgraphDataClass;

@UiController("LexgraphDataClass.edit")
@UiDescriptor("lexgraph-data-class-edit.xml")
@EditedEntityContainer("lexgraphDataClassDc")
public class LexgraphDataClassEdit extends StandardEditor<LexgraphDataClass> {
}