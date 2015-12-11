package bbdn.sample.lti11.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bbdn.sample.lti11.dao.PropertiesDAO;
import bbdn.sample.lti11.model.Properties;
import bbdn.sample.lti11.util.HashMapConverter;
import blackboard.platform.blti.BasicLTILauncher;
import blackboard.base.FormattedText;

@Controller
public class ToolController {
	
	@Autowired
	private PropertiesDAO _dao;
	
	@RequestMapping("/ltitool")
	public void launchLtiTool(HttpServletRequest request, HttpServletResponse response,
									@RequestParam("tool_type") String toolType) {
		
		Properties props = new Properties();
		
		try {
			props = _dao.load();
		} catch (Exception e) {
			
		}
		
		String url = props.getUrl();
		//boolean postGrades = props.isPostGradesEnabled();
		boolean[] sendDataConfig = {false,false,false};
		sendDataConfig[props.getSendDataConfig()] = true;
		boolean sendName = props.isSendNameEnabled();
		boolean sendRole = props.isSendRoleEnabled();
		boolean sendEmail = props.isSendEMailEnabled();
		//boolean linkConfigAllowed = props.isLinkConfigAllowed();
		String key = props.getKey();
		String secret = props.getSecret();
		String customParams = props.getCustomParams();
		boolean displaySplash = props.isSplashScreenEnabled();
		FormattedText splashMsg = FormattedText.toFormattedText(props.getSplashScreenMessage());
		BasicLTILauncher.IdTypeToSend idTypeToSend = BasicLTILauncher.IdTypeToSend.UUID;
		switch(props.getIdTypeToSend()) {
		case 0:
			idTypeToSend = BasicLTILauncher.IdTypeToSend.BATCH_UID;
			break;
		case 1:
			idTypeToSend = BasicLTILauncher.IdTypeToSend.PK1;
			break;
		default:
			idTypeToSend = BasicLTILauncher.IdTypeToSend.UUID;
			break;
		}
	
		/* 
		 *	Instantiate BasicLTILauncher object
		 *	BasicLTILauncher(String url, String key, String secret, String resourceLinkID)
		 *		.addResourceLinkInformation(String title, String description)
		 *		.addCurrentUserInformation(boolean includeRole, boolean includeName, boolean includeEmail)
		 *		.addCurrentCourseInformation(void)
		 *		.addCustomToolParameters (Map<String, String> params)
		 *		.addLaunchPresentationInformation (Map<String,String> params);
		 */
		 	 	
	 	HashMap<String,String> customParamMap = HashMapConverter.convertToStringToHashMap(customParams);
	 
		BasicLTILauncher launcher = new BasicLTILauncher( url, key, secret, toolType )
	       .addResourceLinkInformation( "LTI Tool - " + toolType, "LTI Connection utilizing the Blackboard Learn LTI API Framework" )
	       //.addCurrentUserInformation( sendRole, sendName, sendEmail, (IdTypeToSend) BasicLTILauncher.IdTypeToSend.fromFieldName(idTypeToSend, BasicLTILauncher.IdTypeToSend.class) )
	       .addCurrentUserInformation( sendRole, sendName, sendEmail, idTypeToSend )
	       .addCustomToolParameters( customParamMap );
	    
	    if (!toolType.equals("user_tool")) {
	    	//launcher.addCurrentCourseInformation( (IdTypeToSend) BasicLTILauncher.IdTypeToSend.fromFieldName(idTypeToSend, BasicLTILauncher.IdTypeToSend.class) );
	    	launcher.addCurrentCourseInformation(idTypeToSend);
	    }
	
		//Launch BLTI connection 
		//launch(HttpServletRequest request, HttpServletResponse response, boolean showSplashScreen, FormattedText splashMessage)
	 	launcher.launch( request, response, displaySplash, splashMsg );
		
	}
}
