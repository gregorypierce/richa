package org.richa.tags.extjs;

import org.apache.commons.jelly.TagLibrary;

public class ExtJSTagLibrary extends TagLibrary
{
	public ExtJSTagLibrary()
	{
		//Forms
		registerTag("textfield",org.richa.tags.extjs.form.TextField.class) ;
		registerTag("textarea",org.richa.tags.extjs.form.TextArea.class) ;
		registerTag("numberfield",org.richa.tags.extjs.form.NumberField.class) ;
		registerTag("datefield",org.richa.tags.extjs.form.DateField.class) ;
		registerTag("checkbox",org.richa.tags.extjs.form.CheckBox.class) ;
		registerTag("radio",org.richa.tags.extjs.form.Radio.class) ;
		registerTag("combobox",org.richa.tags.extjs.form.ComboBox.class) ;
		registerTag("button",org.richa.tags.extjs.form.Button.class) ;
		registerTag("formbutton",org.richa.tags.extjs.form.FormButton.class) ;
		
		//Helpers
		registerTag("page",org.richa.tags.extjs.helper.Page.class) ; 
		registerTag("script",org.richa.tags.extjs.helper.Script.class) ;
		registerTag("style",org.richa.tags.extjs.helper.Style.class) ;
		
		//Containers
		registerTag("fieldset",org.richa.tags.extjs.containers.FieldSet.class) ;
		registerTag("form",org.richa.tags.extjs.containers.Form.class) ;
		registerTag("column",org.richa.tags.extjs.containers.Column.class) ;
		
		//Controls
		registerTag("tabpanel",org.richa.tags.extjs.controls.tab.TabPanel.class) ;
		registerTag("tabpanelitem",org.richa.tags.extjs.controls.tab.TabPanelItem.class) ;
		
		//Eye Candy
		registerTag("formwrapper",org.richa.tags.extjs.eyecandy.FormWrapper.class) ;
		
		//Layouts
		registerTag("borderlayout",org.richa.tags.extjs.layouts.BorderLayout.class) ;
		registerTag("layoutregion",org.richa.tags.extjs.layouts.LayoutRegion.class) ;
		registerTag("contentpanel",org.richa.tags.extjs.layouts.ContentPanel.class) ;
		registerTag("nestedlayoutpanel",org.richa.tags.extjs.layouts.NestedLayoutPanel.class) ;
	}
}
