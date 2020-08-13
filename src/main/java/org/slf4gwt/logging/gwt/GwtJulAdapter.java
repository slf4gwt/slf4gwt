/**
 * MIT License
 *
 * Copyright (c) 2020 slf4gwt
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.slf4gwt.logging.gwt;

import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GwtJulAdapter extends MarkerIgnoringBase {

  private final Logger logger;

  public GwtJulAdapter(String name) {
    this.name = name;
    logger = Logger.getLogger(name);
  }

  public boolean isTraceEnabled() {
    return logger.isLoggable(Level.FINEST);
  }

  public void trace(String msg) {
    logMessageOrThrowable(Level.FINEST, msg, null);
  }

  public void trace(String format, Object arg) {
    logObjects(Level.FINEST, format, arg);
  }

  public void trace(String format, Object arg1, Object arg2) {
    logObjects(Level.FINEST, format, arg1, arg2);
  }

  public void trace(String format, Object... arguments) {
    logObjects(Level.FINEST, format, arguments);
  }

  public void trace(String msg, Throwable t) {
    logMessageOrThrowable(Level.FINEST, msg, t);
  }

  public boolean isDebugEnabled() {
    return logger.isLoggable(Level.FINE);
  }

  public void debug(String msg) {
    logMessageOrThrowable(Level.FINE, msg, null);
  }

  public void debug(String format, Object arg) {
    logObjects(Level.FINE, format, arg);
  }

  public void debug(String format, Object arg1, Object arg2) {
    logObjects(Level.FINE, format, arg1, arg2);
  }

  public void debug(String format, Object... arguments) {
    logObjects(Level.FINE, format, arguments);
  }

  public void debug(String msg, Throwable t) {
    logMessageOrThrowable(Level.FINE, msg, t);
  }

  public boolean isInfoEnabled() {
    return logger.isLoggable(Level.INFO);
  }

  public void info(String msg) {
    logMessageOrThrowable(Level.INFO, msg, null);
  }

  public void info(String format, Object arg) {
    logObjects(Level.INFO, format, arg);
  }

  public void info(String format, Object arg1, Object arg2) {
    logObjects(Level.FINEST, format, arg1, arg2);
  }

  public void info(String format, Object... arguments) {
    logObjects(Level.FINEST, format, arguments);
  }

  public void info(String msg, Throwable t) {
    logMessageOrThrowable(Level.INFO, msg, t);
  }

  public boolean isWarnEnabled() {
    return logger.isLoggable(Level.WARNING);
  }

  public void warn(String msg) {
    logMessageOrThrowable(Level.WARNING, msg, null);
  }

  public void warn(String format, Object arg) {
    logObjects(Level.WARNING, format, arg);
  }

  public void warn(String format, Object... arguments) {
    logObjects(Level.WARNING, format, arguments);
  }

  public void warn(String format, Object arg1, Object arg2) {
    logObjects(Level.WARNING, format, arg1, arg2);
  }

  public void warn(String msg, Throwable t) {
    logMessageOrThrowable(Level.WARNING, msg, t);
  }

  public boolean isErrorEnabled() {
    return logger.isLoggable(Level.SEVERE);
  }

  public void error(String msg) {
    logMessageOrThrowable(Level.SEVERE, msg, null);
  }

  public void error(String format, Object arg) {
    logObjects(Level.SEVERE, format, arg);
  }

  public void error(String format, Object arg1, Object arg2) {
    logObjects(Level.SEVERE, format, arg1, arg2);
  }

  public void error(String format, Object... arguments) {
    logObjects(Level.SEVERE, format, arguments);
  }

  public void error(String msg, Throwable t) {
    logMessageOrThrowable(Level.SEVERE, msg, t);
  }

  private void logMessageOrThrowable(Level level, String msg, Throwable t) {
    if (logger.isLoggable(level)) {
      logger.log(level, msg, t);
    }
  }

  private void logObjects(Level level, String format, Object... argArray) {
    if (logger.isLoggable(level)) {
      FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
      logger.log(level, ft.getMessage(), ft.getThrowable());
    }
  }
}
