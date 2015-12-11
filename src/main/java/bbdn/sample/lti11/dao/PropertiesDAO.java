package bbdn.sample.lti11.dao;

/**
 * @author shurrey
 *
 */

import java.util.List;

import bbdn.sample.lti11.model.Properties;
import blackboard.persist.dao.impl.SimpleDAO;

public class PropertiesDAO extends SimpleDAO<Properties> {

	public PropertiesDAO() {
		super(Properties.class);
	}

	public PropertiesDAO(Class<Properties> cls) {
		super(cls);
	}

	public List<Properties> loadAll() {
		return getDAOSupport().loadAll();
	}

	
    public Properties load() {
        List<Properties> props;
        props = getDAOSupport().loadAll();
        if(props!=null&&!props.isEmpty())
        return props.get(0);
        else return null;
    } 
    
    public void save(Properties props) {
        System.out.println(props);        
                
        getDAOSupport().persist(props);
    }

}
