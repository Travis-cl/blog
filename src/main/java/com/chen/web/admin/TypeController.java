package com.chen.web.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.chen.pojo.Type;
import com.chen.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(@RequestParam(required = false,defaultValue = "1",value = "pageNum")int pageNum, Model model){
        PageHelper.startPage(pageNum,5);
        List<Type> allTypes = typeService.getAllType();
        /*
        * 超级大的坑，浪费了我两个小时！！！！！ 一定要加types
        * 告诉我们一个道理，自己不懂得工具，一定要了解使用方法，而不是直接复制！！！！
        *
        * */
        PageInfo<Type> pageInfo = new PageInfo<Type>(allTypes);
        //大坑，如果分页出现问题，排查导包有没有导对
        model.addAttribute("pageInfo",pageInfo);
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String toAddPage(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    /*编辑*/
    @GetMapping("/types/{id}/input")
    public String toEditInput(@PathVariable Long id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }

    //这里的id和type没有搞清楚，后期一定要搞清楚--4.17搞清楚了
    @PostMapping("/types/{id}")
    public String editInput(@PathVariable Long id,Type type, RedirectAttributes redirectAttributes){
        Type typeByName = typeService.getTypeByName(type.getName());

        if (typeByName != null){
            //已经存在，提交失败。
            redirectAttributes.addFlashAttribute("msg","换一个吧，已经存在");

            return "redirect:/admin/types/input";
        }else {

            int i = typeService.updateType(type);
            if (i == 1){
                //插入成功
                redirectAttributes.addFlashAttribute("msg","更新成功");
            }else {
                //插入失败
                redirectAttributes.addFlashAttribute("msg","更新失败");

            }
            //必须重定向到路径，不能直接返回页面，因为需要重新进行查询
            return "redirect:/admin/types";
        }
    }
    /*删除*/
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes redirectAttributes){
        int i = typeService.deleteType(id);
        if (i != 0){
            redirectAttributes.addFlashAttribute("msg","删除成功");
        }else {
            redirectAttributes.addFlashAttribute("msg","删除失败");
        }
        return "redirect:/admin/types";

    }


    //新增
    //Restful风格支持不同方式用相同路径。post和get都用types
    @PostMapping("/types")
    public String addType(Type type, RedirectAttributes redirectAttributes){
        Type typeByName = typeService.getTypeByName(type.getName());
        if (typeByName != null){
            //已经存在，提交失败。
            redirectAttributes.addFlashAttribute("msg","换一个吧，已经存在");

            return "redirect:/admin/types/input";
        }
        int i = typeService.saveType(type);
        if (i == 1){
            //插入成功
            redirectAttributes.addFlashAttribute("msg","恭喜你啊，添加成功了呢");
        }else {
            //插入失败
            redirectAttributes.addFlashAttribute("msg","对不起，添加操作失败");

        }
        //必须重定向到路径，不能直接返回页面，因为需要重新进行查询
        return "redirect:/admin/types";
    }

}
