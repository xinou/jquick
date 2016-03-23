package cn.jquick.it.framework.export.excel.template.components;

/** 
 * 列组件
 * <功能详细描述> 
 * 
 * @author  ouxin 
 * @version  [版本号, 2016年3月22日] 
 */
public class Td extends StyledComponents
{
    
    /**
     * 单元格内容
     */
    private String label;
    
    /**
     * 取值key
     */
    private String valueKey;
    
    /**
     * 列合并
     */
    private int colspan;
    
    /**
     * 行合并
     */
    private int rowspan;
    
    /**
     * 列宽
     */
    private int width;
    
    /**
     * 行高
     */
    private int height;
    
    /**
     * 数据类型
     */
    private String dataType;
    
    /**
     * 时间类型格式
     */
    private String dateFormat = "yyyy-MM-dd HH:mm:ss";
    
    private Tr parent;
    
    @Override
    public StyledComponents getParent()
    {
        return parent;
    }
    
    @Override
    public void setParent(Container parent)
    {
        this.parent = (Tr)parent;
    }
    
    public void setValueKey(String valueKey)
    {
        this.valueKey = valueKey;
    }
    
    public String getValueKey()
    {
        return valueKey;
    }
    
    public void setLabel(String label)
    {
        this.label = label;
    }
    
    public String getLabel()
    {
        return label;
    }
    
    public void setRowspan(int rowspan)
    {
        this.rowspan = rowspan;
    }
    
    public int getRowspan()
    {
        return rowspan;
    }
    
    public void setColspan(int colspan)
    {
        this.colspan = colspan;
    }
    
    public int getColspan()
    {
        return colspan;
    }
    
    public void setHeight(int height)
    {
        this.height = height;
    }
    
    public int getHeight()
    {
        return height;
    }
    
    public void setWidth(int width)
    {
        this.width = width;
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public void setDataType(String dataType)
    {
        this.dataType = dataType;
    }
    
    public String getDataType()
    {
        return dataType;
    }
    
    public void setDateFormat(String dateFormat)
    {
        this.dateFormat = dateFormat;
    }
    
    public String getDateFormat()
    {
        return dateFormat;
    }
    
}
