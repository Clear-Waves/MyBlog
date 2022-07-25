package cdu.cyj.contorller;

import cdu.cyj.domain.vo.CategoryVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @GetMapping("/getCategoryList")
    public List<CategoryVo> getCategoryList() {
        return null;
    }

}
