package cn.jquick.it.framework.export.excel.template.parser;

import org.apache.commons.lang.StringUtils;
import org.xml.sax.Attributes;

import cn.jquick.it.framework.export.excel.template.components.Td;

public class TdParser extends StyledComponentsParser<Td> {
    @Override
    public void parse(Td td, Attributes attributes) {
        super.parse(td, attributes);
        td.setLabel(attributes.getValue("label"));
        if(StringUtils.isNotEmpty(attributes.getValue("colspan")) && StringUtils.isNumeric(attributes.getValue("colspan"))){
            td.setColspan(Integer.parseInt(attributes.getValue("colspan")));
        }
        if(StringUtils.isNotEmpty(attributes.getValue("rowspan")) && StringUtils.isNumeric(attributes.getValue("rowspan"))){
            td.setRowspan(Integer.parseInt(attributes.getValue("rowspan")));
        }
        if(StringUtils.isNotEmpty(attributes.getValue("width")) && StringUtils.isNumeric(attributes.getValue("width"))){
            td.setWidth(Integer.parseInt(attributes.getValue("width")));
        }
        if(StringUtils.isNotEmpty(attributes.getValue("height")) && StringUtils.isNumeric(attributes.getValue("height"))){
            td.setHeight(Integer.parseInt(attributes.getValue("height")));
        }
        if(StringUtils.isNotEmpty(attributes.getValue("dataType"))){
            td.setDataType(attributes.getValue("dataType"));
        }
        if(StringUtils.isNotEmpty(attributes.getValue("dateFormat"))){
            td.setDateFormat(attributes.getValue("dateFormat"));
        }
    }
}

