package com.github.jozefkis.autocompletecb;

import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.SwingUtilities;
import javax.swing.text.JTextComponent;

/**
 *
 * @author YO
 * @param <T>
 */
public class AutoCompleteCBDecorator<T>
{

    private final JComboBox<T> targetComboBox;
    private final List<T> originalList;
    private final TextExtractor<T> textExtractor;
    private boolean isAdjusting = false;
    private String currentTypedText = "";

    public AutoCompleteCBDecorator(JComboBox<T> comboBox, List<T> originalList, TextExtractor<T> textExtractor)
    {
        this.originalList = originalList;
        this.textExtractor = textExtractor;

        this.targetComboBox = comboBox;
        this.targetComboBox.setEditable(true);

        targetComboBox.setModel(new DefaultComboBoxModel(new Vector(originalList)));

        initAutocomplete();
    }

    private void initAutocomplete()
    {
        Component editorComponent = targetComboBox.getEditor().getEditorComponent();

        if (editorComponent instanceof JTextComponent)
        {
            final JTextComponent editorComponentText = (JTextComponent) editorComponent;

            editorComponentText.addKeyListener(new KeyAdapter()
            {
                @Override
                public void keyTyped(KeyEvent e)
                {
                    if (e.getKeyChar() == KeyEvent.VK_ENTER || e.getKeyChar() == '\n')
                    {
                        return;
                    }

                    SwingUtilities.invokeLater(() -> updateList());
                }

                @Override
                public void keyPressed(KeyEvent e)
                {
                    if (e.getKeyChar() == KeyEvent.VK_ENTER || e.getKeyChar() == '\n')
                    {
                        e.consume();

                        T selected = (T) targetComboBox.getSelectedItem();
                        targetComboBox.getEditor().setItem(selected);
                        targetComboBox.hidePopup();

                        Component source = (Component) e.getSource();
                        source.transferFocus();

                    }
                }
            });

            targetComboBox.addItemListener(e ->
            {
                if (e.getStateChange() == java.awt.event.ItemEvent.SELECTED)
                {
                    Object item = e.getItem();
                    if (item instanceof String) // if true, then user entered item which is not in the list
                    {
                        targetComboBox.setSelectedItem(null);
                        editorComponentText.setText("");
                        currentTypedText = "";

                        SwingUtilities.invokeLater(() ->
                        {
                            updateList();
                            targetComboBox.hidePopup();
                        });

                        return;
                    }

                    @SuppressWarnings("unchecked")
                    T selectedItem = (T) e.getItem();

                    if (selectedItem != null)
                    {
                        String text = textExtractor.extract(selectedItem);
                        
                        isAdjusting = true;
                        editorComponentText.setText(text);
                        isAdjusting = false;
                    }
                }
            });
        }
    }

    private void updateList()
    {
        if (isAdjusting)
        {
            return;
        }

        currentTypedText = ((JTextComponent) targetComboBox.getEditor().getEditorComponent()).getText();

        List<T> filteredList = new ArrayList<>();
        if (currentTypedText.isEmpty())
        {
            filteredList.addAll(originalList);
        }
        else
        {
            String typedTextLower = currentTypedText.toLowerCase();
            for (T item : originalList)
            {
                String itemText = textExtractor.extract(item);
                if (itemText != null && itemText.toLowerCase().startsWith(typedTextLower))
                {
                    filteredList.add(item);
                }
            }
        }

        isAdjusting = true;
        try
        {
            DefaultComboBoxModel<T> newModel = new DefaultComboBoxModel<>(new Vector<>(filteredList));
            targetComboBox.setModel(newModel);

            ((JTextComponent) targetComboBox.getEditor().getEditorComponent()).setText(currentTypedText);

            if (!currentTypedText.isEmpty() && !filteredList.isEmpty() && filteredList.size() < originalList.size())
            {
                SwingUtilities.invokeLater(()->targetComboBox.setPopupVisible(true));
            }
            else if (!currentTypedText.isEmpty() && filteredList.isEmpty())
            {
                SwingUtilities.invokeLater(()->targetComboBox.setPopupVisible(false));
            }
            else if (currentTypedText.isEmpty())
            {
                SwingUtilities.invokeLater(()->targetComboBox.setPopupVisible(false));
                targetComboBox.setSelectedItem(null);
            }
        }
        finally
        {
            isAdjusting = false;
        }
    }
    
}
