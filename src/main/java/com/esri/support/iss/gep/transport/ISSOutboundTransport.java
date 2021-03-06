package com.esri.support.iss.gep.transport;

import java.io.IOException;
import java.nio.ByteBuffer;

import com.esri.ges.core.component.ComponentException;
import com.esri.ges.core.component.RunningException;
import com.esri.ges.core.component.RunningState;
import com.esri.ges.framework.i18n.BundleLogger;
import com.esri.ges.framework.i18n.BundleLoggerFactory;
import com.esri.ges.transport.OutboundTransportBase;
import com.esri.ges.transport.TransportDefinition;

public class ISSOutboundTransport extends OutboundTransportBase
{
  /**
   * Initialize the i18n Bundle Logger
   * 
   * See {@link BundleLogger} for more info.
   */
  private static final BundleLogger LOGGER = BundleLoggerFactory.getLogger(ISSOutboundTransport.class);

  public ISSOutboundTransport(TransportDefinition definition) throws ComponentException
  {
    super(definition);
  }

  @SuppressWarnings("unused")
  private void applyProperties() throws IOException
  {
    // This method shows how to extract properties that were set by your user
    boolean myBooleanProperty = (Boolean) getProperty("broadcast").getValue();
    String myStringProperty = getProperty("host").getValueAsString();
  }

  @SuppressWarnings("unused")
  public void receive(ByteBuffer buffer, String channelId)
  {
    // This function is called whenever a GeoEvent is processed by an adapter,
    // and translated into raw bytes.
    // As an author, you are responsible for reading from the buffer to remove
    // data from it. Any data left in
    // The buffer will be left there for you to read again the next time this
    // function is called. This allows you
    // to buffer up larger amounts of data before sending them (e.g. filling up
    // a socket packet before sending it).
    byte b = 0;
    while (buffer.hasRemaining())
      b = buffer.get();
  }

  public void start() throws RunningException
  {
    try
    {
      setRunningState(RunningState.STARTING);
      applyProperties();
      // As a transport author, this is where you would add code to create
      // resources, start threads, etc.
      setRunningState(RunningState.STARTED);
    }
    catch (IOException e)
    {
      LOGGER.error("INIT_ERROR", e, getClass().getName());
      setRunningState(RunningState.ERROR);
    }
  }
}
