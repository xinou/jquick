package cn.jquick.it.framework.export.excel.template.render;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import cn.jquick.it.framework.export.DataPage;
import cn.jquick.it.framework.export.ExportDataSource;
import cn.jquick.it.framework.export.excel.template.SheetStruct;
import cn.jquick.it.framework.export.excel.template.components.Table;
import cn.jquick.it.framework.export.excel.template.components.Td;
import cn.jquick.it.framework.export.excel.template.components.Tr;

public class SheetRender
{
    public static <T> void render(Sheet sheet, SheetStruct ss, ExportDataSource<T> datasource, DataPage<T> datapage)
    {
        Table head = ss.getHead();
        int rownum = 0;
        rownum = rendHead(sheet, head, rownum);
        Table body = ss.getBody();
        renderBody(sheet, body, rownum, datasource, datapage);
        
    }
    
    /**
     * 渲染表头
     * @param sheet sheet
     * @param title 标题配置
     * @param rownum 开始行
     * @return 标题结束的下一行号
     */
    private static int rendHead(Sheet sheet, Table head, int rownum)
    {
        List<Tr> trs = head.getTrs();
        Map<Integer, Set<Integer>> notAcessCellNum = new HashMap<Integer, Set<Integer>>();//己被单元格合并占用的单元格
        Map<String, CellStyle> cellStyles = getCellStyles(head, sheet);
        for (int i = 0; i < trs.size(); i++)
        {
            Tr tr = trs.get(i);
            Row row = sheet.createRow(rownum);
            List<Td> tds = tr.getTds();
            int cellnum = 0;
            for (int m = 0; m < tds.size(); m++)
            {
                Td td = tds.get(m);
                cellnum = getNextCellNum(notAcessCellNum.get(i), cellnum);
                Cell cell = row.createCell(getNextCellNum(notAcessCellNum.get(i), cellnum));
                cell.setCellValue(td.getLabel());
                if (cellStyles.containsKey(i + "-" + m))
                {
                    cell.setCellStyle(cellStyles.get(i + "-" + m));
                }
                if (td.getRowspan() > 1 || td.getColspan() > 1)
                {
                    //单元格合并
                    sheet.addMergedRegion(new CellRangeAddress(rownum, rownum + td.getRowspan() - 1, cellnum,
                        cellnum + td.getColspan() - 1));
                    if (td.getRowspan() > 1)
                    {
                        for (int j = i + 1; j <= i + td.getRowspan(); j++)
                        {
                            if (j > trs.size())
                            {
                                break;
                            }
                            if (!notAcessCellNum.containsKey(j))
                            {
                                notAcessCellNum.put(j, new HashSet<Integer>());
                            }
                            for (int k = cellnum; k < cellnum + td.getColspan(); k++)
                            {
                                notAcessCellNum.get(j).add(k);
                            }
                        }
                    }
                }
                cellnum += td.getColspan();
            }
            rownum++;
        }
        return rownum;
    }
    
    private static int getNextCellNum(Set<Integer> set, int fromNum)
    {
        if (set != null)
        {
            while (set.contains(fromNum))
            {
                fromNum++;
            }
        }
        return fromNum;
    }
    
    private static <T> void renderBody(Sheet sheet, Table body, int rownum, ExportDataSource<T> datasource,
        DataPage<T> datapage)
    {
        if (datapage == null)
        {
            datapage = new DataPage<T>();
        }
        Map<String, CellStyle> cellStyles = getCellStyles(body, sheet);
        boolean hasNext = true;
        int dataIndex = 1;
        do
        {
            if (datapage.getCurrentPage() == datapage.getNextPage())
            {
                hasNext = false;
            }
            datapage = datasource.getData(datapage);
            List<T> datas = datapage.getDatas();
            if (datas != null)
            {
                for (T data : datas)
                {
                    rownum = renderBodyData(sheet, body, rownum, data, dataIndex, cellStyles);
                    dataIndex++;
                }
            }
            datapage.setCurrentPage(datapage.getNextPage());
        } while (hasNext);
    }
    
    private static <T> int renderBodyData(Sheet sheet, Table body, int rownum, T data, int dataIndex,
        Map<String, CellStyle> cellStyles)
    {
        List<Tr> trs = body.getTrs();
        Map<Integer, Set<Integer>> notAcessCellNum = new HashMap<Integer, Set<Integer>>();//己被单元格合并占用的单元格
        for (int i = 0; i < trs.size(); i++)
        {
            Tr tr = trs.get(i);
            Row row = sheet.createRow(rownum);
            List<Td> tds = tr.getTds();
            int cellnum = 0;
            for (int m = 0; m < tds.size(); m++)
            {
                Td td = tds.get(m);
                cellnum = getNextCellNum(notAcessCellNum.get(i), cellnum);
                Cell cell = row.createCell(getNextCellNum(notAcessCellNum.get(i), cellnum));
                setCellValue(data, cell, td, dataIndex);
                if (cellStyles.containsKey(i + "-" + m))
                {
                    cell.setCellStyle(cellStyles.get(i + "-" + m));
                }
                if (td.getRowspan() > 1 || td.getColspan() > 1)
                {
                    //单元格合并
                    sheet.addMergedRegion(new CellRangeAddress(rownum, rownum + td.getRowspan() - 1, cellnum,
                        cellnum + td.getColspan() - 1));
                    if (td.getRowspan() > 1)
                    {
                        for (int j = i + 1; j <= i + td.getRowspan(); j++)
                        {
                            if (j > trs.size())
                            {
                                break;
                            }
                            if (!notAcessCellNum.containsKey(j))
                            {
                                notAcessCellNum.put(j, new HashSet<Integer>());
                            }
                            for (int k = cellnum; k < cellnum + td.getColspan(); k++)
                            {
                                notAcessCellNum.get(j).add(k);
                            }
                        }
                    }
                }
                cellnum += td.getColspan();
            }
            rownum++;
        }
        return rownum;
    }
    
    private static <T> void setCellValue(T data, Cell cell, Td td, int dataIndex)
    {
        if ("RN".equals(td.getValueKey()))
        {
            //是否序号列
            cell.setCellValue(dataIndex);
            return;
        }
        //      String|Number|Date|Formula
        if (StringUtils.isEmpty(td.getDataType()))
        {
            td.setDataType("String");
        }
        try
        {
            Object value = BeanUtilsBean.getInstance().getPropertyUtils().getProperty(data, td.getValueKey());
            if (value == null)
            {
                cell.setCellValue("");
                return;
            }
            cell.setCellType(Cell.CELL_TYPE_STRING);
            if ("String".equals(td.getDataType()))
            {
                if (Date.class.isAssignableFrom(value.getClass()))
                {
                    SimpleDateFormat sdf = new SimpleDateFormat(td.getDateFormat());
                    cell.setCellValue(sdf.format((Date)value));
                }
                else
                {
                    cell.setCellValue(value.toString());
                }
            }
            else if ("Number".equals(td.getDataType()))
            {
                if (Number.class.isAssignableFrom(value.getClass()))
                {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(((Number)value).doubleValue());
                }
                else
                {
                    cell.setCellValue(value.toString());
                }
            }
            else if ("Date".equals(td.getDataType()))
            {
                if (Date.class.isAssignableFrom(value.getClass()))
                {
                    SimpleDateFormat sdf = new SimpleDateFormat(td.getDateFormat());
                    cell.setCellValue(sdf.format((Date)value));
                }
                else
                {
                    cell.setCellValue(value.toString());
                }
            }
            else if ("Formula".equals(td.getDataType()))
            {
                if (td.getLabel().startsWith("#") && td.getLabel().endsWith("#"))
                {
                    cell.setCellFormula(
                        td.getLabel().substring(td.getLabel().indexOf("#") + 1, td.getLabel().lastIndexOf("#")));
                }
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
    
    private static CellStyle createCellStyle(Sheet sheet)
    {
        return sheet.getWorkbook().createCellStyle();
    }
    
    private static Map<String, CellStyle> getCellStyles(Table table, Sheet sheet)
    {
        List<Tr> trs = table.getTrs();
        Map<String, CellStyle> cellStyles = new HashMap<String, CellStyle>();
        for (int i = 0; i < trs.size(); i++)
        {
            List<Td> tds = trs.get(i).getTds();
            for (int j = 0; j < tds.size(); j++)
            {
                CellStyle cellStyle = createCellStyle(sheet);
                parseCellStyle(cellStyle, sheet, tds.get(j));
                cellStyles.put(i + "-" + j, cellStyle);
            }
        }
        return cellStyles;
    }
    
    private static void parseCellStyle(CellStyle cellStyle, Sheet sheet, Td td)
    {
        if ("center".equals(td.getAlign()))
        {
            cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        }
        else if ("left".equals(td.getAlign()))
        {
            cellStyle.setAlignment(CellStyle.ALIGN_LEFT);
        }
        else if ("right".equals(td.getAlign()))
        {
            cellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
        }
        Font font = sheet.getWorkbook().createFont();
        font.setCharSet(Font.DEFAULT_CHARSET);
        if (StringUtils.isNotEmpty(td.getTextColor()))
        {
            font.setColor(IndexedColors.valueOf(td.getTextColor()).getIndex());
        }
        if (StringUtils.isNotEmpty(td.getFontSize()) && StringUtils.isNumeric(td.getFontSize()))
        {
            font.setFontHeightInPoints(Short.parseShort(td.getFontSize()));
        }
        cellStyle.setFont(font);
        if (StringUtils.isNotEmpty(td.getBackgroundColor()))
        {
            cellStyle.setFillForegroundColor(IndexedColors.valueOf(td.getBackgroundColor()).getIndex());
            cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        }
        if (StringUtils.isNotEmpty(td.getBorder()) && StringUtils.isNumeric(td.getBorder()))
        {
            cellStyle.setBorderBottom(Short.parseShort(td.getBorder()));
            cellStyle.setBorderLeft(Short.parseShort(td.getBorder()));
            cellStyle.setBorderRight(Short.parseShort(td.getBorder()));
            cellStyle.setBorderTop(Short.parseShort(td.getBorder()));
        }
        if (StringUtils.isNotEmpty(td.getBorderColor()))
        {
            cellStyle.setLeftBorderColor(IndexedColors.valueOf(td.getBorderColor()).getIndex());
            cellStyle.setTopBorderColor(IndexedColors.valueOf(td.getBorderColor()).getIndex());
            cellStyle.setRightBorderColor(IndexedColors.valueOf(td.getBorderColor()).getIndex());
            cellStyle.setBottomBorderColor(IndexedColors.valueOf(td.getBorderColor()).getIndex());
        }
    }
    
}
