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
package com.allen_sauer.gwt.log.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.logging.client.LogConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Static logging functions for client code.
 */
public final class Log {
  private static Map<String, Logger> loggerList = new HashMap<>();

  private static Logger getLogger(String category) {
    return loggerList.computeIfAbsent(category, LoggerFactory::getLogger);
  }

  /**
   * Register a new logger.
   *
   * @param logger the logger to add.
   */
  public static void addLogger(Logger logger) {
    loggerList.put(logger.getName(), logger);
  }

  /**
   * Supported loggers will have their output cleared. Alternatively, some loggers may either insert
   * separator text, or may do nothing.
   */
  public static void clear() {
    loggerList.clear();
  }

  /**
   * Log a <code>DEBUG</code> level message with no exception information.
   *
   * @param message the text to be logged
   */
  public static void debug(String message) {
    debug(message, (Throwable) null);
  }

  /**
   * Log a <code>DEBUG</code> level message from within a JSNI try/catch block, e.g.
   *
   * <pre>
   *   private native void jsniTryCatchExample()
   *   /&#42;-{
   *     try {
   *       // throws exception
   *       non_existant_variable.non_existant_method();
   *     } catch(e) {
   *       &#64;com.allen_sauer.gwt.log.client.Log::debug(Ljava/lang/String;Lcom/google/gwt/core/client/JavaScriptObject;)("Caught JSNI Exception", e);
   *     }
   *   }-&#42;/;
   * </pre>
   *
   * @param message the text to be logged
   * @param e the native JavaScript exception object to be logged
   */
  public static void debug(String message, JavaScriptObject e) {
    debug("gwt-log", message, e);
  }

  /**
   * Log a <code>DEBUG</code> level message with no exception information.
   *
   * @param category the category to be logged
   * @param message the text to be logged
   */
  public static void debug(String category, String message) {
    debug(category, message, (Throwable) null);
  }

  /**
   * Log a <code>DEBUG</code> level message from within a JSNI try/catch block, e.g.
   *
   * <pre>
   *   private native void jsniTryCatchExample()
   *   /&#42;-{
   *     try {
   *       // throws exception
   *       non_existant_variable.non_existant_method();
   *     } catch(e) {
   *       &#64;com.allen_sauer.gwt.log.client.Log::debug(Ljava/lang/String;Ljava/lang/String;Lcom/google/gwt/core/client/JavaScriptObject;)("foo", "Caught JSNI Exception", e);
   *     }
   *   }-&#42;/;
   * </pre>
   *
   * @param category the category to be logged
   * @param message the text to be logged
   * @param e the native JavaScript exception object to be logged
   */
  public static void debug(String category, String message, JavaScriptObject e) {
    getLogger(category).debug(message, e);
  }

  /**
   * Log a <code>DEBUG</code> level message from within a Java try/catch block, e.g.
   *
   * <pre>
   *   private native void javaTryCatchExample() {
   *     try {
   *       throw new RuntimeException();
   *     } catch(e) {
   *       Log.debug("foo", "Caught Java Exception", e);
   *     }
   *   }
   * </pre>
   *
   * @param category the category to be logged
   * @param message the text to be logged
   * @param e the exception to be logged
   */
  public static void debug(String category, String message, Throwable e) {
    debug(category, message, e);
  }

  /**
   * Log a <code>DEBUG</code> level message from within a Java try/catch block, e.g.
   *
   * <pre>
   *   private native void javaTryCatchExample() {
   *     try {
   *       throw new RuntimeException();
   *     } catch(e) {
   *       Log.debug("Caught Java Exception", e);
   *     }
   *   }
   * </pre>
   *
   * @see Log#debug(String)
   * @see Log#debug(String, JavaScriptObject)
   *
   * @param message the text to be logged
   * @param e the exception to be logged
   */
  public static void debug(String message, Throwable e) {
    debug("gwt-log", message, e);
  }

  /**
   * Log a <code>ERROR</code> level message with no exception information.
   *
   * @see Log#error(String, JavaScriptObject)
   * @see Log#error(String, Throwable)
   *
   * @param message the text to be logged
   */
  public static void error(String message) {
    error(message, (Throwable) null);
  }

  /**
   * Log a <code>ERROR</code> level message from within a JSNI try/catch block, e.g.
   *
   * <pre>
   *   private native void jsniTryCatchExample()
   *   /&#42;-{
   *     try {
   *       // throws exception
   *       non_existant_variable.non_existant_method();
   *     } catch(e) {
   *       &#64;com.allen_sauer.gwt.log.client.Log::error(Ljava/lang/String;Lcom/google/gwt/core/client/JavaScriptObject;)("Caught JSNI Exception", e);
   *     }
   *   }-&#42;/;
   * </pre>
   *
   * @see Log#error(String)
   * @see Log#error(String, Throwable)
   *
   * @param message the text to be logged
   * @param e the native JavaScript exception object to be logged
   */
  public static void error(String message, JavaScriptObject e) {
    error("gwt-log", message, e);
  }

  /**
   * Log a <code>ERROR</code> level message with no exception information.
   *
   * @param category the category to be logged
   * @param message the text to be logged
   */
  public static void error(String category, String message) {
    error(category, message, (Throwable) null);
  }

  /**
   * Log a <code>ERROR</code> level message from within a JSNI try/catch block, e.g.
   *
   * <pre>
   *   private native void jsniTryCatchExample()
   *   /&#42;-{
   *     try {
   *       // throws exception
   *       non_existant_variable.non_existant_method();
   *     } catch(e) {
   *       &#64;com.allen_sauer.gwt.log.client.Log::error(Ljava/lang/String;Ljava/lang/String;Lcom/google/gwt/core/client/JavaScriptObject;)("foo", "Caught JSNI Exception", e);
   *     }
   *   }-&#42;/;
   * </pre>
   *
   * @param category the category to be logged
   * @param message the text to be logged
   * @param e the native JavaScript exception object to be logged
   */
  public static void error(String category, String message, JavaScriptObject e) {
    getLogger(category).error(message, e);
  }

  /**
   * Log a <code>ERROR</code> level message from within a Java try/catch block, e.g.
   *
   * <pre>
   *   private native void javaTryCatchExample() {
   *     try {
   *       throw new RuntimeException();
   *     } catch(e) {
   *       Log.error("foo", "Caught Java Exception", e);
   *     }
   *   }
   * </pre>
   *
   * @param category the category to be logged
   * @param message the text to be logged
   * @param e the exception to be logged
   */
  public static void error(String category, String message, Throwable e) {
    getLogger(category).error(message, e);
  }

  /**
   * Log a <code>ERROR</code> level message from within a Java try/catch block, e.g.
   *
   * <pre>
   *   private native void javaTryCatchExample() {
   *     try {
   *       throw new RuntimeException();
   *     } catch(e) {
   *       Log.error("Caught Java Exception", e);
   *     }
   *   }
   * </pre>
   *
   * @see Log#error(String)
   * @see Log#error(String, JavaScriptObject)
   *
   * @param message the text to be logged
   * @param e the exception to be logged
   */
  public static void error(String message, Throwable e) {
    error("gwt-log", message, e);
  }

  /**
   * Log a <code>FATAL</code> level message with no exception information.
   *
   * @see Log#fatal(String, JavaScriptObject)
   * @see Log#fatal(String, Throwable)
   *
   * @param message the text to be logged
   */
  public static void fatal(String message) {
    fatal(message, (Throwable) null);
  }

  /**
   * Log a <code>FATAL</code> level message from within a JSNI try/catch block, e.g.
   *
   * <pre>
   *   private native void jsniTryCatchExample()
   *   /&#42;-{
   *     try {
   *       // throws exception
   *       non_existant_variable.non_existant_method();
   *     } catch(e) {
   *       &#64;com.allen_sauer.gwt.log.client.Log::fatal(Ljava/lang/String;Lcom/google/gwt/core/client/JavaScriptObject;)("Caught JSNI Exception", e);
   *     }
   *   }-&#42;/;
   * </pre>
   *
   * @see Log#fatal(String)
   * @see Log#fatal(String, Throwable)
   *
   * @param message the text to be logged
   * @param e the native JavaScript exception object to be logged
   */
  public static void fatal(String message, JavaScriptObject e) {
    fatal("gwt-log", message, e);
  }

  /**
   * Log a <code>FATAL</code> level message with no exception information.
   *
   * @param category the category to be logged
   * @param message the text to be logged
   */
  public static void fatal(String category, String message) {
    fatal(category, message, (Throwable) null);
  }

  /**
   * Log a <code>FATAL</code> level message from within a JSNI try/catch block, e.g.
   *
   * <pre>
   *   private native void jsniTryCatchExample()
   *   /&#42;-{
   *     try {
   *       // throws exception
   *       non_existant_variable.non_existant_method();
   *     } catch(e) {
   *       &#64;com.allen_sauer.gwt.log.client.Log::fatal(Ljava/lang/String;Ljava/lang/String;Lcom/google/gwt/core/client/JavaScriptObject;)("foo", "Caught JSNI Exception", e);
   *     }
   *   }-&#42;/;
   * </pre>
   *
   * @param category the category to be logged
   * @param message the text to be logged
   * @param e the native JavaScript exception object to be logged
   */
  public static void fatal(String category, String message, JavaScriptObject e) {
    getLogger(category).error(message, e);
  }

  /**
   * Log a <code>FATAL</code> level message from within a Java try/catch block, e.g.
   *
   * <pre>
   *   private native void javaTryCatchExample() {
   *     try {
   *       throw new RuntimeException();
   *     } catch(e) {
   *       Log.fatal("foo", "Caught Java Exception", e);
   *     }
   *   }
   * </pre>
   *
   * @param category the category to be logged
   * @param message the text to be logged
   * @param e the exception to be logged
   */
  public static void fatal(String category, String message, Throwable e) {
    getLogger(category).error(message, e);
  }

  /**
   * Log a <code>FATAL</code> level message from within a Java try/catch block, e.g.
   *
   * <pre>
   *   private native void javaTryCatchExample() {
   *     try {
   *       throw new RuntimeException();
   *     } catch(e) {
   *       Log.fatal("Caught Java Exception", e);
   *     }
   *   }
   * </pre>
   *
   * @see Log#fatal(String)
   * @see Log#fatal(String, JavaScriptObject)
   *
   * @param message the text to be logged
   * @param e the exception to be logged
   */
  public static void fatal(String message, Throwable e) {
    fatal("gwt-log", message, e);
  }

  /**
   * Log a <code>INFO</code> level message with no exception information.
   *
   * @see Log#info(String, JavaScriptObject)
   * @see Log#info(String, Throwable)
   *
   * @param message the text to be logged
   */
  public static void info(String message) {
    info(message, (Throwable) null);
  }

  /**
   * Log a <code>INFO</code> level message from within a JSNI try/catch block, e.g.
   *
   * <pre>
   *   private native void jsniTryCatchExample()
   *   /&#42;-{
   *     try {
   *       // throws exception
   *       non_existant_variable.non_existant_method();
   *     } catch(e) {
   *       &#64;com.allen_sauer.gwt.log.client.Log::info(Ljava/lang/String;Lcom/google/gwt/core/client/JavaScriptObject;)("Caught JSNI Exception", e);
   *     }
   *   }-&#42;/;
   * </pre>
   *
   * @see Log#info(String)
   * @see Log#info(String, Throwable)
   *
   * @param message the text to be logged
   * @param e the native JavaScript exception object to be logged
   */
  public static void info(String message, JavaScriptObject e) {
    info("gwt-log", message, e);
  }

  /**
   * Log a <code>INFO</code> level message with no exception information.
   *
   * @param category the category to be logged
   * @param message the text to be logged
   */
  public static void info(String category, String message) {
    info(category, message, (Throwable) null);
  }

  /**
   * Log a <code>INFO</code> level message from within a JSNI try/catch block, e.g.
   *
   * <pre>
   *   private native void jsniTryCatchExample()
   *   /&#42;-{
   *     try {
   *       // throws exception
   *       non_existant_variable.non_existant_method();
   *     } catch(e) {
   *       &#64;com.allen_sauer.gwt.log.client.Log::info(Ljava/lang/String;Ljava/lang/String;Lcom/google/gwt/core/client/JavaScriptObject;)("foo", "Caught JSNI Exception", e);
   *     }
   *   }-&#42;/;
   * </pre>
   *
   * @param category the category to be logged
   * @param message the text to be logged
   * @param e the native JavaScript exception object to be logged
   */
  public static void info(String category, String message, JavaScriptObject e) {
    getLogger(category).info(message, e);
  }

  /**
   * Log a <code>INFO</code> level message from within a Java try/catch block, e.g.
   *
   * <pre>
   *   private native void javaTryCatchExample() {
   *     try {
   *       throw new RuntimeException();
   *     } catch(e) {
   *       Log.info("foo", "Caught Java Exception", e);
   *     }
   *   }
   * </pre>
   *
   * @param category the category to be logged
   * @param message the text to be logged
   * @param e the exception to be logged
   */
  public static void info(String category, String message, Throwable e) {
    getLogger(category).info(message, e);
  }

  /**
   * Log a <code>INFO</code> level message from within a Java try/catch block, e.g.
   *
   * <pre>
   *   private native void javaTryCatchExample() {
   *     try {
   *       throw new RuntimeException();
   *     } catch(e) {
   *       Log.info("Caught Java Exception", e);
   *     }
   *   }
   * </pre>
   *
   * @see Log#info(String)
   * @see Log#info(String, JavaScriptObject)
   *
   * @param message the text to be logged
   * @param e the exception to be logged
   */
  public static void info(String message, Throwable e) {
    info("gwt-log", message, e);
  }

  /**
   * Guard utility method to prevent expensive parameter evaluation side effects when logging is set
   * at a higher level, e.g.
   *
   * <pre>
   *   // parameter(s) are evaluated even if method call does nothing
   *   Log.debug(...);
   *
   *   if (Log.isDebugEnabled()) {
   *     // code inside the guard is only conditionally evaluated
   *     Log.debug(...);
   *   }
   * </pre>
   *
   * @return <code>true</code> if the current log level is at least <code>DEBUG</code>
   */
  public static boolean isDebugEnabled() {
    return getLogger("gwt-log").isDebugEnabled();
  }

  /**
   * Guard utility method to prevent expensive parameter evaluation side effects when logging is set
   * at a higher level.
   *
   * @see #isDebugEnabled()
   *
   * @return <code>true</code> if the current log level is at least <code>ERROR</code>
   */
  public static boolean isErrorEnabled() {
    return getLogger("gwt-log").isErrorEnabled();
  }

  /**
   * Guard utility method to prevent expensive parameter evaluation side effects when logging is set
   * at a higher level.
   *
   * @see #isDebugEnabled()
   *
   * @return <code>true</code> if the current log level is at least <code>FATAL</code>
   */
  public static boolean isFatalEnabled() {
    return getLogger("gwt-log").isErrorEnabled();
  }

  /**
   * Guard utility method to prevent expensive parameter evaluation side effects when logging is set
   * at a higher level.
   *
   * @see #isDebugEnabled()
   *
   * @return <code>true</code> if the current log level is at least <code>INFO</code>
   */
  public static boolean isInfoEnabled() {
    return getLogger("gwt-log").isInfoEnabled();
  }

  /**
   * Guard utility method to prevent expensive parameter evaluation side effects when logging is
   * disabled.
   *
   * @see #isTraceEnabled()
   * @see #isDebugEnabled()
   * @see #isInfoEnabled()
   * @see #isWarnEnabled()
   * @see #isErrorEnabled()
   * @see #isFatalEnabled()
   *
   * @return <code>true</code> if the current log level is not <code>OFF</code>
   */
  public static boolean isLoggingEnabled() {
    return LogConfiguration.loggingIsEnabled();
  }

  /**
   * Guard utility method to prevent expensive parameter evaluation side effects when logging is set
   * at a higher level, e.g.
   *
   * <pre>
   *   // parameter(s) are evaluated even if method call does nothing
   *   Log.trace(...);
   *
   *   if (Log.isTraceEnabled()) {
   *     // code inside the guard is only conditionally evaluated
   *     Log.trace(...);
   *   }
   * </pre>
   *
   * @return <code>true</code> if the current log level is at least <code>TRACE</code>
   */
  public static boolean isTraceEnabled() {
    return getLogger("gwt-log").isTraceEnabled();
  }

  /**
   * Guard utility method to prevent expensive parameter evaluation side effects when logging is set
   * at a higher level.
   *
   * @see #isDebugEnabled()
   *
   * @return <code>true</code> if the current log level is at least <code>WARN</code>
   */
  public static boolean isWarnEnabled() {
    return getLogger("gwt-log").isWarnEnabled();
  }

  /**
   * Log a <code>TRACE</code> level message with no exception information.
   *
   * @see Log#trace(String, JavaScriptObject)
   * @see Log#trace(String, Throwable)
   *
   * @param message the text to be logged
   */
  public static void trace(String message) {
    trace(message, (Throwable) null);
  }

  /**
   * Log a <code>TRACE</code> level message from within a JSNI try/catch block, e.g.
   *
   * <pre>
   *   private native void jsniTryCatchExample()
   *   /&#42;-{
   *     try {
   *       // throws exception
   *       non_existant_variable.non_existant_method();
   *     } catch(e) {
   *       &#64;com.allen_sauer.gwt.log.client.Log::trace(Ljava/lang/String;Lcom/google/gwt/core/client/JavaScriptObject;)("Caught JSNI Exception", e);
   *     }
   *   }-&#42;/;
   * </pre>
   *
   * @see Log#trace(String)
   * @see Log#trace(String, Throwable)
   *
   * @param message the text to be logged
   * @param e the native JavaScript exception object to be logged
   */
  public static void trace(String message, JavaScriptObject e) {
    trace("gwt-log", message, e);
  }

  /**
   * Log a <code>TRACE</code> level message with no exception information.
   *
   * @param category the category to be logged
   * @param message the text to be logged
   */
  public static void trace(String category, String message) {
    trace(category, message, (Throwable) null);
  }

  /**
   * Log a <code>TRACE</code> level message from within a JSNI try/catch block, e.g.
   *
   * <pre>
   *   private native void jsniTryCatchExample()
   *   /&#42;-{
   *     try {
   *       // throws exception
   *       non_existant_variable.non_existant_method();
   *     } catch(e) {
   *       &#64;com.allen_sauer.gwt.log.client.Log::trace(Ljava/lang/String;Ljava/lang/String;Lcom/google/gwt/core/client/JavaScriptObject;)("foo", "Caught JSNI Exception", e);
   *     }
   *   }-&#42;/;
   * </pre>
   *
   * @param category the category to be logged
   * @param message the text to be logged
   * @param e the native JavaScript exception object to be logged
   */
  public static void trace(String category, String message, JavaScriptObject e) {
    getLogger(category).trace(message, e);
  }

  /**
   * Log a <code>TRACE</code> level message from within a Java try/catch block, e.g.
   *
   * <pre>
   *   private native void javaTryCatchExample() {
   *     try {
   *       throw new RuntimeException();
   *     } catch(e) {
   *       Log.trace("foo", "Caught Java Exception", e);
   *     }
   *   }
   * </pre>
   *
   * @param category the category to be logged
   * @param message the text to be logged
   * @param e the exception to be logged
   */
  public static void trace(String category, String message, Throwable e) {
    getLogger(category).trace(message, e);
  }

  /**
   * Log a <code>TRACE</code> level message from within a Java try/catch block, e.g.
   *
   * <pre>
   *   private native void javaTryCatchExample() {
   *     try {
   *       throw new RuntimeException();
   *     } catch(e) {
   *       Log.trace("Caught Java Exception", e);
   *     }
   *   }
   * </pre>
   *
   * @see Log#trace(String)
   * @see Log#trace(String, JavaScriptObject)
   *
   * @param message the text to be logged
   * @param e the exception to be logged
   */
  public static void trace(String message, Throwable e) {
    trace("gwt-log", message, e);
  }

  /**
   * Log a <code>WARN</code> level message with no exception information.
   *
   * @see Log#warn(String, JavaScriptObject)
   * @see Log#warn(String, Throwable)
   *
   * @param message the text to be logged
   */
  public static void warn(String message) {
    warn(message, (Throwable) null);
  }

  /**
   * Log a <code>WARN</code> level message from within a JSNI try/catch block, e.g.
   *
   * <pre>
   *   private native void jsniTryCatchExample()
   *   /&#42;-{
   *     try {
   *       // throws exception
   *       non_existant_variable.non_existant_method();
   *     } catch(e) {
   *       &#64;com.allen_sauer.gwt.log.client.Log::warn(Ljava/lang/String;Lcom/google/gwt/core/client/JavaScriptObject;)("Caught JSNI Exception", e);
   *     }
   *   }-&#42;/;
   * </pre>
   *
   * @see Log#warn(String)
   * @see Log#warn(String, Throwable)
   *
   * @param message the text to be logged
   * @param e the native JavaScript exception object to be logged
   */
  public static void warn(String message, JavaScriptObject e) {
    warn("gwt-log", message, e);
  }

  /**
   * Log a <code>WARN</code> level message with no exception information.
   *
   * @param category the category to be logged
   * @param message the text to be logged
   */
  public static void warn(String category, String message) {
    warn(category, message, (Throwable) null);
  }

  /**
   * Log a <code>WARN</code> level message from within a JSNI try/catch block, e.g.
   *
   * <pre>
   *   private native void jsniTryCatchExample()
   *   /&#42;-{
   *     try {
   *       // throws exception
   *       non_existant_variable.non_existant_method();
   *     } catch(e) {
   *       &#64;com.allen_sauer.gwt.log.client.Log::warn(Ljava/lang/String;Ljava/lang/String;Lcom/google/gwt/core/client/JavaScriptObject;)("foo", "Caught JSNI Exception", e);
   *     }
   *   }-&#42;/;
   * </pre>
   *
   * @param category the category to be logged
   * @param message the text to be logged
   * @param e the native JavaScript exception object to be logged
   */
  public static void warn(String category, String message, JavaScriptObject e) {
    getLogger(category).warn(message, e);
  }

  /**
   * Log a <code>WARN</code> level message from within a Java try/catch block, e.g.
   *
   * <pre>
   *   private native void javaTryCatchExample() {
   *     try {
   *       throw new RuntimeException();
   *     } catch(e) {
   *       Log.warn("foo", "Caught Java Exception", e);
   *     }
   *   }
   * </pre>
   *
   * @param category the category to be logged
   * @param message the text to be logged
   * @param e the exception to be logged
   */
  public static void warn(String category, String message, Throwable e) {
    getLogger(category).warn(message, e);
  }

  /**
   * Log a <code>WARN</code> level message from within a Java try/catch block, e.g.
   *
   * <pre>
   *   private native void javaTryCatchExample() {
   *     try {
   *       throw new RuntimeException();
   *     } catch(e) {
   *       Log.warn("Caught Java Exception", e);
   *     }
   *   }
   * </pre>
   *
   * @see Log#warn(String)
   * @see Log#warn(String, Throwable)
   *
   * @param message the text to be logged
   * @param e the exception to be logged
   */
  public static void warn(String message, Throwable e) {
    warn("gwt-log", message, e);
  }

  /**
   * Default private constructor, to be used by GWT module initialization only.
   */
  private Log() {
  }
}