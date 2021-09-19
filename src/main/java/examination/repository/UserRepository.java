package examination.repository;

import examination.entity.User;
import org.hibernate.query.Query;

import java.util.List;

public class UserRepository extends BaseRepository{
    public void createUser(User user){
        updateEntity(session -> session.save(user));
    }
    public User getUser(String userName, String password){
        List<User> list = getEntity(session -> {
            Query query = session.createQuery("from User where username=:username and password=:password", User.class).setMaxResults(1);
            query.setParameter("username", userName);
            query.setParameter("password", password);
            return query.list();
        });

        return list.stream().findFirst().orElse(null);
    }
    public boolean exists(String userName){
        List list = getEntity(session -> {
            Query query = session.createQuery("from User where username=:username", User.class).setMaxResults(1);
            query.setParameter("username", userName);
            return query.list();
        });

        return list.size() > 0;
    }
}
