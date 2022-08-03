package cdu.cyj.constants;

public class SystemConstants {

    // 查询热门文章个数
    public static final Integer HOT_ARTICLE_COUNT = 2;

    // 文章正常状态代码
    public static final Integer ARTICLE_NORMAL_STATUS = 1;

    // 分类正常状态代码
    public static final Integer CATEGORY_NORMAL_STATUS = 0;

    // 审核通过状态代码
    public static final Integer LINK_APPROVED_STATUS = 0;

    // 评论正常状态代码
    public static final Integer COMMENT_NORMAL_STATUS = 0;

    // 文章评论类型代码
    public static final Integer ARTICLE_COMMENT = 0;

    // 友链评论类型代码
    public static final Integer LINK_COMMENT = 1;


    // redis 文章访问量KEY
    public static final String ARTICLE_VIEWCOUNT_KEY = "article:viewCount";

}
