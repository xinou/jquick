package cn.jquick.it.framework.export.excel.template;

import java.io.File;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jquick.it.framework.export.DataPage;
import cn.jquick.it.framework.export.ExportDataSource;
import cn.jquick.it.framework.export.excel.template.components.Table;
import cn.jquick.it.framework.export.excel.template.components.Td;
import cn.jquick.it.framework.export.excel.template.components.Tr;

/** 
 * excel文档配置
 * <功能详细描述> 
 * 
 * @author  ouxin 
 * @version  [版本号, 2016年3月22日] 
 */
public class WorkbookConfigure<T> {

    /**
     * 工作簿集合
     */
    private List<SheetStruct> sheets;
    
    /**
     * 数据源,按SheetStruct.sheetId为key存储
     */
    private Map<String,ExportDataSource<T>> datasources = new HashMap<String, ExportDataSource<T>>();
    
    /**
     * 数据分页对象
     */
    private Map<String,DataPage<T>> dataPages = new HashMap<String, DataPage<T>>();
    
    /**
     * 导出输出流
     */
    private OutputStream outputStream;
    
    /**
     * 从模板文件(xml)加载配置
     * @param file
     */
    public void loadConfigByTemplateFile(File file){
        sheets = new XmlTemplateParser(file).parse();
        //转换body里面的label为valueKey
        for(SheetStruct ss : sheets){
            Table body = ss.getBody();
            List<Tr> trs = body.getTrs();
            for(Tr tr : trs){
                List<Td> tds = tr.getTds();
                for(Td td : tds){
                    td.setValueKey(transform(td.getLabel()));
                }
            }
        }
    }

    private String transform(String label) {
        return label.replaceAll("%", "");
    }

    public List<SheetStruct> getSheets() {
        return sheets;
    }

    public void setSheets(List<SheetStruct> sheets) {
        this.sheets = sheets;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void setDatasources(Map<String,ExportDataSource<T>> datasources) {
        this.datasources = datasources;
    }

    public Map<String,ExportDataSource<T>> getDatasources() {
        return datasources;
    }
    
    /**
     * 增加数据源
     * @param key
     * @param datasource
     */
    public void addDataSource(String key,ExportDataSource<T> datasource){
        this.datasources.put(key, datasource);
    }
    
    /**
     * 增加分页对象
     * @param key
     * @param datapage
     */
    public void addDataPage(String key,DataPage<T> datapage){
        this.dataPages.put(key, datapage);
    }

    public void setDataPages(Map<String,DataPage<T>> dataPages) {
        this.dataPages = dataPages;
    }

    public Map<String,DataPage<T>> getDataPages() {
        return dataPages;
    }
    
}
