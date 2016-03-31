package cn.jquick.it.front.controller.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
 * 首页管理控制器
 * <功能详细描述> 
 * 
 * @author  ouxin 
 * @version  [版本号, 2016年3月17日] 
 */
@Controller
public class IndexController
{
    /** 
     * 跳转首页
     *<功能详细描述>
     * @return 
     */
    @RequestMapping("/index.html")
    public String index()
    {
        return "index/index";
    }
}
