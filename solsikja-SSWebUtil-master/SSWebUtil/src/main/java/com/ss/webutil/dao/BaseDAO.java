package com.ss.webutil.dao;

import com.ss.webutil.wechat.Config;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import sun.reflect.generics.reflectiveObjects.TypeVariableImpl;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by liymm on 2014-12-31.
 */
abstract public class BaseDAO<T> {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    public static final int ASC = 0;
    public static final int DESC = 1;

    public enum OrderType {
        OT_ASC,OT_DESC;

        public static final OrderType convert(int type) {
            if (type == DESC)
                return OT_DESC;
            else
                return OT_ASC;
        }
    }

    /**
     * 当前T的对象
     */
    protected Class<T> persistentClass;
    String baseHQL;


    /**
     * Autowired 自动装配 相当于get() set()
     */
    @Autowired
    protected SessionFactory sessionFactory;

    public BaseDAO() {
        persistentClass = getTrueType(getClass());
        genBaseHQL();
    }

    protected Class getTrueType(Class cls) {
        Type type = cls.getGenericSuperclass();
        Class trueCls = null;

        if (type instanceof ParameterizedType) {
            Type temp = ((ParameterizedType) type).getActualTypeArguments()[0];
            if (temp instanceof Class)
                trueCls = (Class) temp;
        }

        return trueCls;
    }

    protected void genBaseHQL() {
        if (persistentClass == null)
            return;

        Entity entity = persistentClass.getAnnotation(Entity.class);
        if (entity == null)
            return;

        logger.debug("------- BaseDAO<" + persistentClass.getSimpleName() + "> ---------");

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("from %s temp", persistentClass.getName()));
        getClassLeftOuterJoin(sb, "temp", persistentClass);
        sb.append(" where 1=1");
        baseHQL = sb.toString();

        logger.debug(baseHQL);
    }

    void getClassLeftOuterJoin(StringBuilder sb, String prefix, Class<?> cls) {
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            ManyToOne m2o = field.getAnnotation(ManyToOne.class);
            if (m2o == null)
                continue;

            if (m2o.fetch() == FetchType.LAZY)
                continue;

            Fetch fetch = field.getAnnotation(Fetch.class);
            if (fetch == null)
                continue;

            if (fetch.value() != FetchMode.JOIN)
                continue;

            String txt = String.format(" left outer join fetch %s.%s", prefix, field.getName());
            sb.append(txt);
            getClassLeftOuterJoin(sb, String.format("%s.%s", prefix, field.getName()), field.getType());
        }
    }


    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * openSession 需要手动关闭session 意思是打开一个新的session
     *
     * @return
     */
    public Session getNewSession() {
        return sessionFactory.openSession();
    }

    public void flush() {
        getSession().flush();
    }

    public void clear() {
        getSession().clear();
    }

//    public T check(Serializable pk) {
//        return (T)getSession().load(persistentClass, pk);
//    }

    /**
     * 根据 id 查询信息
     *
     * @param id
     * @return
     */
    @SuppressWarnings("rawtypes")
    public T load(Serializable id) {
        Session session = getSession();
        return (T) session.get(persistentClass, id);
    }

    /**
     * 保存
     *
     * @param bean
     */
    public Serializable save(T bean) {
        try {
            Session session = getSession();
            Serializable s = session.save(bean);
            session.flush();
            session.clear();
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Serializable lightSave(T bean) {
        try {
            return getSession().save(bean);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void flushAndClear() {
        flush();
        clear();
    }

    /**
     * 更新
     *
     * @param bean
     */
    public void update(T bean) {
        Session session = getSession();
        session.update(bean);
        session.flush();
        session.clear();
    }

    /**
     * 删除
     *
     * @param bean
     */
    public void delete(T bean) {
        Session session = getSession();
        session.delete(bean);
        session.flush();
        session.clear();
    }

    /**
     * 根据ID删除
     *
     * @param id ID
     */
    @SuppressWarnings({"rawtypes"})
    public void delete(Serializable id) {
        Session session = getSession();
        Object obj = session.get(persistentClass, id);
        session.delete(obj);
        session.flush();
        session.clear();
    }

    /**
     * 批量删除
     *
     * @param ids ID 集合
     */
    @SuppressWarnings({"rawtypes"})
    public void delete(Serializable[] ids) {
        for (Serializable id : ids) {
            Object obj = getSession().get(persistentClass, id);
            if (obj != null) {
                getSession().delete(obj);
            }
        }
    }

    protected String getOrderHQL(String property, OrderType orderType) {
        StringBuilder orderHQL = new StringBuilder();
        orderHQL.append(" order by temp.").append(property);
        if (orderType == OrderType.OT_DESC)
            orderHQL.append(" desc");
        else
            orderHQL.append(" asc");

        return orderHQL.toString();
    }

    protected final List<T> filter(String[] props, Object[] values, Integer offset, Integer length, String orderProp, OrderType orderType) {
        logger.debug("=====Query Start");
        StringBuilder hql = new StringBuilder();
        if (baseHQL == null)
            hql.append("from ").append(persistentClass.getName()).append(" temp where 1=1");
        else
            hql.append(baseHQL);

        if (props != null) {
            for (String prop : props) {
                hql.append(" and temp.").append(prop).append(" = ?");
            }
        }

        if (orderProp != null)
            hql.append(getOrderHQL(orderProp, orderType));

        logger.debug(hql.toString());

        Query q = getSession().createQuery(hql.toString());

        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                q.setParameter(i, values[i]);
            }
        }

        if (offset != null)
            q.setFirstResult(offset);

        if (length != null)
            q.setMaxResults(length);

        List result = q.list();

        logger.debug("=====Query Finished");

        return result;
    }


    /* 无查询条件 start */
    @Deprecated
    public List<T> getAllList() {
        return filter(null, null, null, null, null, null);
    }

    @Deprecated
    public List<T> getAllListByOffset(Integer offset, Integer length) {
        return filter(null, null, offset, length, null, null);
    }

    @Deprecated
    public List<T> getAllListWithOrder(String orderProp, Integer order) {
        return filter(null, null, null, null, orderProp, OrderType.convert(order));
    }

    @Deprecated
    public List<T> getAllListByOffsetWithOrder(Integer offset, Integer length, String orderProp, Integer order) {
        return filter(null, null, offset, length, orderProp, OrderType.convert(order));
    }
    /* 无查询条件 end */

    /* 单一查询条件 start */
    public List<T> findByProperty(String prop, Object value) {
        return filter(new String[]{prop}, new Object[]{value}, null, null, null, null);
    }

    @Deprecated
    public List<T> findByPropertyByOffset(String prop, Object value, Integer offset, Integer length) {
        return filter(new String[]{prop}, new Object[]{value}, offset, length, null, null);
    }

    @Deprecated
    public List<T> findByPropertyWithOrder(String prop, Object value, String orderProp, Integer order) {
        return filter(new String[]{prop}, new Object[]{value}, null, null, orderProp, OrderType.convert(order));
    }

    @Deprecated
    public List<T> findByPropertyByOffsetWithOrder(String prop, Object value, Integer offset, Integer length, String orderProp, Integer order) {
        return filter(new String[]{prop}, new Object[]{value}, offset, length, orderProp, OrderType.convert(order));
    }
    /* 单一查询条件 end */

    /* 多查询条件 start */
    @Deprecated
    public List<T> findByProperties(String[] props, Object[] values) {
        return filter(props, values, null, null, null, null);
    }

    @Deprecated
    public List<T> findByPropertiesWithOrder(String[] props, Object[] values, String orderProp, Integer order) {
        return filter(props, values, null, null, orderProp, OrderType.convert(order));
    }

    @Deprecated
    public List<T> findByPropertiesByOffset(String[] props, Object[] values, Integer offset, Integer length) {
        return filter(props, values, offset, length, null, null);
    }

    @Deprecated
    public List<T> findByPropertiesByOffsetWithOrder(String[] props, Object[] values, Integer offset, Integer length, String orderProp, Integer order) {
        return filter(props, values, offset, length, orderProp, OrderType.convert(order));
    }
    /* 多查询条件 end */

    /* 重载方法 start */
    public List<T> list() {
        return filter(null, null, null, null, null, null);
    }

    public List<T> list(Integer offset, Integer length) {
        return filter(null, null, offset, length, null, null);
    }

    public List<T> list(String orderProp, OrderType orderType) {
        return filter(null, null, null, null, orderProp, orderType);
    }

    public List<T> list(Integer offset, Integer length, String orderProp, OrderType orderType) {
        return filter(null, null, offset, length, orderProp, orderType);
    }

    public List<T> list(String[] props, Object[] values) {
        return filter(props, values, null, null, null, null);
    }

    public List<T> list(String[] props, Object[] values, Integer offset, Integer length) {
        return filter(props, values, offset, length, null, null);
    }

    public List<T> list(String[] props, Object[] values, String orderProp, OrderType orderType) {
        return filter(props, values, null, null, orderProp, orderType);
    }

    public List<T> list(String[] props, Object[] values, Integer offset, Integer length, String orderProp, OrderType orderType) {
        return filter(props, values, offset, length, orderProp, orderType);
    }
    /* 重载方法 end */

    /**
     * 获取总数量
     *
     * @return
     */
    public Long getTotalCount() {
        Session session = getSession();
        String hql = "select count(*) from " + persistentClass.getName();
        Long count = (Long) session.createQuery(hql).uniqueResult();
        return count != null ? count.longValue() : 0;
    }

    /**
     * 单一条件筛选，获取总数
     *
     * @param name
     * @param value
     * @return
     */
    public Long getTotalCountByProperty(String name, Object value) {
        Session session = getSession();
        String hql = "select count(*) from " + persistentClass.getName() + " temp where temp." + name + " = :val";
        Long count = (Long) session.createQuery(hql).setParameter("val", value).uniqueResult();
        return count != null ? count.longValue() : 0;
    }

    /**
     * 复合条件筛选，获取总数
     *
     * @param names
     * @param values
     * @return
     */
    public Long getTotalCountByProperties(String[] names, Object[] values) {
        Session session = getSession();
        String hql = "select count(*) from " + persistentClass.getName() + " temp where 1=1";
        for (String name : names) {
            hql += " and temp." + name + " = ?";
        }

        Query q = session.createQuery(hql);

        for (int i = 0; i < values.length; i++) {
            q.setParameter(i, values[i]);
        }

        Long count = (Long) q.uniqueResult();
        return count != null ? count.longValue() : 0;
    }


}
