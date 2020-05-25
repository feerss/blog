package com.irm.web;
import com.irm.NotFoundException;
import com.irm.service.BlogService;
import com.irm.service.TagService;
import com.irm.service.TypeService;
import com.irm.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/")
    public String index(@PageableDefault(size=8,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                        Model model) {
        model.addAttribute("page", blogService.listBlog(pageable));
        model.addAttribute("types", typeService.listType(6));
        model.addAttribute("tags", tagService.listTagTop(10));
        model.addAttribute("recommendBlogs", blogService.listRecommendBlogTop(8));
        return "index";
    }

    @RequestMapping(value = "/search")
    public String search(@PageableDefault(size=8,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                        String query, Model model) {
        model.addAttribute("page", blogService.listBlog("%"+query+"%", pageable));
        model.addAttribute("query", query);

        return "search";
    }

    @RequestMapping(value = "/blog/{id}")
    public String blog(@PathVariable Long id,Model model) {
        model.addAttribute("blog", blogService.getAndConvert(id));
        return "blog";
    }

    @GetMapping("/footer/newblog")
    public String newblogs(Model model) {
        model.addAttribute("newBlog", blogService.listRecommendBlogTop(3));
        return "_fragments:: newblogList";
    }
}
