package dk.serik.recipes.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dk.serik.recipes.dto.CategoryDTO;
import dk.serik.recipes.model.CategoryEntity;
import dk.serik.recipes.mapper.CategoryMapper;
import dk.serik.recipes.repository.CategoryJpaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional(
		  isolation = Isolation.READ_COMMITTED,
		  propagation = Propagation.REQUIRED, 
		  readOnly = false, 
		  timeout = 5)
public class CategoryServiceImpl implements CategoryService {
	
	private CategoryJpaRepository categoryJpaRepository;
	
	private CategoryMapper categoryMapper;

	@Override
	public Optional<List<CategoryDTO>> findAll() {
		List<CategoryEntity> all = categoryJpaRepository.findAll();
		if(all.size() == 0) {
			return Optional.empty();
		}
		
		List<CategoryDTO> dtos = all.stream()
				.map(e -> categoryMapper.from(e))
				.collect(Collectors.toList());
		
		return Optional.of(dtos);
	}

	@Override
	public Optional<CategoryDTO> findById(String id) {
		Optional<CategoryEntity> categoryEntity = categoryJpaRepository.findById(id);
		if(categoryEntity.isPresent()) {
			return Optional.ofNullable(categoryMapper.from(categoryEntity.get()));
		}
		
		return Optional.empty();
	}

	@Override
	public Optional<CategoryDTO> findByName(String name) {
		Optional<CategoryEntity> categoryEntity =  categoryJpaRepository.findByName(name);
		
		if(categoryEntity.isPresent()) {
			return Optional.ofNullable(categoryMapper.from(categoryEntity.get()));
		}
		
		return Optional.empty();
	}

	@Override
	public CategoryDTO save(CategoryDTO categoryDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) {

	}

	@Override
	public CategoryDTO update(CategoryDTO dto) {
		return null;
	}


}
