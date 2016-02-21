package com.github.rjwestman.editableLabel;

import javafx.beans.property.*;
import javafx.scene.control.Skin;
import javafx.scene.control.TextField;

import java.net.URL;

/**
 * A TextField, that implements some Label functionality
 *
 * It acts as a Label, by removing the TextField style and making it non-editable.
 * It is also not focus traversable.
 *
 * When clicking on it, it will switch to editable mode
 * Changing focus away from the EditableLabel or pressing ENTER will save the changes made and deactivate editable mode.
 * When pressing ESC it will exit editable mode without saving the changes made.
 *
 * @sa EditableLabelSkin, EditableLabelBehavior
 */
public class EditableLabel extends TextField {

    /************************************************************************
     *                                                                      *
     *                                                                      *
     * \defgroup Constructors                                               *
     * Constructors and helper methods for constructors                     *
     *                                                                      *
     * @{                                                                   *
     ***********************************************************************/

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
     * @}                                                                   *
     *                                                                      *
     * \defgroup Properties                                                 *
     * Declaration, getters and setters for the properties of this control  *
     *                                                                      *
     * @{                                                                   *
     ***********************************************************************/

    /**
     * Clicks needed to enter editable-mode
      */
    private IntegerProperty editableClicks;
    public int getEditableClicks() { return editableClicks.get(); }
    public IntegerProperty editableClicksProperty() { return editableClicks; }
    public void setEditableClicks(int editableClicks) { this.editableClicks.set(editableClicks); }

    /**
     * This saves the text that is to be displayed
     *
     * Since we can't override the final set/get methods of the super class, we need to use this
     * to set the text that is to be displayed.
     * Since the displayed text can be a truncated base text we need to save the base in it's own property.
     */
    private StringProperty baseText;
    public String getBaseText() { return baseText.get(); }
    public StringProperty baseTextProperty() { return baseText; }
    public void setBaseText(String baseText) { this.baseText.set(baseText); }

    /************************************************************************
     * @}                                                                   *
     *                                                                      *
     * \defgroup Methods                                                    *
     *                                                                      *
     * @{                                                                   *
     ***********************************************************************/

    @Override
    protected Skin<?> createDefaultSkin() { return new EditableLabelSkin(this); }

    /************************************************************************
     * @}                                                                   *
     *                                                                      *
     * \defgroup StylesheetRelated                                          *
     *                                                                      *
     * @{                                                                   *
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

    /** @} */

}
