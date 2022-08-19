package cdu.cyj.constants;

public class SystemConstants {

    /**
     * article
     */
    // 查询热门文章个数
    public static final Integer HOT_ARTICLE_COUNT = 2;

    // 文章正常状态代码
    public static final Integer ARTICLE_NORMAL_STATUS = 0;

    // 文章未发布状态代码
    public static final Integer ARTICLE_DRAFT_STATUS = 1;

    // 文章删除状态代码
    public static final Integer ARTICLE_DELETE_STATUS = -1;


    /**
     * category
     */
    // 分类正常状态代码
    public static final Integer CATEGORY_NORMAL_STATUS = 0;

    // 分类禁用状态代码
    public static final Integer CATEGORY_DISABLED_STATUS = 1;

    // 分类删除状态代码
    public static final Integer CATEGORY_DELETE_STATUS = -1;


    /**
     * link
     */
    // 审核通过状态代码
    public static final Integer LINK_APPROVED_STATUS = 0;


    /**
     * comment
     */
    // 评论正常状态代码
    public static final Integer COMMENT_NORMAL_STATUS = 0;

    // 文章评论类型代码
    public static final Integer ARTICLE_COMMENT = 0;

    // 友链评论类型代码
    public static final Integer LINK_COMMENT = 1;

    /**
     * user
     */
    // 用户正常状态
    public static final Integer USER_NORMAL_STATUS = 0;

    // 用户禁用状态
    public static final Integer USER_DISABLE_STATUS = 1;


    /**
     * redis
     */
    // redis 文章访问量KEY
    public static final String ARTICLE_VIEWCOUNT_KEY = "article:viewCount";

}
