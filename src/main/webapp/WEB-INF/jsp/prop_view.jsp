<%@ page language="java" contentType="text/html; charset=US-ASCII"
        pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="java.util.HashMap,
				 javax.servlet.http.HttpServletRequest, 
				 blackboard.servlet.tags.ngui.datacollection.DataElementTag,
				 blackboard.base.FormattedText,
				 blackboard.platform.plugin.PlugInUtil,
				 bbdn.sample.lti11.util.BBDNConstants,
				 bbdn.sample.lti11.util.SourcedIdGen,
				 bbdn.sample.lti11.util.SourcedIdGenException" 
%>

<%@ taglib uri="/bbNG" prefix="bbNG"%>

<bbNG:genericPage
        title="LTI Tool Registration Settings"
        ctxId="ctx"
        entitlement='system.admin.VIEW'
        navItem="bbdn-ltitools-nav-4">

        <bbNG:pageHeader 
        	instructions="Configure the LTI Tool Provider for use in the system. Determine the feature and service availability, and default configuration.">
        	
        	<bbNG:breadcrumbBar environment="SYS_ADMIN" />
            
            <bbNG:pageTitleBar>
                    LTI Domain Registration
            </bbNG:pageTitleBar>
            
        </bbNG:pageHeader>

       	<bbNG:form action ="configSave" method="POST" isSecure="${ true }" nonceId="/configSave">
        
        	<bbNG:dataCollection>
        		
        		<bbNG:step title="Tool Provider Registration Information" instructions="Set the connection information for the LTI Tool Provider.">
        	
        			<bbNG:dataElement label="LTI Launch URL" renderLegendAndFieldset="true">
        				<bbNG:textElement name="url" id="url" title="URL" maxLength="255" minLength="1" value="${url}" />
        			</bbNG:dataElement>
        			
        			<bbNG:dataElement label="Consumer Key" renderLegendAndFieldset="true">
        				<bbNG:textElement name="key" id="key" title="Key" maxLength="255" minLength="1" value="${key}" />
        			</bbNG:dataElement>
        			
        			<bbNG:dataElement label="Shared Secret" renderLegendAndFieldset="true">
        				<bbNG:textElement name="secret" id="secret" title="secret" maxLength="255" minLength="1" value="${secret}" />
        			</bbNG:dataElement>
        			
        			<bbNG:dataElement label="Tool Provider Custom Parameters" labelFor="customParameters">
		          		<textarea name="customParams" id="customParams" rows="6" cols="55">${customParams}</textarea>
		          		<bbNG:elementInstructions text="Enter any custom parameters required by the tool provider. Parameters must each be on their own line and be entered in \"name=value\" format."/>
		        	</bbNG:dataElement>
        			
        			<bbNG:dataElement label="Allow Link-Specific Configuration" renderLegendAndFieldset="true">
        				<bbNG:radioElement name="linkConfigAllowed" id="linkConfigAllowedYes" optionLabel="Yes" value="true" isSelected="${linkConfigAllowed}" />
        				<bbNG:radioElement name="linkConfigAllowed" id="linkConfigAllowedNo" optionLabel="No" value="false" isSelected="${!linkConfigAllowed}" />
        			</bbNG:dataElement>
        			
        		</bbNG:step>
        		
        		<bbNG:step title="Default Configuration" instructions="LTI Tool Providers can request certain information from Learn with each click-through. Set the default information that will be sent from your institution. These settings can be overridden for each tool provider.">

        			<bbNG:dataElement label="Allow configured tool providers to post grades" renderLegendAndFieldset="true">
        				<bbNG:radioElement name="postGrades" id="postGradesYes" optionLabel="Yes" value="true" isSelected="${postGrades}" />
        				<bbNG:radioElement name="postGrades" id="postGradesNo" optionLabel="No" value="false" isSelected="${!postGrades}" />
        			</bbNG:dataElement>
        			
        			<bbNG:dataElement label="Id Type To Send" renderLegendAndFieldset="true">
        				<p><bbNG:radioElement name="idTypeToSend" id="idTypeToSendBatchUid" optionLabel="BatchUID" value="<%=BBDNConstants.BBDN_ID_TYPE_TO_SEND_BATCHUID%>" isSelected="${idTypeToSend[0]}" /></p>
        				<p><bbNG:radioElement name="idTypeToSend" id="idTypeToSendPk1" optionLabel="PK1" value="<%=BBDNConstants.BBDN_ID_TYPE_TO_SEND_PK1%>" isSelected="${idTypeToSend[1]}" /></p>
        				<p><bbNG:radioElement name="idTypeToSend" id="idTypeToSendUUID" optionLabel="UUID" value="<%=BBDNConstants.BBDN_ID_TYPE_TO_SEND_UUID%>" isSelected="${idTypeToSend[2]}" /></p>
        			</bbNG:dataElement>
        			
        			<bbNG:dataElement label="Send user data" renderLegendAndFieldset="true">
        				<p><bbNG:radioElement name="sendData" id="sendDataNever" optionLabel="Never" value="<%=BBDNConstants.BBDN_USER_DATA_NEVER%>" isSelected="${sendDataConfig[0]}" /></p>
        				<p><bbNG:radioElement name="sendData" id="sendDataSSL" optionLabel="Send user data only over SSL" value="<%=BBDNConstants.BBDN_USER_DATA_SSL%>" isSelected="${sendDataConfig[1]}" /></p>
        				<p><bbNG:radioElement name="sendData" id="sendDataAny" optionLabel="Send user data over any connection" value="<%=BBDNConstants.BBDN_USER_DATA_ANY%>" isSelected="${sendDataConfig[2]}" /></p>
        			</bbNG:dataElement>
        			
        			<bbNG:dataElement label="Send user name" renderLegendAndFieldset="true">
        				<bbNG:radioElement name="sendName" id="sendUserNameYes" optionLabel="Yes" value="true" isSelected="${sendName}" />
        				<bbNG:radioElement name="sendName" id="sendUserNameNo" optionLabel="No" value="false" isSelected="${!sendName}" />
        			</bbNG:dataElement>
        			
        			<bbNG:dataElement label="Send user role" renderLegendAndFieldset="true">
        				<bbNG:radioElement name="sendRole" id="sendUserRoleYes" optionLabel="Yes" value="true" isSelected="${sendRole}" />
        				<bbNG:radioElement name="sendRole" id="sendUserRoleNo" optionLabel="No" value="false" isSelected="${!sendRole}" />
        			</bbNG:dataElement>
        			
        			<bbNG:dataElement label="Send user email" renderLegendAndFieldset="true">
        				<bbNG:radioElement name="sendEmail" id="sendUserEmailYes" optionLabel="Yes" value="true" isSelected="${sendEmail}" />
        				<bbNG:radioElement name="sendEmail" id="sendUserEmailNo" optionLabel="No" value="false" isSelected="${!sendEmail}" />
        			</bbNG:dataElement>
        			
        			<bbNG:dataElement label="Show user acknowledgement message" renderLegendAndFieldset="true">
        				<bbNG:radioElement name="displaySplash" id="displaySplashYes" optionLabel="Yes" value="true" isSelected="${displaySplash}" />
        				<bbNG:radioElement name="displaySplash" id="displaySplashNo" optionLabel="No" value="false" isSelected="${!displaySplash}" />
        			</bbNG:dataElement>
        			
        			<bbNG:dataElement label="Message Text" renderLegendAndFieldset="true">
        				<bbNG:textbox name="splashMsg" ftext="${splashMsg}" />
        			</bbNG:dataElement>
        			
        		</bbNG:step>
        		
        		<bbNG:stepSubmit />
        	
        	</bbNG:dataCollection>
        </bbNG:form>
        
</bbNG:genericPage>