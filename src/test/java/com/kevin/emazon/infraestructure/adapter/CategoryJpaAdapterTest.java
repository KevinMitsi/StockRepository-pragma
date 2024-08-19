package com.kevin.emazon.infraestructure.adapter;

import com.kevin.emazon.domain.model.Category;
import com.kevin.emazon.infraestructure.entity.CategoryEntity;
import com.kevin.emazon.infraestructure.exceptions.CategoryException;
import com.kevin.emazon.infraestructure.mapper.ICategoryEntityMapper;
import com.kevin.emazon.infraestructure.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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

    @Nested
    class SaveCategoryTest{
        @Test
        void saveCategory_ShouldThrowException_WhenCategoryAlreadyExists() {
            // Arrange
            Category category = new Category();
            category.setName("Libro");

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
            when(categoryEntityMapper.toCategoryEntity(category)).thenReturn(new CategoryEntity());

            // Act
            categoryJpaAdapter.saveCategory(category);

            // Assert
            verify(categoryRepository, times(1)).save(any(CategoryEntity.class));
        }
    }

    @Nested
    class GetCategoriesTest{
        Pageable pageable;
        @BeforeEach
        void setUp(){
            pageable = PageRequest.of(0,10);
        }

        @Test
        void getCategories_ShouldReturnSortedCategoriesAscending_WhenOrderIsAsc() {
            // Arrange
            CategoryEntity categoryEntity1 = new CategoryEntity();
            categoryEntity1.setName("Apple");
            CategoryEntity categoryEntity2 = new CategoryEntity();
            categoryEntity2.setName("Banana");

            Page<CategoryEntity> categoryEntitiesPage = new PageImpl<>(List.of(categoryEntity1, categoryEntity2));

            when(categoryRepository.findAll(any(Pageable.class))).thenReturn(categoryEntitiesPage);
            when(categoryEntityMapper.toCategory(categoryEntity1)).thenReturn(new Category(null,"Apple", null));
            when(categoryEntityMapper.toCategory(categoryEntity2)).thenReturn(new Category(null,"Banana", null));

            // Act
            Page<Category> result = categoryJpaAdapter.getCategories("asc", pageable);

            // Assert
            assertThat(result.getContent()).hasSize(2);
            assertThat(result.getContent().get(0).getName()).isEqualTo("Apple");
            assertThat(result.getContent().get(1).getName()).isEqualTo("Banana");

            verify(categoryRepository, times(1)).findAll(any(Pageable.class));
        }

        @Test
        void getCategories_ShouldReturnSortedCategoriesDescending_WhenOrderIsDesc() {
            // Arrange
            CategoryEntity categoryEntity1 = new CategoryEntity();
            categoryEntity1.setName("Banana");
            CategoryEntity categoryEntity2 = new CategoryEntity();
            categoryEntity2.setName("Apple");

            Page<CategoryEntity> categoryEntitiesPage = new PageImpl<>(List.of(categoryEntity1, categoryEntity2));

            when(categoryRepository.findAll(any(Pageable.class))).thenReturn(categoryEntitiesPage);
            when(categoryEntityMapper.toCategory(categoryEntity1)).thenReturn(new Category(null,"Banana", null));
            when(categoryEntityMapper.toCategory(categoryEntity2)).thenReturn(new Category(null,"Apple", null));

            // Act
            Page<Category> result = categoryJpaAdapter.getCategories("desc", pageable);

            // Assert
            assertThat(result.getContent()).hasSize(2);
            assertThat(result.getContent().get(0).getName()).isEqualTo("Banana");
            assertThat(result.getContent().get(1).getName()).isEqualTo("Apple");

            verify(categoryRepository, times(1)).findAll(any(Pageable.class));
        }

        @Test
        void getCategories_ShouldThrowException_WhenOrderIsInvalid() {
            // Act & Assert
            assertThatThrownBy(() -> categoryJpaAdapter.getCategories("invalid", pageable))
                    .isInstanceOf(CategoryException.class)
                    .hasMessage("metodo de ordenamiento invalido ingrese 'asc' o 'desc'");

            verify(categoryRepository, never()).findAll(any(Pageable.class));
        }
    }

}

