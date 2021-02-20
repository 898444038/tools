package com.ming.tools.binding4.bind.property;

import java.beans.PropertyChangeListener;

public interface Observable {

    void addPropertyChangeListener(PropertyChangeListener listener);

    void removePropertyChangeListener(PropertyChangeListener listener);

}
