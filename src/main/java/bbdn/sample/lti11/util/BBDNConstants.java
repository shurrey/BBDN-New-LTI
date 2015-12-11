/*
 * Copyright (c) 2013 IMS GLobal Learning Consortium
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 * 
 */
package bbdn.sample.lti11.util;

public class BBDNConstants {
	/**
	 * BBDN_ENABLED=Y
	 * <p>
	 * Used to populate index fields when they are enabled
	 */
	public static final String BBDN_ENABLED = "Y";
	
	/**
	 * BBDN_DISABLED=N
	 * <p>
	 * Used to populate index fields when they are disabled
	 */
	public static final String BBDN_DISABLED = "N";
	
	/**
	 * BBDN_ID_TYPE_TO_SEND_BATCHUID=0
	 * <p>
	 * Use Batch UID for user identification.
	 */
	public static final String BBDN_ID_TYPE_TO_SEND_BATCHUID = "0";
	
	/**
	 * BBDN_ID_TYPE_TO_SEND_PK1=1
	 * <p>
	 * Use PK1 for user identification
	 */
	public static final String BBDN_ID_TYPE_TO_SEND_PK1 = "1";
	
	/**
	 * BBDN_ID_TYPE_TO_SEND_UUID=2
	 * <p>
	 * Use UUID for user identification
	 */
	public static final String BBDN_ID_TYPE_TO_SEND_UUID = "2";
		
	
	/**
	 * BBDN_USER_DATA_NEVER=0
	 * <p>
	 * Never send user data
	 */
	public static final String BBDN_USER_DATA_NEVER = "0";
	
	/**
	 * BBDN_USER_DATA_SSL=1
	 * <p>
	 * Only send user data over SSL connections
	 */
	public static final String BBDN_USER_DATA_SSL = "1";
	
	/**
	 * BBDN_USER_DATA_ANY=2
	 * <p>
	 * Send User Data over any connection
	 */
	public static final String BBDN_USER_DATA_ANY = "2";

	/**
	 * BBDN_TOOL_PENDING=0
	 * <p>
	 * Registration requested from Learn, awaiting response from Tool Provider
	 */
	public static final String BBDN_TOOL_PENDING = "0";
	
	/**
	 * BBDN_TOOL_APPROVEDL=1
	 * <p>
	 * Tool Provider Registered and Approved in Learn
	 */
	public static final String BBDN_TOOL_APPROVED = "1";
	
	/**
	 * BBDN_TOOL_EXCLUDED=2
	 * <p>
	 * Tool Provider Registered and Excluded in Learn
	 */
	public static final String BBDN_TOOL_EXCLUDED = "2";
}