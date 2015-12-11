package bbdn.sample.lti11.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import bbdn.sample.lti11.dao.GradeExchangeDAO;
import bbdn.sample.lti11.dao.PropertiesDAO;
import bbdn.sample.lti11.map.Grade;
import bbdn.sample.lti11.model.GradeExchange;
import blackboard.persist.KeyNotFoundException;
import blackboard.platform.spring.web.annotations.NoXSRF;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.StreamFilter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;

@Controller
public class OutcomesController {

	@Autowired
	private PropertiesDAO _propsDao;
	
	@Autowired
	private GradeExchangeDAO _gradeDao;
	
	@NoXSRF
	@RequestMapping(value="/ltigradeexchange", method=RequestMethod.POST)//, consumes="application/xml", produces="application/xml" )
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String receiveGrades(HttpServletRequest request, HttpServletResponse response) throws IOException, XMLStreamException { /* ,
									@RequestBody Grade grade) { */
		//try {
			//List<GradeExchange> gradeEx = _gradeDao.searchBySourcedId(grade.getSourcedId());
		//}
		//catch (KeyNotFoundException knfe) {
			
		//}
		
		/* XMLInputFactory xif = XMLInputFactory.newFactory();
        StreamSource xml = new StreamSource(request.getInputStream());
        XMLStreamReader xsr = xif.createXMLStreamReader(xml);
        
        Grade grade = new Grade();*/
		
		JAXBContext jc;
		try {
			jc = JAXBContext.newInstance( "blackboard.plugin.lti11.map.Grade" );
		
			Unmarshaller u = jc.createUnmarshaller();
	 
			XMLStreamReader xmlStreamReader = XMLInputFactory.newInstance().createXMLStreamReader( request.getInputStream() );
	 
			Grade grade = (Grade) u.unmarshal( xmlStreamReader );
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        //System.out.println(xsr.toString());
		
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?> + "
				+ "<imsx_POXEnvelopeResponse xmlns = \"http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0\">"
				+ "<imsx_POXHeader>"
				+ "<imsx_POXResponseHeaderInfo>"
				//+ "<imsx_version>" + grade.getVersion() + "</imsx_version>"
				+ "<imsx_version>V1.0</imsx_version>"
				//+ "<imsx_messageIdentifier>" + grade.getMessageId() + "</imsx_messageIdentifier>"
				+ "<imsx_messageIdentifier>1234567890</imsx_messageIdentifier>"
				+ "<imsx_statusInfo>"
				+ "<imsx_codeMajor>success</imsx_codeMajor>"
				+ "<imsx_severity>status</imsx_severity>"
				//+ "<imsx_description>Score for " + grade.getSourcedId() + " is now " + Float.toString(grade.getScore()) + "</imsx_description>"
				//+ "<imsx_messageRefIdentifier>" + grade.getMessageId() + "</imsx_messageRefIdentifier>"
				+ "<imsx_description>Score for 1234567890 is now 1.0</imsx_description>"
				+ "<imsx_messageRefIdentifier>1234567890</imsx_messageRefIdentifier>"
				+ "<imsx_operationRefIdentifier>replaceResult</imsx_operationRefIdentifier>"
				+ "</imsx_statusInfo>"
				+ "</imsx_POXResponseHeaderInfo>"
				+ "</imsx_POXHeader>"
				+ "<imsx_POXBody>"
				+ "<replaceResultResponse/>"
				+ "</imsx_POXBody>"
				+ "</imsx_POXEnvelopeResponse>";
	}
}
