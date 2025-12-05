package com.github.jozefkis.autocompletecb;

import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author YO
 */
public class AutoCompleteCBDecoratorUtils
{
    public static <T> void decorate(JComboBox<T> comboBox, List<T> allItems)
    {
        TextExtractor<T> extractor = TextExtractor.defaultExtractor();
        decorate(comboBox, allItems, extractor);
    }
    
    public static <T> void decorate(JComboBox<T> comboBox, List<T> allItems, TextExtractor<T> extractor)
    {           
        new AutoCompleteCBDecorator<>(
                comboBox,
                allItems,
                extractor
        );
        comboBox.setRenderer(new AutoCompleteCBRenderer<>(extractor));
    }
}
