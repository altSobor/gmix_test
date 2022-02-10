package com.company.test4.screen.magnumdataclass;

import io.jmix.ui.screen.*;
import com.company.test4.entity.MagNumDataClass;

@UiController("MagNumDataClass.browse")
@UiDescriptor("mag-num-data-class-browse.xml")
@LookupComponent("magNumDataClassesTable")
public class MagNumDataClassBrowse extends StandardLookup<MagNumDataClass> {
}