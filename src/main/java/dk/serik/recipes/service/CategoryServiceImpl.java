package dk.serik.recipes.service;

import dk.serik.recipes.bean.Session;
import dk.serik.recipes.dto.CategoryDTO;
import dk.serik.recipes.exceptions.ApplicationErrorCodes;
import dk.serik.recipes.exceptions.ServiceException;
import dk.serik.recipes.mapper.CategoryMapper;
import dk.serik.recipes.model.Category;
import dk.serik.recipes.repository.CategoryJpaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional(
		  isolation = Isolation.READ_COMMITTED,
		  propagation = Propagation.REQUIRED, 
		  readOnly = false, 
		  timeout = 5)
@Slf4j
public class CategoryServiceImpl implements CategoryService {
	
	private CategoryJpaRepository categoryJpaRepository;

	private Session session;

	@Override
	public Optional<List<CategoryDTO>> findAll() {
		List<Category> all = categoryJpaRepository.findAll();
		if(all.size() == 0) {
			return Optional.empty();
		}
		
		List<CategoryDTO> dtos = all.stream()
				.map(e -> CategoryMapper.from(e))
				.collect(Collectors.toList());
		
		return Optional.of(dtos);
	}

	@Override
	public Optional<CategoryDTO> findById(String id) {
		Optional<Category> categoryEntity = categoryJpaRepository.findById(UUID.fromString(id));
		if(categoryEntity.isPresent()) {
			return Optional.ofNullable(CategoryMapper.from(categoryEntity.get()));
		}
		
		return Optional.empty();
	}

	@Override
	public Optional<CategoryDTO> findByName(String name) {
		Optional<Category> categoryEntity =  categoryJpaRepository.findByName(name);
		
		if(categoryEntity.isPresent()) {
			return Optional.ofNullable(CategoryMapper.from(categoryEntity.get()));
		}
		
		return Optional.empty();
	}

	@Override
	public CategoryDTO save(CategoryDTO categoryDto) {
		if(Objects.nonNull(categoryDto)) {
			Category entity = new Category();
			entity.setDescription(categoryDto.getDescription());
			entity.setName(categoryDto.getName());
			entity.setCreatedBy(session.getUserName());
			Category managedCategory = categoryJpaRepository.save(entity);
			CategoryDTO dto = CategoryMapper.from(managedCategory);
			return dto;
		}

		return null;
	}

	@Override
	public boolean delete(String id) {
		Optional<Category> toBeDeleted = categoryJpaRepository.findById(UUID.fromString(id));
		if(toBeDeleted.isPresent()) {
			categoryJpaRepository.delete(toBeDeleted.get());
			return true;
		}
		return false;
	}

	@Override
	public CategoryDTO update(CategoryDTO dto) {
		Optional<Category> managedCategory = categoryJpaRepository.findById(UUID.fromString(dto.getId()));
		if(managedCategory.isPresent()) {
			managedCategory.get().setName(dto.getName());
			managedCategory.get().setDescription(dto.getDescription());
			return CategoryMapper.from(managedCategory.get());
		}
		log.info(String.format("Category with id %s could not be found", dto.getId()));
		throw ServiceException.builder()
				.message(String.format("Category with id %s could not be found", dto.getId()))
				.httpStatus(HttpStatus.NOT_FOUND)
				.code(ApplicationErrorCodes.CATEGORY_NOT_FOUND.getCode())
				.build();
	}
}
