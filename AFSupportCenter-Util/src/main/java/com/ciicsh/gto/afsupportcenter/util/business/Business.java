package com.ciicsh.gto.afsupportcenter.util.business;

import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
import com.ciicsh.gto.afsupportcenter.util.kit.ReflectKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 基础业务接口实现类，当前类简单的处理的通用数据
 */
public class Business<T, M extends BaseMapper<T>> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * spring 4 支持泛型注入
     */
    @Autowired
    protected M baseMapper;
    // 泛型 T class
    protected Class<T> entityClass;

    public Business() {
        // 获取泛型 T class
        entityClass = ReflectKit.getClassActualTypeArguments(getClass());
    }

    /**
     * 分页查询
     *
     * @param pageInfo
     * @return
     */
    public PageRows<T> finds(PageInfo pageInfo) {
        return PageKit.doSelectPage(pageInfo, () -> finds(pageInfo.toJavaObject(entityClass)));
    }

    /**
     * 查询数据列表
     *
     * @param entity
     * @return
     */
    public List<T> finds(T entity) {
        handleSelect(entity);
        return baseMapper.select(entity);
    }

    /**
     * 查询数据
     *
     * @param entity
     * @return
     */
    public T find(T entity) {
        handleSelect(entity);
        return baseMapper.selectOne(entity);
    }

    /**
     * 根据 id 查找
     *
     * @param id
     * @return
     */
    public T findById(Object id) {
        T entity = handleEntityPK(id);
        handleSelect(entity);
        return baseMapper.selectOne(entity);
    }

    /**
     * 保存
     *
     * @param entity
     */
    @Transactional
    public int save(T entity) {
        handleInsert(entity);
        return baseMapper.insertSelective(entity);
    }

    /**
     * 删除
     *
     * @param entity
     */
    @Transactional
    public int delete(T entity) {
        handleDelete(entity);
        return update(entity);
    }

    /**
     * 删除
     *
     * @param id （复合主键类型 Map 或当前 bean，非复合主键直接传值）
     */
    @Transactional
    public int deleteById(Object id) {
        T entity = handleEntityPK(id);
        return delete(entity);
    }

    /**
     * 更新
     *
     * @param entity
     */
    @Transactional
    public int update(T entity) {
        handleUpdate(entity);
        return baseMapper.updateByPrimaryKeySelective(entity);
    }

    /**
     * 保存或更新，根据需求使用
     *
     * @param entity
     * @return
     */
    @Transactional
    public int saveOrUpdate(T entity) {
        // 先根据 id 查询数据库，没有就保存
        if (findById(entity) == null) {
            return save(entity);
        } else {
            return update(entity);
        }
    }

    /**
     * 处理保存参数
     *
     * @param entity
     */
    protected void handleInsert(T entity) {
        /*
        if (entity instanceof BasicModel) {
            BasicModel basicModel = (BasicModel) entity;
            LocalDateTime now = LocalDateTime.now();
            basicModel.setDataChangeLastTime(now);
            basicModel.setDataChangeCreateTime(now);
        }*/
    }

    /**
     * 处理删除参数
     *
     * @param entity
     */
    protected void handleDelete(T entity) {
        /*if (entity instanceof BasicModel) {
            BasicModel basicModel = (BasicModel) entity;
            // 逻辑删除
            basicModel.setIsActive(false);
        }*/
    }

    /**
     * 处理更新参数
     *
     * @param entity
     */
    protected void handleUpdate(T entity) {
        /*if (entity instanceof BasicModel) {
            BasicModel basicModel = (BasicModel) entity;
            basicModel.setDataChangeLastTime(LocalDateTime.now());
        }*/
    }

    /**
     * 处理查询参数
     *
     * @param entity
     */
    protected void handleSelect(T entity) {
        /*if (entity instanceof BasicModel) {
            BasicModel basicModel = (BasicModel) entity;
            basicModel.setIsActive(true);// 只查询有效数据
        }*/
    }


    /**
     * 处理 entity 主键
     *
     * @param ids
     * @return
     */
    protected T handleEntityPK(Object ids) {
        T entity = ReflectKit.newInstance(entityClass);

        if (ids == null) {
            return entity;
        }

        Class<?> clazz = ids.getClass();

        List<EntityColumn> pks = new ArrayList<>(EntityHelper.getPKColumns(entityClass));

        if (pks.size() == 0) {// 没有主键
            logger.warn("当前 entity:" + entityClass.getName() + " 没有主键:" + clazz.getName());
            BusinessException.rethrow("当前 entity:" + entityClass.getName() + " 没有主键:" + clazz.getName());
        } else if (pks.size() == 1) {// 非复合主键
            handleEntityColumn(entity, pks.get(0), ids);
        } else if (Map.class.isAssignableFrom(clazz) || entityClass.isAssignableFrom(clazz)) {// 复合主键
            pks.forEach((pk) -> handleEntityColumn(entity, pk, ids));
        } else {
            logger.error("当前 entity:" + entityClass.getName() + " 复合主键不支持此类型:" + clazz.getName());
            BusinessException.rethrow("当前 entity:" + entityClass.getName() + " 复合主键不支持此类型:" + clazz.getName());
        }

        return entity;
    }

    /**
     * 处理 column
     *
     * @param entity
     * @param column
     * @param ids
     */
    void handleEntityColumn(T entity, EntityColumn column, Object ids) {
        String property = column.getProperty();
        Class<?> clazz = ids.getClass();
        ReflectionUtils.doWithFields(entityClass, (field) -> {
            if (property.equals(field.getName())) {
                Object value;
                ReflectionUtils.makeAccessible(field);
                if (Map.class.isAssignableFrom(clazz)) {
                    value = ((Map) ids).get(property);
                } else if (entityClass.isAssignableFrom(clazz)) {
                    value = ReflectionUtils.getField(field, ids);
                } else {
                    value = ids;
                }
                ReflectionUtils.setField(field, entity, value);
                return;
            }
        });
    }

}
