package bbdn.sample.lti11.model;

import blackboard.data.AbstractIdentifiable;
import blackboard.persist.DataType;
import blackboard.persist.impl.mapping.annotation.Column;
import blackboard.persist.impl.mapping.annotation.PrimaryKey;
import blackboard.persist.impl.mapping.annotation.Table;

@Table("bbdn_lti11_linkcfg")
public class LinkConfig  extends AbstractIdentifiable {

		   public static final DataType DATA_TYPE = new DataType( LinkConfig.class );
		   
		   public DataType getDataType() {
				return DATA_TYPE;
		   }

		   @PrimaryKey
		   private int pk1;

		   @Column("course_contents_pk1")
		   private int contentId;
		   
		   @Column("custom_config")
		   private String config;

		public int getLinkCfgId() {
			return pk1;
		}

		public int getContentId() {
			return contentId;
		}

		public void setContentId(int contentId) {
			this.contentId = contentId;
		}

		public String getConfig() {
			return config;
		}

		public void setConfig(String config) {
			this.config = config;
		}
		   
		   
		   
		   
}