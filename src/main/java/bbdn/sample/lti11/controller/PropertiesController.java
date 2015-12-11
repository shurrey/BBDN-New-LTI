package bbdn.sample.lti11.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bbdn.sample.lti11.dao.PropertiesDAO;
import bbdn.sample.lti11.model.Properties;
import bbdn.sample.lti11.util.HashMapConverter;
import blackboard.base.FormattedText;


@Controller
public class PropertiesController {

	@Autowired
	private PropertiesDAO _dao;
	
	@RequestMapping("/config")
	public ModelAndView create( HttpServletRequest request, HttpServletResponse response )
	{
		ModelAndView mv = new ModelAndView("prop_view");
		 
		Properties props = _dao.load();
		
		String url = props.getUrl();
		boolean postGrades = props.isPostGradesEnabled();
		boolean[] sendDataConfig = {false,false,false};
		sendDataConfig[props.getSendDataConfig()] = true;
		boolean sendName = props.isSendNameEnabled();
		boolean sendRole = props.isSendRoleEnabled();
		boolean sendEmail = props.isSendEMailEnabled();
		boolean linkConfigAllowed = props.isLinkConfigAllowed();
		String key = props.getKey();
		String secret = props.getSecret();
		String customParams = HashMapConverter.convertHashMapCsvToDisplay(props.getCustomParams());
		boolean displaySplash = props.isSplashScreenEnabled();
		FormattedText splashMsg = FormattedText.toFormattedText(props.getSplashScreenMessage());
		boolean[] idTypeToSend = {false,false,false};
		idTypeToSend[props.getIdTypeToSend()] = true;
		
		mv.addObject("url", url);
		mv.addObject("postGrades", postGrades);
		mv.addObject("sendDataConfig", sendDataConfig);
		mv.addObject("sendName", sendName);
		mv.addObject("sendRole", sendRole);
		mv.addObject("sendEmail", sendEmail);
		mv.addObject("linkConfigAllowed",linkConfigAllowed);
		mv.addObject("key", key);
		mv.addObject("secret", secret);
		mv.addObject("customParams", customParams);
		mv.addObject("displaySplash", displaySplash);
		mv.addObject("splashMsg", splashMsg);
		mv.addObject("idTypeToSend", idTypeToSend);
		mv.addObject("props", props);
    	
    	return mv;   
	}

	@RequestMapping("/configSave")
	public void saveProps(HttpServletRequest request, HttpServletResponse response,
							@RequestParam("url") String url,
							@RequestParam("key") String key,
							@RequestParam("secret") String secret,
							@RequestParam("customParams") String customParams,
							@RequestParam("linkConfigAllowed") String linkConfigAllowed,
							@RequestParam("idTypeToSend") String idTypeToSend,
							@RequestParam("postGrades") String postGrades,
							@RequestParam("sendData") String sendData,
							@RequestParam("sendName") String sendName,
							@RequestParam("sendRole") String sendRole,
							@RequestParam("sendEmail") String sendEmail,
							@RequestParam("displaySplash") String displaySplash,
							@RequestParam("splashMsgtext") String splashMsg
							) {

		Properties props = _dao.load();
        
		HashMap<String,String> tempCustomParams = HashMapConverter.convertToStringToHashMap(customParams);
		
		
        //set all prefs to incoming request parameter values
		props.setUrl(url);
		props.enablePostGrades(Boolean.valueOf(postGrades));
        props.setSendDataConfig(Integer.parseInt(sendData));
        props.enableSendName(Boolean.valueOf(sendName));
        props.enableSendRole(Boolean.valueOf(sendRole));
        props.enableSendEMail(Boolean.valueOf(sendEmail));
        props.allowLinkSettings(Boolean.valueOf(linkConfigAllowed));
        props.setIdTypeToSend(Integer.parseInt(idTypeToSend));
        props.setKey(key);
        props.setSecret(secret);
        props.setCustomParams(tempCustomParams.toString());
        props.enableSplashScreen(Boolean.valueOf(displaySplash));
        props.setSplashScreenMessage(splashMsg);
        
        //save the prefs
        _dao.save(props);
        
        try {
			response.sendRedirect("config");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
