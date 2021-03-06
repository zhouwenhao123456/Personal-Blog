package cn.edu.controller.admin;

import cn.edu.po.Blog;
import cn.edu.po.Tag;
import cn.edu.po.User;
import cn.edu.service.BlogService;
import cn.edu.service.TagService;
import cn.edu.service.TypeService;
import cn.edu.vo.BlogSearch;
import cn.edu.vo.IndexBlog;
import cn.edu.vo.PageRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class BlogController {

  @Autowired
  private BlogService blogService;
  @Autowired
  private TypeService typeService;
  @Autowired
  private TagService tagService;
  @Value("${pageRequest.pagesize}")
  private int pageSize;
  public void setTypeAndTagToModel(Model model) {
    model.addAttribute("types", typeService.listType());
    model.addAttribute("tags", tagService.listTag());
  }

  private void setTypeToModel(Model model) {
    model.addAttribute("types", typeService.listType());
  }

  private List<Tag> setTagsToBlog(Blog blog) {
    if (blog.getTagIds() == null || blog.getTagIds().equals("")) {
      return null;
    }
    String[] tagIds = blog.getTagIds().split(",");
    if (tagIds == null) {
      return null;
    }
    List<Tag> listTag = new ArrayList<>();
    for (String tagid : tagIds) {
      listTag.add(tagService.getTagById(Long.parseLong(tagid)));
    }
    return listTag;

  }

  //????????????
  @GetMapping("/blogs")
  public String listblog(
      @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
      Model model) {

    model.addAttribute("page", blogService.getPageInfo(pageNum,pageSize));
    setTypeToModel(model);
    return "admin/blogs";
  }

  //?????????????????????
  @PostMapping("/blogs")
  public String post(@Valid Blog blog, BindingResult bindingResult, RedirectAttributes attributes, HttpSession session) {
    if (bindingResult.hasErrors()){

    }
    blog.setType(typeService.getTypeById(blog.getTypeId()));
    blog.setUser((User) session.getAttribute("user"));
    blog.setTags(setTagsToBlog(blog));
    Boolean b;
    if (blog.getId() == null || blog.getId() == "") {
      b = blogService.saveBlog(blog);
    }else {
      b=blogService.updateBlog(blog);
    }
    Boolean published = blog.isPublished();
    String message = null;
    if (published) {//??????
      if (b) {
        message = "??????????????????";
      } else {
        message = "??????????????????";
      }
    } else {//??????
      if (b) {
        message = "????????????????????????";
      } else {
        message = "??????????????????";
      }
    }
    attributes.addFlashAttribute("message", message);
    return "redirect:/admin/blogs";
  }

  //???????????????????????????
  @GetMapping("/blogs/input")
  public String blogInputPage(Model model, HttpSession session) {
    User user = (User) session.getAttribute("user");
    Blog blog = blogService.getUnPublishedBlog(user.getId());
    model.addAttribute("blog", blog == null ? new Blog() : blog);
    setTypeAndTagToModel(model);
    return "admin/blogs_input";
  }

  //???????????????????????????
  @GetMapping("/blogs/{id}/input")
  public String blogUpdatePage(@PathVariable String id, Model model) {
    Blog blog = blogService.getBlogById(id);
    model.addAttribute("blog", blog);
    setTypeAndTagToModel(model);
    return "admin/blogs_update";
  }
  @GetMapping("/blogs/{id}/check")
  public String blogCheckPage(@PathVariable String id, Model model) {
    IndexBlog blog = blogService.getDetailBlog(id);
    model.addAttribute("blog", blog);
    setTypeAndTagToModel(model);
    return "admin/blog";
  }

  //????????????
  @PostMapping("/blogs/{id}")
  public String updateBlog(@Valid Blog blog, RedirectAttributes attributes) {
    blog.setType(typeService.getTypeById(blog.getTypeId()));
    blog.setTags(setTagsToBlog(blog));
    Boolean b = blogService.updateBlog(blog);
    String message = null;
    if (b) {
      message = "??????????????????";
    } else {
      message = "??????????????????";
    }
    attributes.addFlashAttribute("message", message);
    return "redirect:/admin/blogs";
  }

  //????????????
  @PostMapping("/blogs/search")
  public String search(
      @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
      BlogSearch blogSearch, Model model) {
    model.addAttribute("page", blogService.getPageInfoBySearch(pageNum,pageSize, blogSearch));
    setTypeToModel(model);
    return "admin/blogs";
  }

  //????????????
  @GetMapping("/blogs/{id}/delete")
  public String delete(@PathVariable String id, RedirectAttributes attributes) {
    Boolean b = blogService.deleteBlog(id);
    if (b) {
      attributes.addFlashAttribute("message", "????????????");
    } else {
      attributes.addFlashAttribute("message", "????????????");
    }
    return "redirect:/admin/blogs";
  }
}
