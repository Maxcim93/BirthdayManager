package com.maxim.webjs.storage;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Максим on 29.08.2016.
 */
public interface Storage<T> {

    public Collection<T> values();

    public int add(final T object);

    public void edit(final T object);

    public void delete(final int id);

    public T get(final int id);

    public Collection<T> get(Map<String,Object> params);

    public void close();
}
