/*
 * Copyright 2016 Arnell Christoper D. Dalid.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.rockyroadshub.planner.core.utils;

import java.awt.Component;
import java.awt.Toolkit;
import javax.swing.JOptionPane;

/**
 *
 * @author Arnell Christoper D. Dalid
 * @version 0.0.0
 * @since 1.8
 */
public final class LoggerUtils {
    private LoggerUtils() {}
    
    /**
     * Creates a <code>JOptionPane</code> (Message Dialog) then emits an audio beep.
     * 
     * @param parent  places dialog relative to it
     * @param message the Object to display
     * @param title   title of the dialog
     * @param type    type of dialog (e.g. JOptionPane.ERROR_MESSAGE)
     */
    public static void displayMessage(Component parent, String message, String title, int type) {
        Toolkit.getDefaultToolkit().beep();
        JOptionPane.showMessageDialog(parent, message, title, type);
    }   
    
    /**
     * Creates a <code>JOptionPane</code> (Confirm Dialog) then emits an audio beep.
     * 
     * @param parent  places dialog relative to it
     * @param message the Object to display
     * @param title   title of the dialog
     * 
     * @return JOptionPane.OK_OPTION or JOptionPane.CANCEL_OPTION
     */
    public static int displayOption(Component parent, String message, String title) {
        Toolkit.getDefaultToolkit().beep();
        return JOptionPane.showConfirmDialog(parent, message, title, JOptionPane.OK_CANCEL_OPTION);
    }   
    
    /**
     * Gets a stack trace obtained from an exception and organize it by line.
     * 
     * @param tb throwable
     * @return whole stack trace as a <code>String</code>
     * 
     * @see #traceBuilder
     */
    public static String getStackTrace(Throwable tb) {
        return traceBuilder(tb).toString();
    }
    
    /**
     * Organizes a stack trace obtained from an exception and displays
     * the error message in a <code>JOptionPane</code> and returns the whole
     * exception in <code>String</code>.
     * 
     * @param message exception message
     * @param title   exception title for <code>JOptionPane</code>
     * @param tb throwable
     * @return organized stack trace with <code>message</code>
     *         obtained from the exception
     * 
     * @see #displayMessage
     * @see #insertMessage
     */
    public static String getOrganizedTrace(String message, String title, Throwable tb) {
        return getOrganizedTrace(null, message, title, tb);
    }
    
    /**
     * Organizes a stack trace obtained from an exception and displays
     * the error message in a <code>JOptionPane</code> and returns the whole
     * exception in <code>String</code>.
     * 
     * @param component relative location of <code>JOptionPane</code>  
     * @param message   exception message
     * @param title     exception title for <code>JOptionPane</code>
     * @param tb throwable
     * @return organized stack trace with <code>message</code>
     *         obtained from the exception
     * 
     * @see #displayMessage
     * @see #insertMessage
     */
    public static String getOrganizedTrace
        (Component component, String message, String title, Throwable tb) 
    {
        displayMessage(component, message, title, JOptionPane.ERROR_MESSAGE);
        return insertMessage(message, traceBuilder(tb));
    }
    
    /**
     * Gets the raw stack trace and organize it by line.
     * 
     * @param tb throwable
     * @return whole stack trace as a <code>StringBuilder</code>
     * 
     * @see #traceStack
     * @see #insertMessage
     */
    private static StringBuilder traceBuilder(Throwable tb) {
        tb.printStackTrace(System.out);
        StringBuilder mg = new StringBuilder(System.lineSeparator());
        mg.append(tb);
        for(StackTraceElement stk : tb.getStackTrace()) {                         
            mg.append(System.lineSeparator()).append("\tat ").append(stk.toString());
        } 
        return mg;
    }
    
    /**
     * Inserts message into the raw stack trace obtained from
     * <code>traceBuilder</code>.
     * 
     * @param message message to be inserted
     * @param mg      organized stack trace obtained from <code>traceBuilder</code>
     * @return combined message and stack trace as a <code>String</code>
     * 
     * @see #getOrganizedTrace
     * @see #traceBuilder
     */
    private static String insertMessage(String message, StringBuilder mg) {
        return mg.insert(0, message).toString();
    }    
}
