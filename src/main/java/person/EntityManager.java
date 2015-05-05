package person;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class EntityManager {
    private static EntityManager eManager;
    private static List<PersonBean> entities = new ArrayList<PersonBean>();

    public static List<PersonBean> getEntities() {
        return entities;
    }

    public static void addEntity(PersonBean person) {
        entities.add(person);
    }

    private EntityManager() {
        PersonBean personBean = new PersonBean();
        personBean.setId(1);
        personBean.setName("Ivan");
        personBean.setSurname("Ivanov");
        personBean.setPatronymic("Ivanovich");
        personBean.setDob(Calendar.getInstance().getTime());
        entities.add(personBean);
        personBean = new PersonBean();
        personBean.setId(2);
        personBean.setName("Petr");
        personBean.setSurname("Petrov");
        personBean.setPatronymic("Ivanovich");
        personBean.setDob(Calendar.getInstance().getTime());
        entities.add(personBean);
        personBean = new PersonBean();
        personBean.setId(3);
        personBean.setName("Oleg");
        personBean.setSurname("Ivanov");
        personBean.setPatronymic("Olegovich");
        personBean.setDob(Calendar.getInstance().getTime());
        entities.add(personBean);
        personBean = new PersonBean();
        personBean.setId(4);
        personBean.setName("Alex");
        personBean.setSurname("Fox");
        personBean.setPatronymic("Ivanovich");
        personBean.setDob(Calendar.getInstance().getTime());
        entities.add(personBean);
        personBean = new PersonBean();
        personBean.setId(5);
        personBean.setName("Max");
        personBean.setSurname("Ivanov");
        personBean.setPatronymic("Olegovich");
        personBean.setDob(Calendar.getInstance().getTime());
        entities.add(personBean);
    }

    public static int removeEntity(int id) {
        int result = 500;
        Iterator<PersonBean> iter = entities.iterator();
        while (iter.hasNext()) {
            PersonBean pb = iter.next();
            if (pb.getId() == id) {
                iter.remove();
                result = 200;
                break;
            }
        }
        return result;
    }

    public static EntityManager getinstance() {
        if (eManager == null) {
            eManager = new EntityManager();
        }
        return eManager;
    }
}
