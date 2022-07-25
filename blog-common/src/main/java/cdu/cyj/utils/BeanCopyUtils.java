package cdu.cyj.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BeanCopyUtils {

    public static <V> V copyBean(Object source, Class<V> vClass) {

        // 创建目标对象
        V result = null;
        try {
            result = vClass.newInstance();
            BeanUtils.copyProperties(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static <V> List<V> copyBeanList(List<?> list, Class<V> vClass) {
        // Steam流进行映射
        return list.stream()
                .map(o -> copyBean(o, vClass))
                .collect(Collectors.toList());
    }


}
