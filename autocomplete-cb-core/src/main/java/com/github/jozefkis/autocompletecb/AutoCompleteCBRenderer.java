package com.github.jozefkis.autocompletecb;


import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 *
 * @author YO
 * @param <T>
 */
public class AutoCompleteCBRenderer<T> extends DefaultListCellRenderer
{

    private final TextExtractor<T> textExtractor;

    public AutoCompleteCBRenderer(TextExtractor<T> textExtractor)
    {
        this.textExtractor = textExtractor;
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus)
    {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (value != null)
        {
            try
            {
                T item = (T) value;
                setText(textExtractor.extract(item));
            }
            catch (ClassCastException e)
            {
                setText(value.toString());
            }
        }
        else
        {
            setText("");
        }

        return this;
    }
}
