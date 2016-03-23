package cn.jquick.it.framework.export;

import java.util.List;

public class DataPage<T>
{
    
    private static final int DEFAULT_PAGE = 1;
    
    private static int DEFAULT_PAGE_SIZE = 1000;
    
    /**
     * 数据总数量
     */
    private int totalCount;
    
    /**
     * 每次加载数据数量
     */
    private int pageSize = DEFAULT_PAGE_SIZE;
    
    /**
     * 当前页码
     */
    private int currentPage = DEFAULT_PAGE;
    
    /**
     * 当前数据
     */
    private List<T> datas;
    
    public int getPageCount()
    {
        return (totalCount == 0) ? 1
            : ((totalCount % pageSize == 0) ? (totalCount / pageSize) : (totalCount / pageSize) + 1);
    }
    
    public int getNextPage()
    {
        if (currentPage < getPageCount())
        {
            return currentPage + 1;
        }
        else
        {
            return getPageCount();
        }
    }
    
    public int getTotalCount()
    {
        return totalCount;
    }
    
    public void setTotalCount(int totalCount)
    {
        this.totalCount = totalCount;
    }
    
    public int getPageSize()
    {
        return pageSize;
    }
    
    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }
    
    public int getCurrentPage()
    {
        return currentPage;
    }
    
    public void setCurrentPage(int currentPage)
    {
        this.currentPage = currentPage;
    }
    
    public List<T> getDatas()
    {
        return datas;
    }
    
    public void setDatas(List<T> datas)
    {
        this.datas = datas;
    }
    
}
