package com.yeoou.common.context;

/**
 * 
 * <p>
 * Title: Global.java
 * </p>
 * <p>
 * Description: Used to define some common variables.
 * </p>
 * @author 
 * @version 1.0
 * @created 2007-01-24
 */
public final class Global {
	

	public static final String APP_USER_LOGIN_INFO = "APP_USER_LOGIN_INFO";
	public static final String ACEGI_SESSION_REGISTRY = "sessionRegistry";
	
	/**
	 * Security
	 */
	public static final String ACEGI_CURRENTUSERINFOPROVIDER = "currentUserInfoProvider";
	
    /**
     * rbac Manage
     */
    public static final String SERVICE_RBAC_GROUPMANAGER = "rbacGroupManager";
    public static final String SERVICE_RBAC_USERMANAGER = "rbacUserManager";
    public static final String SERVICE_RBAC_ROLEMANAGER = "rbacRoleManager";
    public static final String SERVICE_RBAC_PERMISSIONMANAGER = "rbacPermissionManager";
    public static final String SERVICE_RBAC_RESOURCEMANAGER = "rbacResourceManager";
    public static final String SERVICE_RBAC_ROLEPERMISSIONMANAGER = "rbacRolePermissionManager";
    public static final String SERVICE_RBAC_MENUMANAGER = "rbacMenuManager";
    public static final String SERVICE_RBAC_USERGROUPROLEMANAGER = "rbacUserGroupRoleManager";
    public static final String SERVICE_RBAC_ACCESSLOGMANAGER = "rbacAccessLogManager";
    public static final String SERVICE_RBAC_RBACOBJECTMAXKEYMANAGER = "rbacObjectMaxKeyManager";

	public static final String RBAC_ACCESSENTRYCONTROLLER = "accessEntryController";
    
    public static final String rootGroupId = "000000";
    public static final int groupKeyLength = 6;
    public static final int groupStepLength = 2;
    
    
    /**
     * PSS Manage
     */
    public static final String SERVICE_JXC_PRODUCTMANAGER = "productManager";
    public static final String SERVICE_JXC_CUSTOMERMANAGER = "customerManager";
    
    /**
     * The session scope attribute under which the user message to be
     * displayed to the user is stored
     */
    public static final String MESSAGE_KEY = "userMessageKey";

    /**
     * The request scope attribute under the list of validation errors is stored
     */
    public static final String VALIDATION_ERRORS_KEY = "validationErrors";

    /**
     * The response header key to indicate that validation errors occurred. Used during Ajax updates.
     */
    public static final String VALIDATION_ERRORS_EXIST = "VALIDATION_ERRORS_EXIST";
    
    /**
     * The response header key to indicate whether update/create operation is successful. Used during Ajax updates.
     */
    public static final String PERSIST_SUCCESSFULLY = "PERSIST_SUCCESSFULLY";
    

    public static final String AJAX_REQUEST_TIMEOUT = "AJAX_REQUEST_TIMEOUT";
    public static final String AJAX_REQUEST_TIMEOUT_REDIRECT_URL = "AJAX_REQUEST_TIMEOUT_REDIRECT_URL";
    
    
    public static final String SYSTEM_MAILBOX = "xie_wansong@163.com";
	@SuppressWarnings("unused")
	//关于日期的常量
	public static String[] dayOfWeeks = {"每周日","每周一","每周二","每周三","每周四","每周五","每周六"};
	public static String[] otherDayOfWeeks = {"星期天","星期一","星期二","星期三","星期四","星期五","星期六"};
	public static String[] shortWeeks = {"[日]","[一]","[二]","[三]","[四]","[五]","[六]"};
	public static String[] beginMonths ={"01","02","03","04","05","06","07","08","09","10","11","12"};
	public static String[] endMonths ={"02","03","04","05","06","07","08","09","10","11","12","01"};
	public static String[] zhMonths={"一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"};
	public static int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	public static int[] months = {1,2,3,4,5,6,7,8,9,10,11,12};
	public static String[] firstDayOfMonth = {"-1-1","-2-1","-3-1","-4-1","-5-1","-6-1","-7-1","-8-1","-9-1","-10-1","-11-1","-12-1"};
	public static int NOTICE_TYPE_MARGIN = 0;
	public static int NOTICE_TYPE_POP=1;
	public static int NOTICE_TYPE_OTHER = 2;
	public static String[] themeArray = {"国庆","中秋"};
	public static int PAGESIZE = 30;
	public static int SCENERY_TYPE_RENWEN  = 1;//人文风光
	public static int SCENERY_TYPE_ZIRAN   = 0;//自然风景
    /**
     * 线路删除状态定义
     */

    public static final int LINE_JOBBER_DELETE = 1;
    public static final int LINE_KEEP = 0;
    /**
     * 出团删除状态定义
     */
    public static final boolean TEAM_JOBBER_DELETE = true;
    public static final boolean TEAM_KEEP = false;
    /**
     * 订单删除状态定义
     */
    public static final int ORDER_ALL_DELETE = 1;
    public static final int ORDER_JOBBER_DELETE = 2;
    public static final int ORDER_SHOPKEEPER_DELETE = 3;
    public static final int ORDER_KEEP = 0;
    /**
     * 订单状态定义
     */
    public static final int ORDER_STATUS_NEWORDER = 1;//新订单
    public static final int ORDER_STATUS_WAIT_COMPACT = 2;//未付款，带合同
    public static final int ORDER_STATUS_WAIT_PAY=3;//已合同待付款
    public static final int ORDER_STATUS_SUCCESS = 4;//付部分款
    public static final int ORDER_STATUS_COMPLETE = 5;//完成付款
    public static final int ORDER_STATUS_INVALID = 6;
    public static final int DOORORDER_STATUS_NEWORDER = 1;//新订单
    public static final int DOORORDER_STATUS_CHECKED =2;  //已经审核
    public static final int DOORORDER_STATUS_STARTED = 3; //已经出团
    public static final int DOORORDER_STATUS_COMEBACK = 4;//已经返回
    public static final int DOORORDER_STATUS_VIEWED = 5;//已经回访
    public static final int DOORORDER_STATUS_INVALID = 6;//已经失效
    /**
     * 旅游类型定义
     */
    public static final int ZHOUBIAN = 1;
    public static final int GUONEI = 2;
    public static final int CHUJING = 3;
    public static final int CHUJING_FREE = 4;
    public static final int GUONEI_FREE = 5;
    /**
     * 销售状态
     */
    public static final String SALE_OVER = "已售完";
    public static final String SALE_BOOK = "可预定";
    /**
     * 通告类型
     */
    public static final int NOTICE_JOBBER = 1;
    public static final int NOTICE_GOLBAL = 2;
}