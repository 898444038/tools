package com.ming.tools.binding4.bind.property;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoggingPropertyChangeListener implements PropertyChangeListener {

    private static final Log LOG = LogFactory.getLog(LoggingPropertyChangeListener.class);

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LOG.info("属性变化[" + evt.getPropertyName() + ": " + evt.getOldValue() + " => " + evt.getNewValue()+"]");
    }

}