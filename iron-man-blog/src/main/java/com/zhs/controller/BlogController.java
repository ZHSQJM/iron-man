package com.zhs.controller;


import com.zhs.common.IronResult;
import com.zhs.entity.sys.IronFile;
import com.zhs.service.blog.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hszhou
 * @since 2020-07-08
 */
@RestController
@RequestMapping
public class BlogController  {


    @Autowired
    private IBlogService blogService;


     @GetMapping
     @PreAuthorize("hasAuthority('blog:find')")
    public IronResult list(){
          return IronResult.isOk(blogService.list());
    }

    @PostMapping("/insert")
    @PreAuthorize("hasAuthority('blog:insert')")
    public IronResult insert(){
         return IronResult.isOk();
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('blog:del')")
    public IronResult delete(@PathVariable("id")Long id){
        return IronResult.isOk();
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('blog:update')")
    public IronResult update(@PathVariable("id")Long id){
        return IronResult.isOk();
    }

}
