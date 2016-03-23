package cn.jquick.it.framework.export.excel.template.parser;

import org.xml.sax.Attributes;

import cn.jquick.it.framework.export.excel.template.components.Container;

/** 
 * 容器格式化接口
 * <功能详细描述> 
 * 
 * @author  ouxin 
 * @version  [版本号, 2016年3月22日] 
 */
public interface Parser<T extends Container>
{
    public void parse(T container, Attributes attributes);
}
