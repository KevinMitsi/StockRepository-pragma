package com.kevin.emazon.infraestructure.adapter;

import com.kevin.emazon.domain.model.Category;
import com.kevin.emazon.infraestructure.entity.CategoryEntity;
import com.kevin.emazon.infraestructure.exceptions.CategoryException;
import com.kevin.emazon.infraestructure.mapper.ICategoryEntityMapper;
import com.kevin.emazon.infraestructure.repositories.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryJpaAdapterTest {

    @Mock
    private ICategoryEntityMapper categoryEntityMapper;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryJpaAdapter categoryJpaAdapter;

    @Test
    void saveCategory_ShouldThrowException_WhenCategoryAlreadyExists() {
        // Arrange
        Category category = new Category();
        category.setName("Piza");

        when(categoryRepository.existsByNameIgnoreCase(category.getName())).thenReturn(true);

        // Act & Assert
        assertThatThrownBy(() -> categoryJpaAdapter.saveCategory(category))
                .isInstanceOf(CategoryException.class)
                .hasMessage("Categoría ya creada");

        verify(categoryRepository, never()).save(any());
    }

    @Test
    void saveCategory_ShouldSaveCategory_WhenCategoryDoesNotExist() {
        // Arrange
        Category category = new Category();
        category.setName("NewCategoryName");

        when(categoryRepository.existsByNameIgnoreCase(category.getName())).thenReturn(false);
        when(categoryEntityMapper.categoryToCategoryEntity(category)).thenReturn(new CategoryEntity());

        // Act
        categoryJpaAdapter.saveCategory(category);

        // Assert
        verify(categoryRepository, times(1)).save(any(CategoryEntity.class));
    }
}
