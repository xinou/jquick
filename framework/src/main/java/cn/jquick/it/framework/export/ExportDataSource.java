package cn.jquick.it.framework.export;

public interface ExportDataSource<T>
{
    /**
     * 获取下一页数据
     * 返回数据必须提供pageData.totalCount,datas
     * @param dataPage
     * @return
     */
    DataPage<T> getData(DataPage<T> dataPage);
}
