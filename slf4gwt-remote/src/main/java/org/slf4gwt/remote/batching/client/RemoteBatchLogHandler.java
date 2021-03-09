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
package org.slf4gwt.remote.batching.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.logging.client.RemoteLogHandlerBase;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.slf4gwt.remote.batching.shared.RemoteBatchLoggingService;
import org.slf4gwt.remote.batching.shared.RemoteBatchLoggingServiceAsync;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * remote log handler with batch log support.
 *
 * The Handler saves the LogRecord in a list waits until the LogRecord is published to the server. So
 * more log messages can be collected until the delivery is fulfilled.
 *
 * If a request is pending, the messages are collected, too. So the amount of log-requests to the server is optimized.
 * And the Handler has filter that removed log messages with a lower log level. So you can log on INFO on the client and
 * send only WARN and higher levels to the server.
 */
public class RemoteBatchLogHandler extends RemoteLogHandlerBase {
  private static final int MESSAGE_QUEUEING_DELAY_MILLIS = 100;

  private boolean callInProgressOrScheduled = false;

  private Throwable failure;

  private final Timer batchDeliveryTimer = new Timer() {
    @Override
    public void run() {
      service.logOnServer(logRecordList, callback);
      logRecordList.clear();
    }
  };

  private final ArrayList<LogRecord> logRecordList = new ArrayList<LogRecord>();

  class DefaultCallback implements AsyncCallback<String> {

    @Override
    public void onFailure(Throwable caught) {
      wireLogger.log(Level.SEVERE, "Remote logging failed: ", caught);
      failure = caught;
      callInProgressOrScheduled = false;
    }

    @Override
    public void onSuccess(String result) {
      if (result != null) {
        wireLogger.severe("Remote logging failed: " + result);
      } else {
        wireLogger.finest("Remote logging message acknowledged");
      }

      callInProgressOrScheduled = false;
      maybeTriggerRPC();
    }
  }

  private AsyncCallback<String> callback;
  private RemoteBatchLoggingServiceAsync service;

  public RemoteBatchLogHandler() {
    service = (RemoteBatchLoggingServiceAsync) GWT.create(RemoteBatchLoggingService.class);
    this.callback = new DefaultCallback();
  }

  @Override
  public void publish(LogRecord record) {
    if (failure != null) {
      // remote logger has been disabled
      return;
    }
    if (isLoggable(record)) {
      logRecordList.add(record);
      maybeTriggerRPC();
    }
  }

  private void maybeTriggerRPC() {
    if (failure == null && !callInProgressOrScheduled && !logRecordList.isEmpty()) {
      // allow a few log messages to accumulate before firing RPC
      batchDeliveryTimer.schedule(MESSAGE_QUEUEING_DELAY_MILLIS);
      callInProgressOrScheduled = true;
    }
  }

  @Override
  public boolean isLoggable(LogRecord record) {
    return super.isLoggable(record) && getLowestLogLevel().intValue() <= record.getLevel().intValue();
  }

  /**
   * the lowest loglevel of logs that are transferred to the server.
   *
   * This is configured in your gwt.xml file
   * @return the minimum LogLevel
   */
  public Level getLowestLogLevel() {
    return Level.ALL;
  }

  public static class RemoteBatchLogHandlerDebug extends RemoteBatchLogHandler {
    @Override
    public Level getLowestLogLevel() {
      return Level.FINE;
    }
  }

  public static class RemoteBatchLogHandlerInfo extends RemoteBatchLogHandler {
    @Override
    public Level getLowestLogLevel() {
      return Level.INFO;
    }
  }

  public static class RemoteBatchLogHandlerWarn extends RemoteBatchLogHandler {
    @Override
    public Level getLowestLogLevel() {
      return Level.WARNING;
    }
  }

  public static class RemoteBatchLogHandlerError extends RemoteBatchLogHandler {
    @Override
    public Level getLowestLogLevel() {
      return Level.SEVERE;
    }
  }
}
