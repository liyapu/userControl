package com.sl.practice.base.constant;

/**
 * @author: liyapu
 * @description:  常量类
 * @date 2019-11-19 10:50
 */
public interface Const {

    /**
     * 请求成功
     */
    int SUCCESS = 200;

    /** 请求追踪的唯一id*/
    String REQUEST_TRACE_ID = "traceId";

    /** token 常量 */
    String TOKEN = "token";

    /**
     * 正确操作
     */
    int OK = 200;



    /** token 失效等情况的统一说明*/
    String TOKEN_ERR_MSG = "用户未登录或角色有误，请注销重新登录!";

    /** 删除文件的标记 */
    String DELETED = "deleted";

    /** 小圆点 */
    String DOT = ".";

    /** 下划线 */
    String UNDERSCORE = "_";

    String AMPERSAND = "&";
    String AND = "and";
    String AT = "@";
    String ASTERISK = "*";
    String STAR = ASTERISK;
    String BACK_SLASH = "\\";
    String COLON = ":";
    String COMMA = ",";
    String DASH = "-";
    String DOLLAR = "$";
    String DOTDOT = "..";
    String DOT_CLASS = ".class";
    String DOT_JAVA = ".java";
    String DOT_XML = ".xml";
    String EMPTY = "";
    String EQUALS = "=";
    String FALSE = "false";
    String SLASH = "/";
    String HASH = "#";
    String HAT = "^";
    String LEFT_BRACE = "{";
    String LEFT_BRACKET = "(";
    String LEFT_CHEV = "<";
    String DOT_NEWLINE = ",\n";
    String NEWLINE = "\n";
    String N = "n";
    String NO = "no";
    String NULL = "null";
    String OFF = "off";
    String ON = "on";
    String PERCENT = "%";
    String PIPE = "|";
    String PLUS = "+";
    String QUESTION_MARK = "?";
    String EXCLAMATION_MARK = "!";
    String QUOTE = "\"";
    String RETURN = "\r";
    String TAB = "\t";
    String RIGHT_BRACE = "}";
    String RIGHT_BRACKET = ")";
    String RIGHT_CHEV = ">";
    String SEMICOLON = ";";
    String SINGLE_QUOTE = "'";
    String BACKTICK = "`";
    String SPACE = " ";
    String TILDA = "~";
    String LEFT_SQ_BRACKET = "[";
    String RIGHT_SQ_BRACKET = "]";
    String TRUE = "true";
    String UTF_8 = "UTF-8";
    String US_ASCII = "US-ASCII";
    String ISO_8859_1 = "ISO-8859-1";
    String Y = "y";
    String YES = "yes";
    String ONE = "1";
    String ZERO = "0";
    String DOLLAR_LEFT_BRACE = "${";
    String HASH_LEFT_BRACE = "#{";
    String CRLF = "\r\n";

    String HTML_NBSP = "&nbsp;";
    String HTML_AMP = "&amp";
    String HTML_QUOTE = "&quot;";
    String HTML_LT = "&lt;";
    String HTML_GT = "&gt;";

    String DOWNLOAD = "download";
    //download 的简写,尽量缩短下载的url
    String DL = "dl";

    String PDF = ".pdf";

    Integer PAGE_NUM_DEFAULT = 1;
    Integer PAGE_SIZE_DEFAULT = 20;



}
