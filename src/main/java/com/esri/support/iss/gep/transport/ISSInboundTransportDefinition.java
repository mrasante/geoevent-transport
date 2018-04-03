package com.esri.support.iss.gep.transport;

import com.esri.ges.core.property.PropertyDefinition;
import com.esri.ges.core.property.PropertyType;
import com.esri.ges.framework.i18n.BundleLogger;
import com.esri.ges.framework.i18n.BundleLoggerFactory;
import com.esri.ges.transport.TransportDefinitionBase;
import com.esri.ges.transport.TransportType;

public class ISSInboundTransportDefinition extends TransportDefinitionBase{

	private final BundleLogger LOGGER = BundleLoggerFactory.getLogger(this.getClass());
	public ISSInboundTransportDefinition(TransportType type) {
		super(type);
		// TODO Auto-generated constructor stub
		try {
			
			//Event Size property Definition
			PropertyDefinition eventSizePropertyDef = new PropertyDefinition();
			eventSizePropertyDef.setPropertyName("eventSize");
			eventSizePropertyDef.setType(PropertyType.Integer);
			eventSizePropertyDef.setLabel("${com.esri.support.iss.gep.iss-transport.TRANSPORT_IN_EVENT_SIZE_LBL}");
			eventSizePropertyDef.setDefaultValue("10");
			eventSizePropertyDef.setMandatory(true);
			eventSizePropertyDef.setReadOnly(false);
			eventSizePropertyDef.setDescription("${com.esri.support.iss.gep.iss-transport.TRANSPORT_IN_EVENT_SIZE_DESC}");
			
			
			//Event Size property Definition
			PropertyDefinition eventRatePropertyDef = new PropertyDefinition();
			eventRatePropertyDef.setPropertyName("eventRate");
			eventRatePropertyDef.setType(PropertyType.Integer);
			eventRatePropertyDef.setLabel("${com.esri.support.iss.gep.iss-transport.TRANSPORT_IN_EVENT_RATE_LBL}");
			eventRatePropertyDef.setDefaultValue("1");
			eventRatePropertyDef.setMandatory(true);
			eventRatePropertyDef.setReadOnly(false);
			eventRatePropertyDef.setDescription("${com.esri.support.iss.gep.iss-transport.TRANSPORT_IN_EVENT_RATE_DESC}");
			
			propertyDefinitions.put(eventRatePropertyDef.getPropertyName(), eventRatePropertyDef);
			propertyDefinitions.put(eventSizePropertyDef.getPropertyName(), eventSizePropertyDef);
			
		}catch (Exception exception) {
			LOGGER.debug("Exception in Property Definition: "+ exception.getMessage());
		}
	}
	
	
	@Override
	public String getName() {
		return "ISSInboundTransport";
	}
	
	@Override
	public String getLabel() {
		return "${com.esri.support.iss.gep.iss-transport.TRANSPORT_IN_LABEL}";
	}
	
	
	@Override
	public String getDescription() {
		return "${com.esri.support.iss.gep.iss-transport.TRANSPORT_IN_DESC}";
	}

}
