package com.github.jozefkis.autocompletecb;

/**
 *
 * @author YO
 * @param <T>
 */
public interface TextExtractor<T>
{
    String extract(T item);
    
    static <T> TextExtractor<T> defaultExtractor()
    {
        return item -> item.toString();
    }
}
