package cn.jquick.it.framework.export;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jquick.it.framework.export.excel.ExcelExportUtils;
import cn.jquick.it.framework.export.excel.template.WorkbookConfigure;

public class Test
{
    public static void main(String[] args)
        throws FileNotFoundException
    {
        WorkbookConfigure<Object> config = new WorkbookConfigure<Object>();
        config.loadConfigByTemplateFile(
            new File("F:\\test\\exportDemo.xml"));
        final int totalCount = 10;
        config.addDataSource("test", new ExportDataSource<Object>()
        {
            @Override
            public DataPage<Object> getData(DataPage<Object> dataPage)
            {
                dataPage.setTotalCount(totalCount);
                List<Object> list = new ArrayList<Object>();
                for (int i = 0; i < totalCount; i++)
                {
                    Map<String, Object> data = new HashMap<String, Object>();
                    data.put("String", "aa" + i);
                    data.put("int", i ^ 2);
                    data.put("float", 1.1 * i);
                    data.put("Date", new Date());
                    data.put("aa", "aa" + i);
                    list.add(data);
                }
                dataPage.setDatas(list);
                return dataPage;
            }
        });
        config.setOutputStream(new FileOutputStream("F:/test/testTemplate.xlsx"));
        ExcelExportUtils.export(config);
    }
}
