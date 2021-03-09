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
package org.slf4gwt.remote.batching.server;

import com.google.gwt.core.server.StackTraceDeobfuscator;
import com.google.gwt.logging.server.RemoteLoggingServiceUtil;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.slf4gwt.remote.batching.shared.RemoteBatchLoggingService;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * Server-side code for the remote batch log handler.
 */
public class RemoteBatchLoggingServiceImpl extends RemoteServiceServlet implements RemoteBatchLoggingService {

  private static final Logger logger = Logger.getLogger(RemoteServiceServlet.class.getName());

  // No deobfuscator by default
  private StackTraceDeobfuscator deobfuscator = null;
  private String loggerNameOverride = null;

  @Override
  public String logOnServer(List<LogRecord> logRecords) {
    if (logRecords != null) {
      return logRecords.stream().map(this::logOnServer).filter(Objects::nonNull).findFirst().orElse(null);
    }

    return null;
  }

  /**
   * Logs a Log Record which has been serialized using GWT RPC on the server.
   * @return either an error message, or null if logging is successful.
   */
  @Override
  public String logOnServer(LogRecord lr) {
    String strongName = getPermutationStrongName();
    try {
      RemoteLoggingServiceUtil.logOnServer(
              lr, strongName, deobfuscator, loggerNameOverride);
    } catch (RemoteLoggingServiceUtil.RemoteLoggingException e) {
      logger.log(Level.SEVERE, "Remote logging failed", e);
      return "Remote logging failed, check stack trace for details.";
    }
    return null;
  }

  /**
   * By default, messages are logged to a logger that has the same name as
   * the logger that created them on the client. If you want to log all messages
   * from the client to a logger with another name, you can set the override
   * using this method.
   */
  public void setLoggerNameOverride(String override) {
    loggerNameOverride = override;
  }

  /**
   * By default, this service does not do any deobfuscation. In order to do
   * server-side deobfuscation, you must copy the symbolMaps files to a
   * directory visible to the server and set the directory using this method.
   */
  public void setSymbolMapsDirectory(String symbolMapsDir) {
    deobfuscator = StackTraceDeobfuscator.fromFileSystem(symbolMapsDir);
  }
}
