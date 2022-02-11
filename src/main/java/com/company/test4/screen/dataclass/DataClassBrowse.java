package com.company.test4.screen.dataclass;

import io.jmix.ui.screen.*;
import com.company.test4.entity.DataClass;

@UiController("DataClass.browse")
@UiDescriptor("data-class-browse.xml")
@LookupComponent("dataClassesTable")
public class DataClassBrowse extends StandardLookup<DataClass> {
}