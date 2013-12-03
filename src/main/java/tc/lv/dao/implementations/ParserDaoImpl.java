package tc.lv.dao.implementations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import tc.lv.dao.DaoAbstract;
import tc.lv.dao.ParserDao;
import tc.lv.domain.Parser;

@Repository
public class ParserDaoImpl extends DaoAbstract implements ParserDao{
    
    @PersistenceContext(name = PERSISTENCE_UNIT_NAME)
    private EntityManager entityManager;
    
    @Override
    public List<Parser> findAll(int from, int count){
        Query query = entityManager.createNamedQuery(Parser.FIND_ALL);
        return findRange(from, count, query);
    }
    
    @Override
    public String findUrlByName(String name){
        Query query = entityManager.createNamedQuery(Parser.FIND_URL_BY_NAME);
        return (String) find(query);
    }
}
