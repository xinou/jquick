package cn.jquick.it.framework.export.excel.template;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import cn.jquick.it.framework.export.excel.template.components.Table;
import cn.jquick.it.framework.export.excel.template.components.Td;
import cn.jquick.it.framework.export.excel.template.components.Tr;
import cn.jquick.it.framework.export.excel.template.parser.ParserFactory;
import cn.jquick.it.framework.export.excel.template.parser.TableParser;
import cn.jquick.it.framework.export.excel.template.parser.TdParser;
import cn.jquick.it.framework.export.excel.template.parser.TrParser;

/** 
 * xml模板解析工具
 * <功能详细描述> 
 * 
 * @author  ouxin 
 * @version  [版本号, 2016年3月22日] 
 */
public class XmlTemplateParser
{
    
    private TdParser tdParser;
    
    private TrParser trParser;
    
    private TableParser tableParser;
    
    /**
     * 模板文件
     */
    private File file;
    
    public XmlTemplateParser(File file)
    {
        if (file == null || !file.isFile())
        {
            throw new IllegalArgumentException("非法模板文件:" + file.getPath());
        }
        this.file = file;
        this.tdParser = ParserFactory.getInstance().getParser(TdParser.class);
        this.trParser = ParserFactory.getInstance().getParser(TrParser.class);
        this.tableParser = ParserFactory.getInstance().getParser(TableParser.class);
    }
    
    private void parseSheet(SheetStruct sheet, Attributes attributes)
    {
        sheet.setSheetId(attributes.getValue("id"));
        sheet.setSheetName(attributes.getValue("name"));
    }
    
    public List<SheetStruct> parse()
    {
        final List<SheetStruct> sheets = new LinkedList<SheetStruct>();
        try
        {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse(file, new DefaultHandler()
            {
                private SheetStruct sheetStruct;
                
                private Table table;
                
                private Tr tr;
                
                private Td td;
                
                public void startElement(String uri, String localName, String qName, org.xml.sax.Attributes attributes)
                    throws SAXException
                {
                    if ("sheet".equals(qName))
                    {
                        sheetStruct = new SheetStruct();
                        parseSheet(sheetStruct, attributes);
                    }
                    else if ("head".equals(qName))
                    {
                        table = new Table();
                        tableParser.parse(table, attributes);
                    }
                    else if ("body".equals(qName))
                    {
                        table = new Table();
                        tableParser.parse(table, attributes);
                    }
                    else if ("foot".equals(qName))
                    {
                        table = new Table();
                        tableParser.parse(table, attributes);
                    }
                    else if ("tr".equals(qName))
                    {
                        tr = new Tr();
                        tr.setParent(table);
                        trParser.parse(tr, attributes);
                    }
                    else if ("td".equals(qName))
                    {
                        td = new Td();
                        td.setParent(tr);
                        tdParser.parse(td, attributes);
                    }
                };
                
                public void endElement(String uri, String localName, String qName)
                    throws SAXException
                {
                    if ("sheet".equals(qName))
                    {
                        sheets.add(sheetStruct);
                        sheetStruct = null;
                    }
                    else if ("head".equals(qName))
                    {
                        sheetStruct.setHead(table);
                        table = null;
                    }
                    else if ("body".equals(qName))
                    {
                        sheetStruct.setBody(table);
                        table = null;
                    }
                    else if ("foot".equals(qName))
                    {
                        sheetStruct.setFoot(table);
                        table = null;
                    }
                    else if ("tr".equals(qName))
                    {
                        if (table.getTrs() == null)
                        {
                            table.setTrs(new LinkedList<Tr>());
                        }
                        table.getTrs().add(tr);
                        tr = null;
                    }
                    else if ("td".equals(qName))
                    {
                        if (tr.getTds() == null)
                        {
                            tr.setTds(new LinkedList<Td>());
                        }
                        tr.getTds().add(td);
                        td = null;
                    }
                };
            });
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        catch (SAXException e)
        {
            throw new RuntimeException(e);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        catch (ParserConfigurationException e)
        {
            throw new RuntimeException(e);
        }
        return sheets;
    }
    
}
