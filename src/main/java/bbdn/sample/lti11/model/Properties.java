package bbdn.sample.lti11.model;

import blackboard.data.AbstractIdentifiable;
import blackboard.persist.DataType;
import blackboard.persist.impl.mapping.annotation.Column;
import blackboard.persist.impl.mapping.annotation.PrimaryKey;
import blackboard.persist.impl.mapping.annotation.Table;

@Table("bbdn_lti11_props")
public class Properties  extends AbstractIdentifiable {

		   public static final DataType DATA_TYPE = new DataType( Properties.class );
		   
		   public DataType getDataType() {
				return DATA_TYPE;
		   }

		   @PrimaryKey
		   private int pk1;
		  
		   @Column(value = "props_url")
		   private String url;
		   
		   @Column(value = "props_postgrades_ind")
		   private String postGrades;
		 
		   @Column(value = "props_senddata")
		   private int sendData;
		   
		   @Column(value = "props_sendrole_ind")
		   private String sendRole;
		   
		   @Column(value = "props_sendname_ind")
		   private String sendName;
		   
		   @Column(value = "props_sendemail_ind")
		   private String sendEmail;

		   @Column(value = "props_global_ind")
		   private String linkSettings;
		   
		   @Column(value = "props_key")
		   private String key;
		   
		   @Column(value = "props_secret")
		   private String secret;
		   
		   @Column(value = "props_customparams")
		   private String customParams;
		   
		   @Column(value = "props_userack_ind")
		   private String displaySplash;

		   @Column(value = "props_ackmsg")
		   private String ackMsg;
		   
		   @Column(value = "props_idtypetosend")
		   private int idTypeToSend;


		   /**
			 * @return the props_id
			 */
			public int getPropertyId() {
				return pk1;
			}
			
		
			/**
			 * @return the props_ackmsg
			 */
			public String getUrl() {
				return url;
			}

			/**
			 * @param props_ackmsg the props_ackmsg to set
			 */
			public void setUrl(String url) {
				this.url = url;
			}
			
		/**
		 * @return the props_postgrades_ind
		 */
		public Boolean isPostGradesEnabled() {
			if ( this.postGrades.equals("Y") ) {
				return true;
			} else {
				return false;
			}
		}

		/**
		 * @param flag : If true, set props_postgrades_ind to 'Y', else 'N'
		 */
		public void enablePostGrades(Boolean flag) {
			if (flag) {
				this.postGrades = "Y";
			} else {
				this.postGrades = "N";
			}
			
		}

		/**
		 * @return the props_senddata
		 */
		public int getSendDataConfig() {
			return sendData;
		}

		/**
		 * @param props_senddata the props_senddata to set
		 */
		public void setSendDataConfig(int props_senddata) {
			this.sendData = props_senddata;
		}

		/**
		 * @return the props_sendrole_ind
		 */
		public Boolean isSendRoleEnabled() {
			if ( this.sendRole.equals("Y") ) {
				return true;
			} else {
				return false;
			}
		}

		/**
		 * @param flag : If true, set props_sendrole_ind to 'Y', else 'N'
		 */
		public void enableSendRole(Boolean flag) {
			if (flag) {
				this.sendRole = "Y";
			} else {
				this.sendRole = "N";
			}
			
		}

		/**
		 * @return the props_sendname_ind
		 */
		public Boolean isSendNameEnabled() {
			if ( this.sendName.equals("Y") ) {
				return true;
			} else {
				return false;
			}
		}

		/**
		 * @param flag : If true, set props_sendname_ind to 'Y', else 'N'
		 */
		public void enableSendName(Boolean flag) {
			if (flag) {
				this.sendName = "Y";
			} else {
				this.sendName = "N";
			}
			
		}

		/**
		 * @return the props_sendemail_ind
		 */
		public Boolean isSendEMailEnabled() {
			if ( this.sendEmail.equals("Y") ) {
				return true;
			} else {
				return false;
			}
		}
		
		/**
		 * @param flag : If true, set props_sendemail_ind to 'Y', else 'N'
		 */
		public void enableSendEMail(Boolean flag) {
			if (flag) {
				this.sendEmail = "Y";
			} else {
				this.sendEmail = "N";
			}
			
		}

		/**
		 * @param flag : If true, set props_sendemail_ind to 'Y', else 'N'
		 */
		public void allowLinkSettings(Boolean flag) {
			if (flag) {
				this.linkSettings = "Y";
			} else {
				this.linkSettings = "N";
			}
			
		}

		/**
		 * @return the props_sendemail_ind
		 */
		public Boolean isLinkConfigAllowed() {
			if ( this.linkSettings.equals("Y") ) {
				return true;
			} else {
				return false;
			}
		}

		/**
		 * @return the key
		 */
		public String getKey() {
			return key;
		}

		/**
		 * @param set the key
		 */
		public void setKey(String key) {
			this.key = key;
		}
		
		/**
		 * @return the props_ackmsg
		 */
	
		public String getSecret() {
			return secret;
		}

		/**
		 * @param props_ackmsg the props_ackmsg to set
		 */
		public void setSecret(String secret) {
			this.secret = secret;
		}
	
		/**
		 * @return the props_ackmsg
		 */
		public String getCustomParams() {
			return customParams;
		}

		/**
		 * @param props_ackmsg the props_ackmsg to set
		 */
		public void setCustomParams(String customparams) {
			this.customParams = customparams;
		}
	
		/**
		 * @return the props_userack_ind
		 */

		public Boolean isSplashScreenEnabled() {
			if ( this.displaySplash.equals("Y") ) {
				return true;
			} else {
				return false;
			}
		}

		/**
		 * @param flag : If true, set props_userack_ind to 'Y', else 'N'
		 */
		public void enableSplashScreen(Boolean flag) {
			if (flag) {
				this.displaySplash = "Y";
			} else {
				this.displaySplash = "N";
			}
			
		}

		/**
		 * @return the props_ackmsg
		 */
		public String getSplashScreenMessage() {
			return ackMsg;
		}

		/**
		 * @param props_ackmsg the props_ackmsg to set
		 */
		public void setSplashScreenMessage(String props_ackmsg) {
			this.ackMsg = props_ackmsg;
		}
		 
		/**
		 * @return the props_idTypeToSend
		 */
		public int getIdTypeToSend() {
			return idTypeToSend;
		}

		/**
		 * @param props_idTypeToSend the props_idTypeToSend to set
		 */
		public void setIdTypeToSend(int props_idTypeToSend) {
			this.idTypeToSend = props_idTypeToSend;
		}
}
