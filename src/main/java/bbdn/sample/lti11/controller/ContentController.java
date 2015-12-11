package bbdn.sample.lti11.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import blackboard.base.FormattedText;
import blackboard.data.ExtendedData;
import blackboard.data.ValidationException;
import blackboard.data.content.Content;
import blackboard.data.content.CourseDocument;
import blackboard.data.course.Course;
import blackboard.data.course.CourseMembership;
import blackboard.persist.Id;
import blackboard.persist.KeyNotFoundException;
import blackboard.persist.PersistenceException;
import blackboard.persist.PersistenceRuntimeException;
import blackboard.persist.content.ContentDbLoader;
import blackboard.persist.content.ContentDbPersister;
import blackboard.persist.course.CourseMembershipDbLoader;
import blackboard.platform.blti.BasicLTILauncher;
import blackboard.platform.context.ContextManagerFactory;
import blackboard.platform.context.UnsetContextException;
import blackboard.platform.gradebook2.GradableItem;
import blackboard.platform.gradebook2.GradableItemManager;
import blackboard.platform.gradebook2.GradebookManagerFactory;
import blackboard.platform.plugin.PlugInUtil;
import blackboard.platform.security.authentication.BbSecurityException;
import blackboard.servlet.util.DatePickerUtil;

import com.google.gson.Gson;

import bbdn.sample.lti11.dao.GradeExchangeDAO;
import bbdn.sample.lti11.dao.LinkConfigDAO;
import bbdn.sample.lti11.dao.PropertiesDAO;
import bbdn.sample.lti11.model.GradeExchange;
import bbdn.sample.lti11.model.LinkConfig;
import bbdn.sample.lti11.model.Properties;
import bbdn.sample.lti11.util.HashMapConverter;
import bbdn.sample.lti11.util.SourcedIdGen;
import bbdn.sample.lti11.util.SourcedIdGenException;


@Controller
public class ContentController {

	@Autowired
	private PropertiesDAO _dao;
	
	@Autowired
	private GradeExchangeDAO _gradeDao;
	
	@Autowired
	private LinkConfigDAO _linkDao;
	
	@RequestMapping("/create")
	public ModelAndView createLtiLink(HttpServletRequest request, HttpServletResponse response, 
										@RequestParam("course_id") String courseId,
										@RequestParam("content_id") String contentId) {
		
		ModelAndView mv = new ModelAndView("create");
		 
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
		mv.addObject("course_id", courseId);
		mv.addObject("content_id", contentId);
		mv.addObject("props", props);
		
		return mv;
	}
	
	@RequestMapping("/modify")
	public ModelAndView modifyLtiLink(HttpServletRequest request, HttpServletResponse response,  
										@RequestParam("course_id") String courseId,
										@RequestParam("content_id") String contentId) throws PersistenceException {
		
		ModelAndView mv = new ModelAndView("modify");
		
		Properties props = _dao.load();
		Gson gson = new Gson();
		
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
		
		if(linkConfigAllowed) {
			try {
				List<LinkConfig> links = _linkDao.searchByContentId(contentId);
				ListIterator<LinkConfig> iterator = links.listIterator();
				
				while(iterator.hasNext()) {
					LinkConfig link = iterator.next();
					@SuppressWarnings("unchecked")
					HashMap<String, String> customConfig = gson.fromJson(link.getConfig(),HashMap.class);
					key = customConfig.get("key");
					secret = customConfig.get("secret");
					displaySplash = Boolean.getBoolean(customConfig.get("displaySplash"));
					splashMsg = FormattedText.toFormattedText(customConfig.get("SplashMsg"));
				}
			} catch (KeyNotFoundException knfe) {
				
			}
		}
		
		ContentDbLoader contentLoader = ContentDbLoader.Default.getInstance();
		
		Content courseDoc = contentLoader.loadById(Id.generateId(Content.DATA_TYPE, contentId));
		
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
		mv.addObject("name", courseDoc.getTitle());
		mv.addObject("text", courseDoc.getBody());
		mv.addObject("availability", courseDoc.getIsAvailable());
		mv.addObject("tracking", courseDoc.getIsTracked());
		mv.addObject("props", props);

		
		return mv;
	}
	
	@RequestMapping("/saveltilink")
	public void saveLtiLink(HttpServletRequest request, HttpServletResponse response,  
			@RequestParam("course_id") String course_id, //
			@RequestParam("content_id") String content_id, //
			@RequestParam("name") String name, //
			@RequestParam("texttext") String description, //
			@RequestParam("availability") String availability, //
			@RequestParam("tracking") String tracking, //
			@RequestParam("dateTimeRestrictions_start_checkbox") String startDateSelected, //
			@RequestParam("dateTimeRestrictions_start_date") String startDate, //
			@RequestParam("dateTimeRestrictions_end_checkbox") String endDateSelected, //
			@RequestParam("dateTimeRestrictions_end_date") String endDate, //
			@RequestParam("post_grades") String postGrades, //
			@RequestParam("link_config") String linkConfigAllowed, //
			@RequestParam("key") String key, //
			@RequestParam("secret") String secret, //
			@RequestParam("customParams") String customParams, //
			@RequestParam("displaySplash") String displaySplash, //
			@RequestParam("splashMsgtext") String splashMsg //
	) throws BbSecurityException, PersistenceException, ValidationException {
		
		/*
		 * Removing gradingOptions for now
		 * ---------------------------
		 * @RequestParam("gradingOptions_enabled") String gradingOptionsEnabled,
			@RequestParam("gradingOptions_hasExistingGradableItem") String existingGradableItem,
			@RequestParam("gradingOptions_possible") String points,
			@RequestParam("visibleToStudents") String visibleToStudents,
			@RequestParam("gradingOptions_dueDate_in_use") String dueDateInUse,
			@RequestParam("gradingOptions_dueDate_datetime") String dueDateTime,
			@RequestParam("gradingOptions_dueDate_date") String dueDate,
			@RequestParam("gradingOptions_dueDate_time") String dueTime,
			
		 */
		
		Id courseId = Id.generateId( Course.DATA_TYPE, course_id );
		Id folderId = Id.generateId( CourseDocument.DATA_TYPE, content_id );

		ContentDbPersister persister= ContentDbPersister.Default.getInstance();

		HashMap<String,String> tempCustomParams = HashMapConverter.convertToStringToHashMap(customParams);
		
		Content courseDoc = new Content();
		courseDoc.setContentHandler("resource/x-bbdn-lti11-content");
		courseDoc.setCourseId( courseId );
		courseDoc.setParentId( folderId );
		courseDoc.setTitle( name );
		courseDoc.setBody( FormattedText.toFormattedText(description));

		if(!tempCustomParams.isEmpty()) {
			ExtendedData ed = new ExtendedData();
			
			Collection<String> cpKeys = tempCustomParams.keySet();
			Iterator<String> keyIterator = cpKeys.iterator();
			
			while(keyIterator.hasNext()) {
				String cpKey = keyIterator.next();
				ed.setValue(cpKey,tempCustomParams.get(cpKey));
			}
			courseDoc.setExtendedData(ed);
		}
		courseDoc.setIsAvailable(Boolean.parseBoolean(availability));
		if(startDateSelected.equalsIgnoreCase("1")) {
			courseDoc.setStartDate(DatePickerUtil.getDateFromPicker(startDateSelected, startDate));
		}
		
		if(endDateSelected.equalsIgnoreCase("1")) {
			courseDoc.setEndDate(DatePickerUtil.getDateFromPicker(endDateSelected, endDate));
		}
		
		courseDoc.setIsTracked(Boolean.parseBoolean(tracking));
		
		courseDoc.validate();
		persister.persist( courseDoc );
		
		if(postGrades.equalsIgnoreCase("true")) {  
			//GradableItem gi = new GradableItem();
			//GradableItemManager giManager = GradebookManagerFactory.getGradableItemManager();
			
			//if(existingGradableItem.equalsIgnoreCase("true")) {
			//	gi = giManager.getGradebookItemByContentId(courseDoc.getId());
			//}
			
			//gi.setCourseId(courseId);
			//gi.setCourseContentId(courseDoc.getId());
			//gi.setDisplayTitle(name);
			//gi.setPoints(Double.parseDouble(points));
			//gi.setTitle(name);
			//gi.setVisibleInBook(true);
			//gi.setVisibleToStudents(Boolean.parseBoolean(visibleToStudents));
			//gi.setDescription(FormattedText.toFormattedText(description));
			//gi.setDueDate(DatePickerUtil.getDateFromPicker(dueDateInUse, dueDateTime, true));
			
			  
			//giManager.persistGradebookItem(gi);
			
		}
		
		if(linkConfigAllowed.equalsIgnoreCase("true")) {
			LinkConfig customLtiCfg = new LinkConfig();
			Gson gson = new Gson();
			
			HashMap<String,String> customConfig = new HashMap<String,String>();
			customConfig.put("key", key);
			customConfig.put("secret", secret);
			customConfig.put("displaySplash", displaySplash);
			customConfig.put("splashMsg", splashMsg);
			
			String jsonConfig = gson.toJson(customConfig, HashMap.class);
			
			customLtiCfg.setConfig(jsonConfig);
			customLtiCfg.setContentId(Integer.parseInt(courseDoc.getId().toExternalString().split("_")[1]));
			
			_linkDao.persist(customLtiCfg);
			
		}
		/* @SuppressWarnings("unchecked")
		Map<String,String> map = request.getParameterMap();
		*/
	}

	@RequestMapping("/launch")
	public void launchLtiLink (HttpServletRequest request, HttpServletResponse response, 
										@RequestParam("content_id") String contentId,
										@RequestParam("course_id") String courseId
									   ) {
		Properties props = new Properties();
		
		try {
			props = _dao.load();
		} catch (Exception e) {
			
		}
		
		String url = props.getUrl();
		boolean postGrades = props.isPostGradesEnabled();
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
	 
		BasicLTILauncher launcher = new BasicLTILauncher( url, key, secret, "ContentId: " + contentId )
	       .addResourceLinkInformation( "LTI Content Handler - ContentId: ", "LTI Connection utilizing the Blackboard Learn LTI API Framework" )
	       .addCurrentUserInformation( sendRole, sendName, sendEmail, idTypeToSend )
	       .addCurrentCourseInformation(idTypeToSend)
		   .addCustomToolParameters( customParamMap );
		
		if( postGrades ) {
			String sourcedId = null;
			CourseMembership cm = null;
			String cmBatchUid = null;
			
			
			
			String gradingUrl = request.getServerName() + "/" + PlugInUtil.getUriStem("bbdn", "lti11") + "/ltigradeexchange";
			
			try {
				cm = CourseMembershipDbLoader.Default.getInstance().loadByCourseAndUserId(Id.generateId(Course.DATA_TYPE, courseId), ContextManagerFactory.getInstance().getContext().getUserId() );
				cmBatchUid = cm.getBatchUID();
			} catch (PersistenceException
					| UnsetContextException e1) {
				e1.printStackTrace();
			}
			
			try {
				sourcedId = SourcedIdGen.generate();
			} catch (SourcedIdGenException e) {
				
				sourcedId = "bbdn" + cmBatchUid + "id" + contentId;
			}
			
			GradeExchange grade = new GradeExchange();
			grade.setContentId(Integer.parseInt(contentId));
			grade.setCourseMembershipId(Integer.parseInt(cm.getId().toExternalString()));
			grade.setGradableItemId(getGradableItemId(contentId));
			grade.setSourcedId(sourcedId);
			
			_gradeDao.save(grade);
			
			launcher.addGradingInformation(gradingUrl, sourcedId);
			
			
		}
		   
		//Launch BLTI connection 
		//launch(HttpServletRequest request, HttpServletResponse response, boolean showSplashScreen, FormattedText splashMessage)
	 	launcher.launch( request, response, displaySplash, splashMsg );
	}
	
	private void createGradeableItem(String courseId, String contentId, String title, double points, boolean avlInBook, boolean avlToStudents) {
		GradableItem gi = new GradableItem();
		GradableItemManager giManager = GradebookManagerFactory.getGradableItemManager();
		
		try {
			gi.setCourseId(Id.generateId(Course.DATA_TYPE,courseId));
			gi.setCourseContentId(Id.generateId(Content.DATA_TYPE, contentId));
			gi.setDisplayTitle(title);
			gi.setPoints(points);
			gi.setTitle(title);
			gi.setVisibleInBook(avlInBook);
			gi.setVisibleToStudents(avlToStudents);
			  
			giManager.persistGradebookItem(gi);
		} catch(PersistenceException pe) {
			
		} catch(BbSecurityException bse) {
			
		} catch(Exception e) {
			
		}
	}
	
	private int getGradableItemId(String contentId) {
		GradableItemManager giManager = GradebookManagerFactory.getGradableItemManager();
		
		try {
			GradableItem gi =giManager.getGradebookItemByContentId(Id.generateId(Content.DATA_TYPE, contentId));
			return Integer.parseInt(gi.getId().toExternalString());
		} catch (PersistenceRuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BbSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
