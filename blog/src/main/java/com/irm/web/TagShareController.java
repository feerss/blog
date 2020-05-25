package com.irm.web;

import com.irm.po.Tag;
import com.irm.service.BlogService;
import com.irm.service.TagService;
import com.irm.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TagShareController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/tag/{id}")
    public String Tags(@PageableDefault(size=8,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                        @PathVariable Long id, Model model) {
        List<Tag> tags = tagService.listTagTop(10000);
        if (id == -1) {
            id=tags.get(0).getId();
        }
        BlogQuery blogQuery = new BlogQuery();
        model.addAttribute("tags", tags);
        model.addAttribute("page", blogService.listBlog(id, pageable));
        model.addAttribute("activeTagId", id);
        System.out.println(tags.size());
        return "tags";
    }
}
