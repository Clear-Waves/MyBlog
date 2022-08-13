package cdu.cyj.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserUpdateVo {

    private AdminUserDetailVo user;

    private List<AdminRoleOptionListVo> roles;

    private List<Integer> roleIds;

}
