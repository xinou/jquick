package cn.jquick.it.framework.export.excel.template.components;

import java.util.ArrayList;
import java.util.List;

/** 
 * 表格组件,没有父级容器
 * <功能详细描述> 
 * 
 * @author  ouxin 
 * @version  [版本号, 2016年3月22日] 
 */
public class Table extends StyledComponents
{
    private List<Tr> trs = new ArrayList<Tr>();
    
    @Override
    public StyledComponents getParent()
    {
        return null;
    }
    
    @Override
    public void setParent(Container parent)
    {
    }
    
    public void setTrs(List<Tr> trs)
    {
        this.trs = trs;
    }
    
    public List<Tr> getTrs()
    {
        return trs;
    }
}
