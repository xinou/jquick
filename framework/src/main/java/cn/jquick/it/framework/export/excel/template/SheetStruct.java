package cn.jquick.it.framework.export.excel.template;

import cn.jquick.it.framework.export.excel.template.components.Table;

/** 
 * 工作簿结构框架
 * <功能详细描述> 
 * 
 * @author  ouxin 
 * @version  [版本号, 2016年3月22日] 
 */
public class SheetStruct
{
    
    /**
     * 工作薄名称
     */
    private String sheetName;
    
    /**
     * 工作薄ID
     */
    private String sheetId;
    
    /**
     * 列头
     */
    private Table head;
    
    /**
     * 数据
     */
    private Table body;
    
    /**
     * 列尾
     */
    private Table foot;
    
    /**
     * 单个工作簿最大行数
     * excel 03 单sheet最大行数为65536 = 256^2
     * excel 07 单sheet最大行数为1048576 = 1024^2
     */
    private int maxRows = 1000000;//excel 07 单sheet最大行数为1048576

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public String getSheetId() {
        return sheetId;
    }

    public void setSheetId(String sheetId) {
        this.sheetId = sheetId;
    }

    public Table getBody() {
        return body;
    }

    public void setBody(Table body) {
        this.body = body;
    }

    public Table getFoot() {
        return foot;
    }

    public void setFoot(Table foot) {
        this.foot = foot;
    }

    public void setHead(Table head) {
        this.head = head;
    }

    public Table getHead() {
        return head;
    }

    public void setMaxRows(int maxRows) {
        this.maxRows = maxRows;
    }

    public int getMaxRows() {
        return maxRows;
    }
}
