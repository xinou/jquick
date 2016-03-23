package cn.jquick.it.framework.export.excel.template.parser;

import java.util.HashMap;
import java.util.Map;

/** 
 * 容器解析工厂类
 * <功能详细描述> 
 * 
 * @author  ouxin 
 * @version  [版本号, 2016年3月22日] 
 */
public class ParserFactory
{
    private static ParserFactory factory;
    
    private static final Map<String, Parser<?>> parsers = new HashMap<String, Parser<?>>();
    
    private ParserFactory()
    {
    }
    
    public static synchronized ParserFactory getInstance()
    {
        if (factory == null)
        {
            factory = new ParserFactory();
        }
        return factory;
    }
    
    @SuppressWarnings("unchecked")
    public synchronized <T extends Parser<?>> T getParser(Class<T> clazz)
    {
        if (parsers.get(clazz.getName()) == null)
        {
            try
            {
                parsers.put(clazz.getName(), (Parser<?>)clazz.newInstance());
            }
            catch (Exception e)
            {
            
            }
        }
        return (T)parsers.get(clazz.getName());
    }
    
}
