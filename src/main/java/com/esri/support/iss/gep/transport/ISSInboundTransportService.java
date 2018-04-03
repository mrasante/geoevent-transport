package com.esri.support.iss.gep.transport;

import com.esri.ges.core.component.ComponentException;
import com.esri.ges.transport.Transport;
import com.esri.ges.transport.TransportServiceBase;
import com.esri.ges.transport.TransportType;

public class ISSInboundTransportService extends TransportServiceBase
{
  public ISSInboundTransportService()
  {
//    definition = new XmlTransportDefinition(getResourceAsStream("sample-inbound-transport-definition.xml"));
    definition = new ISSInboundTransportDefinition(TransportType.INBOUND);
//    definition = getTransportDefinition();
    
  }
  
  public Transport createTransport() throws ComponentException
  {
    return new ISSInboundTransport(definition);
  }
}