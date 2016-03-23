package cn.jquick.it.framework.export.excel.template.components;

/** 
 * 容器接口
 * <功能详细描述> 
 * 
 * @author  ouxin 
 * @version  [版本号, 2016年3月22日] 
 */
public interface Container
{
    public Container getParent();
    
    public void setParent(Container parent);
}
