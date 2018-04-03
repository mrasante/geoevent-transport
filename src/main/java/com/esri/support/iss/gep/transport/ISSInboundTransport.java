package com.esri.support.iss.gep.transport;

import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;

import com.esri.ges.core.component.ComponentException;
import com.esri.ges.core.component.RunningException;
import com.esri.ges.core.component.RunningState;
import com.esri.ges.framework.i18n.BundleLogger;
import com.esri.ges.framework.i18n.BundleLoggerFactory;
import com.esri.ges.transport.InboundTransportBase;
import com.esri.ges.transport.TransportDefinition;

public class ISSInboundTransport extends InboundTransportBase implements Runnable
{
  /**
   * Initialize the i18n Bundle Logger
   * 
   * See {@link BundleLogger} for more info.
   */
  private static final BundleLogger LOGGER = BundleLoggerFactory.getLogger(ISSInboundTransport.class);
  
	private int eventSize = 1;
	private int eventRate = 1;
	private Thread thread = null;
	private ByteBuffer byteBuffer = ByteBuffer.allocate(eventSize);
	private String channelId = "1";

	public ISSInboundTransport(TransportDefinition definition) throws ComponentException
	{
		super(definition);
	}
	
	

	public void applyProperties() throws Exception
	{
		if (getProperty("eventSize").isValid())
		{
			int value = (Integer) getProperty("eventSize").getValue();
			if( value > 0 && value != eventSize )
			{
				eventSize = value;
				byteBuffer = ByteBuffer.allocate(eventSize);
			}
		}
		if (getProperty("eventRate").isValid())
		{
			int value = (Integer) getProperty("eventRate").getValue();
			if( value > 0 && value != eventRate )
			{
				eventRate = value;
			}
		}
	}

	public void run()
	{
		try
		{
			applyProperties();
			setRunningState(RunningState.STARTED);
			while( getRunningState() == RunningState.STARTED )
			{
				try
				{
					for( int i = 0; i < eventSize; i++ )
						byteBuffer.put( (byte) i );
					byteBuffer.flip();
					byteListener.receive(byteBuffer, channelId);
					byteBuffer.compact();
				}
				catch (BufferOverflowException boe)
				{
				  LOGGER.error("BUFFER_OVERFLOW_ERROR", boe);
					byteBuffer.clear();
				}
				catch (Exception e)
				{
				  LOGGER.error("UNEXPECTED_ERROR", e);
					stop();
				}
			}
		}
		catch (Throwable ex)
		{
		  LOGGER.error(ex.getMessage(), ex);
			setRunningState(RunningState.ERROR);
		}
	}

	@SuppressWarnings("incomplete-switch")
  public void start() throws RunningException
	{
    switch (getRunningState())
		{
		case STARTING:
		case STARTED:
		case STOPPING:
			return;
		}
		setRunningState(RunningState.STARTING);
		thread = new Thread(this);
		thread.start();
	}
}