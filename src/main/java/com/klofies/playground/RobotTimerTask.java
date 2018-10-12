/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.klofies.playground;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimerTask;

/**
 *
 * @author eugene
 */
public class RobotTimerTask extends TimerTask {

    private PropertyChangeEvent lastEvent;

    private final List<PropertyChangeListener> listener = new ArrayList<>();
    
    public void addPropertyChangeListener(PropertyChangeListener newListener) {

        listener.add(newListener);
    }
    
    private void raiseSensorProbeAddEvent()
    {
        PropertyChangeEvent lastEvtValue = null;
        if(lastEvent!=null)
        {
            //Make sure
            lastEvtValue = (PropertyChangeEvent)lastEvent.getNewValue();
        }

        Calendar cal = Calendar.getInstance();//yy, ma, dd, hh, mm, ss);

        PropertyChangeEvent evt = new PropertyChangeEvent(this, "TimerTick", lastEvtValue, lastEvent);

        if(listener.size()>0 && listener!=null)
        {
            for (PropertyChangeListener name : listener) {
                //Make sure we don't raise same Event, but all listeners shoul get the same event
//                    if(lastSecureEvent==null || !evt.getNewValue().equals(lastSecureEvent.getNewValue()))
//                    {
////                        if(evt.getNewValue()!= evt.getOldValue())
////                        {
                        name.propertyChange(evt);
////                        }
//                    }
            }
            lastEvent = evt;
        }

        
    }
    
    @Override
    public void run() {
        //timer.schedule(this, 2000);
        raiseSensorProbeAddEvent();
    }

    
}
