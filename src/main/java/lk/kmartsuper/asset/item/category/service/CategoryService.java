package lk.kmartsuper.asset.item.category.service;

import lk.kmartsuper.asset.item.category.dao.CategoryDao;
import lk.kmartsuper.asset.item.category.entity.Category;
import lk.kmartsuper.util.interfaces.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig( cacheNames = "category" )
public class CategoryService implements AbstractService<Category, Integer>{
    private final CategoryDao categoryDao;

    @Autowired
    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    public Category findById(Integer id) {
        return categoryDao.getOne(id);
    }

    public Category persist(Category category) {
        return categoryDao.save(category);
    }

    public boolean delete(Integer id) {
        categoryDao.deleteById(id);
        return false;
    }

    public List<Category> search(Category category) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Category> categoryExample = Example.of(category, matcher);
        return categoryDao.findAll(categoryExample);
    }
}
