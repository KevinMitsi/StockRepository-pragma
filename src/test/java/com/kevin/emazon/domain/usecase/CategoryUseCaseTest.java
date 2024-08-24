package com.kevin.emazon.domain.usecase;

import com.kevin.emazon.domain.model.Category;
import com.kevin.emazon.domain.spi.ICategoryPersistentPort;
import com.kevin.emazon.infraestructure.exceptions.CategoryException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryUseCaseTest {
    @Mock
    private ICategoryPersistentPort categoryPersistentPort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @Nested
    class SaveCategoryTest {
        @Test
        void saveCategory_ShouldThrowException_WhenCategoryAlreadyExists() {
            // Arrange
            Category category = new Category();
            category.setName("Libro");

            when(categoryPersistentPort.existByNameIgnoreCase(category.getName())).thenReturn(true);

            // Act & Assert
            assertThatThrownBy(() -> categoryUseCase.saveCategory(category))
                    .isInstanceOf(CategoryException.class)
                    .hasMessage("Categoría ya creada");

            verify(categoryPersistentPort, never()).saveCategory(any());
        }

        @Test
        void saveCategory_ShouldSaveCategory_WhenCategoryDoesNotExist() {
            // Arrange
            Category category = new Category();
            category.setName("NewCategoryName");

            when(categoryPersistentPort.existByNameIgnoreCase(category.getName())).thenReturn(false);

            // Act
            categoryUseCase.saveCategory(category);

            // Assert
            verify(categoryPersistentPort, times(1)).saveCategory(category);
        }
    }
}