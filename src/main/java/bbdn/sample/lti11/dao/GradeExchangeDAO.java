/**
 * 
 */
package bbdn.sample.lti11.dao;

/**
 * @author shurrey
 *
 */

import java.util.List;

import bbdn.sample.lti11.model.GradeExchange;
import blackboard.persist.KeyNotFoundException;
import blackboard.persist.dao.impl.SimpleDAO;
import blackboard.persist.impl.SimpleSelectQuery;
import blackboard.util.StringUtil;

public class GradeExchangeDAO extends SimpleDAO<GradeExchange> {

	public GradeExchangeDAO() {
		super(GradeExchange.class);
	}

	public GradeExchangeDAO(Class<GradeExchange> cls) {
		super(cls);
	}

	public List<GradeExchange> loadAll() {
		return getDAOSupport().loadAll();
	}

	
    public GradeExchange load() {
        List<GradeExchange> props;
        props = getDAOSupport().loadAll();
        if(props!=null&&!props.isEmpty())
        return props.get(0);
        else return null;
    } 
    
    public List<GradeExchange> searchBySourcedId(String sourcedId) 
			throws KeyNotFoundException {
		if (!StringUtil.isEmpty(sourcedId)) {
			SimpleSelectQuery query = new SimpleSelectQuery(getDAOSupport().getMap());
			//Change column name to whatever the name is in the bean
			query.addWhere("sourcedid", sourcedId);
			return getDAOSupport().loadList(query);
		}
		return null;  
	}
    
    public List<GradeExchange> searchByContentId(String contentId) 
			throws KeyNotFoundException {
		if (!StringUtil.isEmpty(contentId)) {
			SimpleSelectQuery query = new SimpleSelectQuery(getDAOSupport().getMap());
			//Change column name to whatever the name is in the bean
			query.addWhere("course_content_pk1", contentId);
			return getDAOSupport().loadList(query);
		}
		return null;  
	}
    
    public List<GradeExchange> searchByCourseMembershipId(String courseMembershipId) 
			throws KeyNotFoundException {
		if (!StringUtil.isEmpty(courseMembershipId)) {
			SimpleSelectQuery query = new SimpleSelectQuery(getDAOSupport().getMap());
			//Change column name to whatever the name is in the bean
			query.addWhere("course_users_pk1", courseMembershipId);
			return getDAOSupport().loadList(query);
		}
		return null;  
	}
    
    public List<GradeExchange> searchByGradableItemId(String gradableItemId) 
			throws KeyNotFoundException {
		if (!StringUtil.isEmpty(gradableItemId)) {
			SimpleSelectQuery query = new SimpleSelectQuery(getDAOSupport().getMap());
			//Change column name to whatever the name is in the bean
			query.addWhere("gradebook_main_pk1", gradableItemId);
			return getDAOSupport().loadList(query);
		}
		return null;  
	}
    
    public void save(GradeExchange gradebook) {
        System.out.println(gradebook);        
                
        getDAOSupport().persist(gradebook);
    }

}
