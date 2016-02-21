package com.github.rjwestman.editableLabel;

import javafx.beans.property.*;
import javafx.scene.control.Skin;
import javafx.scene.control.TextField;

import java.net.URL;

public class EditableLabel extends TextField {

    /************************************************************************
     *                                                                      *
     * Constructors                                                         *
     *                                                                      *
     ***********************************************************************/
    // Constructors and helper methods for constructors

    public EditableLabel() {
        this("");
    }

    public EditableLabel(String text) {
        super(text);
        getStyleClass().setAll("editable-label");
        init();
    }

    private void init() {
        editableClicks = new SimpleIntegerProperty(1);
        baseText = new SimpleStringProperty(getText());
        setFocusTraversable(false);
        setEditable(false);
    }

    /************************************************************************
     *                                                                      *
     * Properties                                                           *
     *                                                                      *
     ***********************************************************************/
    // Declaration, getters and setters for the properties of this control

    private IntegerProperty editableClicks;
    public int getEditableClicks() { return editableClicks.get(); }
    public IntegerProperty editableClicksProperty() { return editableClicks; }
    public void setEditableClicks(int editableClicks) { this.editableClicks.set(editableClicks); }

    private StringProperty baseText;
    public String getBaseText() { return baseText.get(); }
    public StringProperty baseTextProperty() { return baseText; }
    public void setBaseText(String baseText) { this.baseText.set(baseText); }

    /************************************************************************
     *                                                                      *
     * Methods                                                              *
     *                                                                      *
     ***********************************************************************/

    @Override
    protected Skin<?> createDefaultSkin() { return new EditableLabelSkin(this); }

    /************************************************************************
     *                                                                      *
     * Stylesheet Related                                                   *
     *                                                                      *
     ***********************************************************************/

    @Override
    public String getUserAgentStylesheet() {
        URL pathToCSS = EditableLabel.class.getResource("editablelabel.css");
        if ( pathToCSS != null ) {
            return pathToCSS.toExternalForm();
        } else {
            System.err.println("CSS file for EditableLabel could not be found.");
            return null;
        }
    }


}
