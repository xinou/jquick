package cn.jquick.it.framework.export.excel;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import cn.jquick.it.framework.export.DataPage;
import cn.jquick.it.framework.export.ExportDataSource;
import cn.jquick.it.framework.export.excel.template.SheetStruct;
import cn.jquick.it.framework.export.excel.template.WorkbookConfigure;
import cn.jquick.it.framework.export.excel.template.render.SheetRender;

/** 
 * Excel导出工具类
 * <功能详细描述> 
 * 
 * @author  ouxin 
 * @version  [版本号, 2016年3月22日] 
 */
public class ExcelExportUtils
{
    
    /**
     * 导出
     * @param config
     */
    public static <T> void export(WorkbookConfigure<T> config)
    {
        if (config == null || config.getOutputStream() == null
            || (config.getSheets() == null || config.getSheets().size() == 0))
        {
            throw new IllegalArgumentException("confg is null or outputstream is null");
        }
        doExport(config);
    }
    
    @SuppressWarnings("resource")
    private static <T> void doExport(WorkbookConfigure<T> config)
    {
        List<SheetStruct> sheets = config.getSheets();
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        CountDownLatch countDownLatch = new CountDownLatch(sheets.size());
        //启动多线程构建excel，每个sheet启动一个线程
        for (SheetStruct ss : sheets)
        {
            Thread t = new SheetRenderThread<T>(workbook.createSheet(ss.getSheetName()), ss,
                config.getDatasources().get(ss.getSheetId()), countDownLatch,
                config.getDataPages().get(ss.getSheetId()));
            t.start();
        }
        try
        {
            //所有线程结束
            countDownLatch.await();
            workbook.write(config.getOutputStream());
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
    
    static class SheetRenderThread<T> extends Thread
    {
        private Sheet sheet;
        
        private SheetStruct ss;
        
        private ExportDataSource<T> datasource;
        
        private CountDownLatch countDownLatch;
        
        private DataPage<T> datapage;
        
        public SheetRenderThread(Sheet sheet, SheetStruct ss, ExportDataSource<T> datasource,
            CountDownLatch countDownLatch, DataPage<T> datapage)
        {
            this.sheet = sheet;
            this.ss = ss;
            this.datasource = datasource;
            this.countDownLatch = countDownLatch;
            this.datapage = datapage;
        }
        
        @Override
        public void run()
        {
            SheetRender.render(this.sheet, this.ss, this.datasource, this.datapage);
            this.countDownLatch.countDown();
        }
    }
}
