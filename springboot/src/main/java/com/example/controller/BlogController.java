/**
 * 功能：
 * 作者：yixiangxi
 * 日期：2024/3/6 11:15
 **/
package com.example.controller;

import com.example.common.Result;
import com.example.entity.Blog;
import com.example.service.BlogService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("blog")
public class BlogController {
    @Resource
    private BlogService blogService;

//    新增
    @PostMapping("/add")
    public Result add(@RequestBody Blog blog){
        blogService.add(blog);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        blogService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        blogService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Blog blog) {
        blogService.updateById(blog);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Blog blog = blogService.selectById(id);
        return Result.success(blog);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Blog blog) {
        List<Blog> list = blogService.selectAll(blog);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Blog blog,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Blog> page = blogService.selectPage(blog, pageNum, pageSize);
        return Result.success(page);
    }

}
