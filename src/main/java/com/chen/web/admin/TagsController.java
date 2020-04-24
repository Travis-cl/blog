package com.chen.web.admin;

import com.chen.pojo.Tag;
import com.chen.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagsController {

    @Autowired
    private TagService tagService;

    //跳转路径为@{/admin/tags}时跳转到admin文件夹下的tags.html
    @GetMapping("/tags")
    public String toTagsPage(@RequestParam(required = false,defaultValue = "1",value = "pageNum") int pageNum,
                             Model model){
        PageHelper.startPage(pageNum,5);
        List<Tag> tags = tagService.getAllTag();
        PageInfo<Tag> pageInfo = new PageInfo<>(tags);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/tags";
    }


    //跳转至新增页面
    @GetMapping("/tags/input")
    public String toAddPage(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";
    }

    //新增
    @PostMapping("/tags")
    public String addTag(Tag tag,RedirectAttributes redirectAttributes){
        Tag tagByName = tagService.getTagByName(tag.getName());
        if (tagByName != null){
            //重复,失败
            redirectAttributes.addFlashAttribute("msg","已经存在的标签，换一个吧");

            return "redirect:/admin/tags/input";
        }

        int i = tagService.saveTag(tag);
        if (i != 0) {
            //添加成功
            redirectAttributes.addFlashAttribute("msg", "添加标签成功");
        } else {
            redirectAttributes.addFlashAttribute("msg", "添加标签失败，再试一次");
        }
        return "redirect:/admin/tags";

    }

    //前往编辑页面
    @GetMapping("/tags/{id}/input")
    public String toEditPage(@PathVariable Long id,Model model){
        Tag tag = tagService.getTag(id);
        model.addAttribute("tag",tag);
        return "admin/tags-input";
    }

    //编辑保存
    @PostMapping("/tags/{id}")
    public String editTag(@PathVariable Long id,Tag tag,RedirectAttributes redirectAttributes){
        Tag tagByName = tagService.getTagByName(tag.getName());
        if (tagByName != null){
            redirectAttributes.addFlashAttribute("msg","已经存在的标签，换一个吧");
            return "redirect:/admin/tags/input";
        }

        int i = tagService.updateTag(tag);
        if (i != 0) {
            redirectAttributes.addFlashAttribute("msg", "修改成功");
        } else {
            redirectAttributes.addFlashAttribute("msg", "修改失败");
        }
        return "redirect:/admin/tags";

    }

    //删除
    @GetMapping("tags/{id}/delete")
    public String deleteTag(@PathVariable Long id,RedirectAttributes redirectAttributes){
        int i = tagService.deleteTag(id);
        if (i != 0){
            redirectAttributes.addFlashAttribute("msg","删除成功");
        }else {
            redirectAttributes.addFlashAttribute("msg","删除失败");
        }
        return "redirect:/admin/tags";
    }

}
