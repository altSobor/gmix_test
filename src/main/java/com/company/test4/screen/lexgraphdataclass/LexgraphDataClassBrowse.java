package com.company.test4.screen.lexgraphdataclass;

import io.jmix.ui.screen.*;
import com.company.test4.entity.LexgraphDataClass;

@UiController("LexgraphDataClass.browse")
@UiDescriptor("lexgraph-data-class-browse.xml")
@LookupComponent("lexgraphDataClassesTable")
public class LexgraphDataClassBrowse extends StandardLookup<LexgraphDataClass> {
}