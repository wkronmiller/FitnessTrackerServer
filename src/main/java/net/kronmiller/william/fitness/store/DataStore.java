package net.kronmiller.william.fitness.store;

import java.io.Closeable;

/**
 * Created by wkronmiller on 7/14/17.
 */
public interface DataStore extends Closeable {
    public void init();
    public void close();
}
