package common.util;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by zhaoxuliang on 16/1/14.
 */
public class BeanUtil {

    /**
     * 通过JavaBean的set方法对属性赋值
     *
     * @param object
     * @param fieldValues
     */
    public static void setDeclaredFieldValue(Object object, Map<String, Object> fieldValues) {
        if (object == null) {
            return;
        }
        if (fieldValues == null || fieldValues.isEmpty()) {
            return;
        }
        Class c = object.getClass();
        Method[] methods = c.getDeclaredMethods();
        if (methods == null || methods.length == 0) {
            return;
        }
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            if (!fieldValues.containsKey(field.getName())) {
                continue;
            }
            String setMethodName = "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
            Method method;
            try {
                method = c.getMethod(setMethodName, field.getType());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                continue;
            }
            try {
                method.invoke(object, fieldValues.get(field.getName()));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                continue;
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    /**
     * 深拷贝对象
     * 被拷贝对象需继承Serializable接口
     * @param obj
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T clone(T obj){
        T cloneObj = null;
        try {
            //写入字节流
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream obs = new ObjectOutputStream(out);
            obs.writeObject(obj);
            obs.close();

            //分配内存，写入原始对象，生成新对象
            ByteArrayInputStream ios = new ByteArrayInputStream(out.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(ios);
            //返回生成的新对象
            cloneObj = (T) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }

}
