package com.example.project5;

/**
 * Customizable interface to be used in Cafe project
 * @author Mahad Rauf, MOeez Shahid
 */
public interface Customizable {
    /**
     * to be implemented: add the parameter object
     * @param obj object to add
     * @return true of added, false otherwise
     */
    boolean add(Object obj);

    /**
     * to be implemented: remove the parameter object
     * @param obj object to remove
     * @return true if removed, false otherwise
     */
    boolean remove(Object obj);
}