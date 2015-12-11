package bbdn.sample.lti11.map;

//import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;


/*
<?xml version="1.0" encoding="UTF-8"?>
<imsx_POXEnvelopeRequest xmlns="http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0">
    <imsx_POXHeader>
        <imsx_POXRequestHeaderInfo>
            <imsx_version>V1.0</imsx_version>
            <imsx_messageIdentifier>db868079d2ec49e68f157fca2b4d4139</imsx_messageIdentifier>
        </imsx_POXRequestHeaderInfo>
    </imsx_POXHeader>
    <imsx_POXBody>
        <replaceResultRequest>
            <resultRecord>
                <sourcedGUID>
                    <sourcedId>bbgc10gi44</sourcedId>
                </sourcedGUID>
                <result>
                    <resultScore>
                        <language>en</language>
                        <textString>0.0</textString>
                    </resultScore>
                </result>
            </resultRecord>
        </replaceResultRequest>
    </imsx_POXBody>
</imsx_POXEnvelopeRequest>

 */
@XmlRootElement(name="imsx_POXEnvelopeRequest")
public class Grade {
 
    private String version;
    private String messageId;
    private String sourcedId;
    private String language;
    private float score;
 
    public Grade() { }
    
    @XmlElement(name="imsx_version")
    public String getVersion() {
    return version;
    }
 
    public void setVersion(String version) {
    this.version = version;
    }
 
    @XmlElement(name="imsx_messageIdentifier")
    public String getMessageId() {
    return messageId;
    }
 
    public void setMessageId(String messageId) {
    this.messageId = messageId;
    }   
    
    @XmlElement(name="sourcedId")
    public String getSourcedId() {
    return sourcedId;
    }
 
    public void setSourcedId(String sourcedId) {
    this.sourcedId = sourcedId;
    }   
    @XmlElement(name="language")
    public String getLanguage() {
    return language;
    }
 
    public void setLanguage(String language) {
    this.language = language;
    }   
    @XmlElement(name="textString")
    public float getScore() {
    return score;
    }
 
    public void setScore(float score) {
    this.score = score;
    }   
    
}