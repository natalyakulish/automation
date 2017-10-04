package de.asos.exceptions;

public class FilterPanelNotExistsException extends RuntimeException {

    public FilterPanelNotExistsException(String panelName) {
        super(panelName);
    }

}
