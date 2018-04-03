package com.esri.support.iss.gep.transport;

import com.esri.ges.core.component.ComponentException;
import com.esri.ges.transport.Transport;
import com.esri.ges.transport.TransportServiceBase;
import com.esri.ges.transport.util.XmlTransportDefinition;

public class ISSOutboundTransportService extends TransportServiceBase
{
  public ISSOutboundTransportService()
  {
    definition = new XmlTransportDefinition(getResourceAsStream("sample-outbound-transport-definition.xml"));
//    definition = getTransportDefinition();
  }

  public Transport createTransport() throws ComponentException
  {
    return new ISSOutboundTransport(definition);
  }
}