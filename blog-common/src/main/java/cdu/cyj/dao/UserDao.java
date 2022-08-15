package cdu.cyj.dao;

import cdu.cyj.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2022-07-28 14:51:51
 */
@Mapper
public interface UserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param user 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<User> queryAllByLimit(User user, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param user 查询条件
     * @return 总行数
     */
    long count(User user);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int insert(User user);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<User> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<User> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<User> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<User> entities);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int update(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 通过用户名查询
     * @param userName 用户名
     * @return User对象
     */
    User queryByUserName(String userName);

    /**
     * 判断用户名是否存在
     *
     * @param userName 用户名
     * @return 1：存在 null: 不存在
     */
    Integer userNameExist(String userName);

    /**
     * 昵称是否存在
     *
     * @param nickName 昵称
     * @return 1：存在 null: 不存在
     */
    Integer nickNameExist(String nickName);

    /**
     * 邮箱是否已经存在
     *
     * @param email 邮箱
     * @return 1：存在 null: 不存在
     */
    Integer emailExist(String email);

    List<User> queryByObject(User user);

    Integer insertUserRoleBatch(@Param("uid") Integer uid, @Param("roleIds") List<Integer> roleIds);

    /**
     * 删除对应用户id的角色数据
     *
     * @return 受影响行数
     */
    int deleteUserRoleByUserId(Integer uid);

    /**
     * 通过用户id查询对应的roleIds
     *
     * @param id 用户id
     * @return roleIds
     */
    List<Integer> queryRoleIdsByUserId(Integer id);

    /**
     * 通过id批量删除
     *
     * @param userIds id列表
     * @return 受影响行数
     */
    int deleteByIdBatch(@Param("ids") List<Integer> userIds);

    /**
     * 批量删除用户角色中间表
     *
     * @param userIds 用户id列表
     * @return 受影响行数
     */
    int deleteUserRoleByUserIdBatch(@Param("ids") List<Integer> userIds);
}

