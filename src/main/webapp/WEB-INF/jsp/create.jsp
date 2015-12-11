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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<bbNG:genericPage
        title="Create LTI Link"
        ctxId="ctx"
        entitlement='system.admin.VIEW'
        navItem="content">

        <bbNG:pageHeader 
        	instructions="Configure the LTI Tool Provider for use as a content item.">
        	
        	<bbNG:breadcrumbBar environment="CTRL_PANEL" navItem="content" isContent="true" />
            
            <bbNG:pageTitleBar>
                    Create LTI Link
            </bbNG:pageTitleBar>
            
        </bbNG:pageHeader>

       	<bbNG:form action ="saveltilink" method="POST" isSecure="${ true }" nonceId="/saveltilink">
       	<input name="course_id" type="hidden" value="${course_id}" />
       	<input name="content_id" type="hidden" value="${content_id}" />
       	<input name="post_grades" type="hidden" value="${postGrades}" />
       	<input name="link_config" type="hidden" value="${linkConfigAllowed}"/>
        
        	<bbNG:dataCollection>
        		
        		<bbNG:step title="Content Information" >
        	
        			<bbNG:dataElement label="Name" renderLegendAndFieldset="true">
        				<bbNG:textElement name="name" id="name" title="Name" maxLength="255" minLength="1" value="${name}" />
        			</bbNG:dataElement>
        			
        			<bbNG:dataElement label="Text" renderLegendAndFieldset="true">
        				<bbNG:textbox name="text" ftext="${text}" />
        			</bbNG:dataElement>
        			
        		</bbNG:step>
        		
        		<bbNG:step title="Standard Options">
        		
        			<bbNG:dataElement label="Permit Users to View This Content" renderLegendAndFieldset="true">
			        	<bbNG:radioElement name="availability" id="availableYes" optionLabel="Yes" value="true" isSelected="${availability}" />
			        	<bbNG:radioElement name="availability" id="availableNo" optionLabel="No" value="false" isSelected="${!availability}" />
			       	</bbNG:dataElement>
			    
        			<bbNG:dataElement label="Track Number of Views" renderLegendAndFieldset="true">
			        	<bbNG:radioElement name="tracking" id="trackingYes" optionLabel="Yes" value="true" isSelected="${tracking}" />
			        	<bbNG:radioElement name="tracking" id="trackingNo" optionLabel="No" value="false" isSelected="${!tracking}" />
			       	</bbNG:dataElement>
			       	
			       	<bbNG:dataElement label="Select Date and Time Restrictions" renderLegendAndFieldset="true">
			       		<bbNG:dateRangePicker baseFieldName="dateTimeRestrictions" />
			       	</bbNG:dataElement>
			        		    			
        		</bbNG:step>
        		<% /*<c:choose> 
        				<c:when test="${postGrades == 'true'}">
			        		<bbNG:step title="Grading">
	       							<!-- <bbGrading:gradingOptions showGradingEnabledToggle="true" showPointsPossible="true" showVisibleToStudents="true" showDueDate="true" item="${gradableItem}"/>  -->
			        		</bbNG:step>
			        	</c:when>
			        	<c:otherwise>
			        	</c:otherwise>
			        </c:choose> */  %>
        		<c:choose> 
        				<c:when test="${linkConfigAllowed == 'true'}">
			        		<bbNG:step title="LTI Options">
			
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
			        						
			        			<bbNG:dataElement label="Show user acknowledgement message" renderLegendAndFieldset="true">
			        				<bbNG:radioElement name="displaySplash" id="displaySplashYes" optionLabel="Yes" value="true" isSelected="${displaySplash}" />
			        				<bbNG:radioElement name="displaySplash" id="displaySplashNo" optionLabel="No" value="false" isSelected="${!displaySplash}" />
			        			</bbNG:dataElement>
			        			
			        			<bbNG:dataElement label="Message Text" renderLegendAndFieldset="true">
			        				<bbNG:textbox name="splashMsg" ftext="${splashMsg}" />
			        			</bbNG:dataElement>
			        			
			        		</bbNG:step>
			        	</c:when>
			        	<c:otherwise>
			        	</c:otherwise>
			        </c:choose>
			      
        		<bbNG:stepSubmit />
        	
        	</bbNG:dataCollection>
        </bbNG:form>
        
</bbNG:genericPage>