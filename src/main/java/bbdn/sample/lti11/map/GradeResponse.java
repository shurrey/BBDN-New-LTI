package bbdn.sample.lti11.map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

/*
<?xml version="1.0" encoding="UTF-8"?>
<imsx_POXEnvelopeResponse xmlns = "http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0">
  <imsx_POXHeader>
    <imsx_POXResponseHeaderInfo>
      <imsx_version>V1.0</imsx_version>
      <imsx_messageIdentifier>4560</imsx_messageIdentifier>
      <imsx_statusInfo>
        <imsx_codeMajor>success</imsx_codeMajor>
        <imsx_severity>status</imsx_severity>
        <imsx_description>Score for 3124567 is now 0.92</imsx_description>
        <imsx_messageRefIdentifier>999999123</imsx_messageRefIdentifier>
        <imsx_operationRefIdentifier>replaceResult</imsx_operationRefIdentifier>
      </imsx_statusInfo>
    </imsx_POXResponseHeaderInfo>
  </imsx_POXHeader>
  <imsx_POXBody>
    <replaceResultResponse/>
  </imsx_POXBody>
</imsx_POXEnvelopeResponse>
 */

@XmlRootElement(name="imsx_POXEnvelopeResponse")
//@Xml
public class GradeResponse {

}
