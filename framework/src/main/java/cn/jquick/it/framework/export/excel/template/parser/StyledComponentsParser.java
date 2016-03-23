package cn.jquick.it.framework.export.excel.template.parser;

import org.apache.commons.lang.StringUtils;
import org.xml.sax.Attributes;

import cn.jquick.it.framework.export.excel.template.components.StyledComponents;

public abstract class StyledComponentsParser<T extends StyledComponents> implements Parser<T>
{
    @Override
    public void parse(T container, Attributes attributes)
    {
        if (StringUtils.isNotEmpty(attributes.getValue("align")))
        {
            container.setAlign(attributes.getValue("align"));
        }
        if (StringUtils.isNotEmpty(attributes.getValue("textColor")))
        {
            container.setTextColor(attributes.getValue("textColor"));
        }
        if (StringUtils.isNotEmpty(attributes.getValue("fontSize")))
        {
            container.setFontSize(attributes.getValue("fontSize"));
        }
        if (StringUtils.isNotEmpty(attributes.getValue("backgroundColor")))
        {
            container.setBackgroundColor(attributes.getValue("backgroundColor"));
        }
        if (StringUtils.isNotEmpty(attributes.getValue("border")))
        {
            container.setBorder(attributes.getValue("border"));
        }
        if (StringUtils.isNotEmpty(attributes.getValue("borderColor")))
        {
            container.setBorderColor(attributes.getValue("borderColor"));
        }
    }
    
}
