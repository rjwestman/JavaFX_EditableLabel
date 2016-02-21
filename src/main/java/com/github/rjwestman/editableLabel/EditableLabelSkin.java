package com.github.rjwestman.editableLabel;

import com.sun.javafx.scene.control.skin.TextFieldSkin;
import javafx.application.Platform;
import javafx.collections.SetChangeListener;
import javafx.css.PseudoClass;
import javafx.scene.text.Text;

/**
 * The Skin Class for EditableLabel
 *
 * @sa EditableLabel, EditableLabelBehavior
 */
public class EditableLabelSkin extends TextFieldSkin {

    private EditableLabel editableLabel;
    private Boolean editableState;

    /************************************************************************
     *                                                                      *
     *                                                                      *
     * \defgroup Constructors                                               *
     * Constructors and helper methods for constructors                     *
     *                                                                      *
     * @{                                                                   *
     ***********************************************************************/

    public EditableLabelSkin(final EditableLabel editableLabel) {
        this(editableLabel, new EditableLabelBehavior(editableLabel));
    }
    public EditableLabelSkin(final EditableLabel editableLabel, final EditableLabelBehavior editableLabelBehavior) {
        super(editableLabel, editableLabelBehavior);
        this.editableLabel = editableLabel;
        init();
    }

    private void init() {
        editableState = false;

        Platform.runLater(this::updateVisibleText);

        // Register listeners and binds
        editableLabel.getPseudoClassStates().addListener( (SetChangeListener<PseudoClass>) e -> {
            if (e.getSet().contains(PseudoClass.getPseudoClass("editable"))) {
                if ( !editableState ) {
                    // editableState change to editable
                    editableState = true;
                    updateVisibleText();
                }
            } else {
                if ( editableState ) {
                    // editableState change to not editable
                    editableState = false;
                    updateVisibleText();
                }
            }
        });
        editableLabel.widthProperty().addListener( observable -> updateVisibleText() );
        editableLabel.baseTextProperty().addListener( observable -> updateVisibleText() );
    }

    /************************************************************************
     * @}                                                                   *
     *                                                                      *
     * \defgroup ControlStateChanges                                        *
     * Handles visual changes on state change that are not or cannot be     *
     * handled via css                                                      *
     *                                                                      *
     * @{                                                                   *
     ***********************************************************************/

    /**
     * Updates the visual text using the baseText
     */
    private void updateVisibleText() {
        String baseText = editableLabel.getBaseText();
        if ( !editableState ) {
            editableLabel.setText(calculateClipString(baseText));
        } else {
            editableLabel.setText(baseText);
            editableLabel.positionCaret(baseText.length());
        }
    }

    /**
     * Truncates text to fit into the EditableLabel
     *
     * @param text The text that needs to be truncated
     * @return The truncated text with an appended "..."
     */
    private String calculateClipString(String text) {
        double labelWidth = editableLabel.getWidth();

        Text layoutText = new Text(text);
        layoutText.setFont(editableLabel.getFont());

        if ( layoutText.getLayoutBounds().getWidth() < labelWidth ) {
            return text;
        } else {
            layoutText.setText(text+"...");
            while ( layoutText.getLayoutBounds().getWidth() > labelWidth ) {
                text = text.substring(0, text.length()-1);
                layoutText.setText(text+"...");
            }
            return text+"...";
        }
    }

    /************************************************************************
     * @}                                                                   *
     *                                                                      *
     * \defgroup SkinLayout                                                 *
     * Lays out the elements of the control                                 *
     * (e.g. calculating and setting sizes and bounds or changing number    *
     * of grid rows and columns)                                            *
     *                                                                      *
     * @{                                                                   *
     ***********************************************************************/

    /** @} */

}
