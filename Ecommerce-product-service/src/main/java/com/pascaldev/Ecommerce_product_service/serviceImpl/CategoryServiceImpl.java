package com.pascaldev.Ecommerce_product_service.serviceImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.pascaldev.Ecommerce_product_service.model.Category;
import com.pascaldev.Ecommerce_product_service.repository.CategoryRepository;
import com.pascaldev.Ecommerce_product_service.service.CategoryService;
import com.pascaldev.Ecommerce_utils_service.model.PascalDevException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
	
	final CategoryRepository categoryRepository;

	@Override
	public Category getById(Long id) {
		log.trace("try to get category by id  : {}", id);

		try {
			Optional<Category> category = categoryRepository.findById(id);
			if (!category.isPresent()) {
				log.trace("this category does not exist");
				return null;
			}

			return category.get();
		} catch (PascalDevException e) {
//			String message = messageSource.getMessage("not found product",new Object[] {product}, locale);
			throw new PascalDevException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "not found category");
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Category> getAll() {
		log.trace("try  to find all categories");
		Page<Category> items = categoryRepository.findAll(PageRequest.of(0, 5));
		List<Category> categoryList = items.getContent();
		if (categoryList.isEmpty()) {
			log.trace("this list is empty");
			return null;
		}
		List<Category> newCategoryList = new ArrayList<>();
		for (Category category : categoryList) {
			newCategoryList.add(category);
		}
		return newCategoryList;
	}

	@Override
	public Category save(Category category) {
		log.trace("try to save category : {}", category);

		try {
			if (category == null) {
				throw new PascalDevException("unable.save.null.category");
			}
			if (!StringUtils.hasText(category.getName())) {
				throw new PascalDevException("unable.save.category.with.empty.name");
			}
			Optional<Category> newCategory = categoryRepository.findById(category.getId());
			if (newCategory.isPresent()) {
				log.trace("this category already exist");
				return category;
			}
			Category newCategory1 = categoryRepository.save(category);
			return newCategory1;

		} catch (PascalDevException e) {
			throw e;
		} catch (Exception e) {
			log.error("Unexpected error while save product : {}", category, e);
			throw new PascalDevException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "unable.to.save.category");
		}
	}

	@Override
	public Category update(Long id, Category categoryDto) {
		Category category = getById(id);
		if (category == null) {
			throw new PascalDevException("unable.to.update.null.category");

		}
		category.setName(categoryDto.getName());
		category.setDescription(categoryDto.getDescription());

		Category savedcategoryDto = save(category);

		return savedcategoryDto;
	}

	@Override
	public void deleteById(Long id) {
		log.trace("try to delete category by id: {}", id);
		try {
			if (id == null) {
				throw new PascalDevException("unable.to.delete.null.category");
			}
			categoryRepository.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Override
	public void deleteAll() {
		log.trace("try to delete allCategory : {}");

		categoryRepository.deleteAll();
	}

}
