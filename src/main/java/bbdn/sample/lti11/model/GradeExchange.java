package bbdn.sample.lti11.model;

import blackboard.data.AbstractIdentifiable;
import blackboard.persist.DataType;
import blackboard.persist.impl.mapping.annotation.Column;
import blackboard.persist.impl.mapping.annotation.PrimaryKey;
import blackboard.persist.impl.mapping.annotation.Table;

@Table("bbdn_lti11_gradebook")
public class GradeExchange  extends AbstractIdentifiable {

		   public static final DataType DATA_TYPE = new DataType( GradeExchange.class );
		   
		   public DataType getDataType() {
				return DATA_TYPE;
		   }

		   @PrimaryKey
		   private int pk1;

		   @Column("sourcedid")
		   private String sourcedId;
		   
		   @Column("course_contents_pk1")
		   private int contentId;
		   
		   @Column("gradebook_main_pk1")
		   private int gradableItemId;
		   
		   @Column("course_users_pk1")
		   private int courseMembershipId;

		public int getGradebookId() {
			return pk1;
		}

		public String getSourcedId() {
			return sourcedId;
		}

		public void setSourcedId(String sourcedid) {
			this.sourcedId = sourcedid;
		}

		public int getContentId() {
			return contentId;
		}

		public void setContentId(int contentId) {
			this.contentId = contentId;
		}

		public int getGradableItemId() {
			return gradableItemId;
		}

		public void setGradableItemId(int gradableItemId) {
			this.gradableItemId = gradableItemId;
		}

		public int getCourseMembershipId() {
			return courseMembershipId;
		}

		public void setCourseMembershipId(int courseMembershipId) {
			this.courseMembershipId = courseMembershipId;
		}
}