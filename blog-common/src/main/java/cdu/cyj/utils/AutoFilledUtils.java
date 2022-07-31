package cdu.cyj.utils;

import cdu.cyj.domain.entity.Comment;

import java.lang.reflect.Method;
import java.util.Date;

public class AutoFilledUtils {

    public static <T> T autoFillOnInsert(T t) {

        Class<?> tClass = t.getClass();

        try {
            // 填充createTime
            Method setCreateTime = tClass.getMethod("setCreateTime", Date.class);
            setCreateTime.invoke(t, new Date());
            // 填充createBy
            Method setCreateBy = tClass.getMethod("setCreateBy", Integer.class);
            setCreateBy.invoke(t, SecurityUtils.getUserId());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return t;

    }

    public static <T> T autoFillOnUpdate(T t) {

        Class<?> tClass = t.getClass();

        try {
            // 填充updateTime
            Method setCreateTime = tClass.getMethod("setUpdateTime", Date.class);
            setCreateTime.invoke(t, new Date());
            // 填充updateBy
            Method setCreateBy = tClass.getMethod("setUpdateBy", Integer.class);
            setCreateBy.invoke(t, SecurityUtils.getUserId());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return t;

    }

    public static void main(String[] args) {
        Comment comment = new Comment();
        autoFillOnInsert(comment);
        System.out.println(comment);
    }



}
