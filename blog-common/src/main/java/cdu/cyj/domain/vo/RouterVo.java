package cdu.cyj.domain.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RouterVo {

    @JSONField(serialize = false)
    private Integer id;
    /**
     * 菜单名
     */
    private String name;

    private String path;

    private String component;

    private RouterMetaVo meta;

    private List<RouterVo> children;

    @JSONField(serialize = false)
    private String title;

    /**
     * 图标
     */
    @JSONField(serialize = false)
    private String icon;

}
