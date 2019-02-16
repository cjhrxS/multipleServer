package ua.ita.smartcarservice.service.alerts;

import ua.ita.smartcarservice.dto.alerts.FaultCodeDto;

public interface FaultCodeService {

//	String getFaultCodeDescription (String faultCode);
	
	FaultCodeDto getFaultCode (String faultCode);
}
