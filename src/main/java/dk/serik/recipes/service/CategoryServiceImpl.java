package dk.serik.recipes.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import dk.serik.recipes.entity.CategoryEntity;
import dk.serik.recipes.repository.CategoryJpaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
	
	private CategoryJpaRepository categoryJpaRepository;

	@Override
	public Optional<List<CategoryEntity>> findAll() {
		List<CategoryEntity> all = categoryJpaRepository.findAll();
		if(all.size() == 0) {
			return Optional.empty();
		}
		
		return Optional.of(all);
	}

	@Override
	public Optional<CategoryEntity> findById(String id) {
		return categoryJpaRepository.findById(id);
	}

	@Override
	public Optional<CategoryEntity> findByName(String name) {
		return categoryJpaRepository.findByName(name);
	}


}
