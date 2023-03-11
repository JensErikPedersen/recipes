package dk.serik.recipes.service;

import dk.serik.recipes.dto.CategoryDTO;
import dk.serik.recipes.mapper.CategoryMapper;
import dk.serik.recipes.model.Category;
import dk.serik.recipes.repository.CategoryJpaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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

	private CategoryMapper categoryMapper;

	@Override
	public Optional<List<CategoryDTO>> findAll() {
		List<Category> all = categoryJpaRepository.findAll();
		if(all.size() == 0) {
			return Optional.empty();
		}
		
		List<CategoryDTO> dtos = all.stream()
				.map(e -> categoryMapper.category(e))
				.collect(Collectors.toList());
		
		return Optional.of(dtos);
	}

	@Override
	public Optional<CategoryDTO> findById(String id) {
		Optional<Category> categoryEntity = categoryJpaRepository.findById(id);
		if(categoryEntity.isPresent()) {
			return Optional.ofNullable(categoryMapper.category(categoryEntity.get()));
		}
		
		return Optional.empty();
	}

	@Override
	public Optional<CategoryDTO> findByName(String name) {
		Optional<Category> categoryEntity =  categoryJpaRepository.findByName(name);
		
		if(categoryEntity.isPresent()) {
			return Optional.ofNullable(categoryMapper.category(categoryEntity.get()));
		}
		
		return Optional.empty();
	}

	@Override
	public CategoryDTO save(CategoryDTO categoryDto) {
		Category from = categoryMapper.categoryDTO(categoryDto);
		log.info("Mapped entity: {}", from);
		Category managedEntity = categoryJpaRepository.saveAndFlush(from);
		return categoryMapper.category(managedEntity);
	}

	@Override
	public void delete(String id) {

	}

	@Override
	public CategoryDTO update(CategoryDTO dto) {
		return null;
	}


}
