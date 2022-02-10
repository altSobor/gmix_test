package com.company.test4.screen.magnumdataclass;

import io.jmix.ui.screen.*;
import com.company.test4.entity.MagNumDataClass;

@UiController("MagNumDataClass.edit")
@UiDescriptor("mag-num-data-class-edit.xml")
@EditedEntityContainer("magNumDataClassDc")
public class MagNumDataClassEdit extends StandardEditor<MagNumDataClass> {
}