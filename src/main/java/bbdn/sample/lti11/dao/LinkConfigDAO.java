/**
 * 
 */
package bbdn.sample.lti11.dao;

/**
 * @author shurrey
 *
 */

import java.util.List;

import bbdn.sample.lti11.model.LinkConfig;
import blackboard.persist.KeyNotFoundException;
import blackboard.persist.dao.impl.SimpleDAO;
import blackboard.persist.impl.SimpleSelectQuery;
import blackboard.util.StringUtil;

public class LinkConfigDAO extends SimpleDAO<LinkConfig> {

	public LinkConfigDAO() {
		super(LinkConfig.class);
	}

	public LinkConfigDAO(Class<LinkConfig> cls) {
		super(cls);
	}

	public List<LinkConfig> loadAll() {
		return getDAOSupport().loadAll();
	}

	public List<LinkConfig> searchByContentId(String contentId) 
			throws KeyNotFoundException {
		if (!StringUtil.isEmpty(contentId)) {
			SimpleSelectQuery query = new SimpleSelectQuery(getDAOSupport().getMap());
			//Change column name to whatever the name is in the bean
			query.addWhere("course_content_pk", contentId);
			return getDAOSupport().loadList(query);
		}
		return null;  
	}
    
    public LinkConfig load() {
        List<LinkConfig> linkConfig;
        linkConfig = getDAOSupport().loadAll();
        if(linkConfig!=null&&!linkConfig.isEmpty())
        return linkConfig.get(0);
        else return null;
    } 
    
    public void save(LinkConfig linkConfig) {
        System.out.println(linkConfig);        
                
        getDAOSupport().persist(linkConfig);
    }

}
