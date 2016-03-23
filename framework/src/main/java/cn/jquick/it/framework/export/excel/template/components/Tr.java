package cn.jquick.it.framework.export.excel.template.components;

import java.util.ArrayList;
import java.util.List;

/** 
 * 行组件
 * <功能详细描述> 
 * 
 * @author  ouxin 
 * @version  [版本号, 2016年3月22日] 
 */
public class Tr extends StyledComponents
{
    
    private Table parent;
    
    private List<Td> tds = new ArrayList<Td>();
    
    @Override
    public StyledComponents getParent()
    {
        return parent;
    }
    
    @Override
    public void setParent(Container parent)
    {
        this.parent = (Table)parent;
    }
    
    public void setTds(List<Td> tds)
    {
        this.tds = tds;
    }
    
    public List<Td> getTds()
    {
        return tds;
    }
}
