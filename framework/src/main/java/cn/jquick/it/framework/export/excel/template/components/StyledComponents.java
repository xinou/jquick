package cn.jquick.it.framework.export.excel.template.components;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import cn.jquick.it.framework.utils.StringUtils;

/** 
 * 样式化组件
 * <功能详细描述> 
 * 
 * @author  ouxin 
 * @version  [版本号, 2016年3月22日] 
 */
@SuppressWarnings("unused")
public abstract class StyledComponents implements Container
{

    /**
     * 字体居中
     */
    private String align;
    
    /**
     * 字体颜色
     */
    private String textColor;
    
    /**
     * 字体
     */
    private String fontSize;
    
    /**
     * 背景色
     */
    private String backgroundColor;
    
    /**
     * 边框颜色
     */
    private String borderColor;
    
    /**
     * 边框大小
     */
    private String border;
    
    protected String getAttribute(String attribute){
        try{
            String value = getPropertyValue(attribute);
            if(StringUtils.isEmpty(value) && getParent() != null){
                value = getParent().getAttribute(attribute);
            }
            return value;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    
    private String getPropertyValue(String field){
        try {
            List<Field> fields = new LinkedList<Field>();
            getAllFields(this.getClass(), fields);
            Object value = null;
            for(Field f : fields){
                if(f.getName().endsWith(field)){
                    value = f.get(this);
                    break;
                }
            }
            
            return value==null?null:value.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }       
    }
    
    private void getAllFields(Class<?> clazz,List<Field> fields){
        if(clazz == null){
            return;
        }
        Field[] fs = clazz.getDeclaredFields();
        for(Field f : fs){
            fields.add(f);
        }
        getAllFields(clazz.getSuperclass(), fields);
    }
    
    @Override
    public abstract StyledComponents getParent();

    public String getAlign() {
        return getAttribute("align");
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public String getTextColor() {
        return getAttribute("textColor");
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public String getFontSize() {
        return getAttribute("fontSize");
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    public String getBackgroundColor() {
        return getAttribute("backgroundColor");
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getBorderColor() {
        return getAttribute("borderColor");
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public String getBorder() {
        return getAttribute("border");
    }

    public void setBorder(String border) {
        this.border = border;
    }
}