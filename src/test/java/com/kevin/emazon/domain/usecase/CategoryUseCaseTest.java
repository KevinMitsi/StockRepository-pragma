package com.kevin.emazon.domain.usecase;

import com.kevin.emazon.domain.model.Category;
import com.kevin.emazon.domain.spi.ICategoryPersistentPort;
import com.kevin.emazon.infraestructure.exceptions.CategoryException;
import com.kevin.emazon.infraestructure.exceptions.InvalidOrderingMethodException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryUseCaseTest {
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int DEFAULT_PAGE_NUMBER = 1;
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

    @Nested
    class GetCategoriesTest {
        @Test
        void getCategories_ShouldThrowException_WhenOrderIsInvalid() {
            // Act & Assert
            assertThatThrownBy(() -> categoryUseCase.getCategories("invalid",DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE))
                    .isInstanceOf(InvalidOrderingMethodException.class)
                    .hasMessage("Elija un ordenamiento valido: 'ASC' o 'DESC'");

            verify(categoryPersistentPort, never()).getCategories(any(), eq(DEFAULT_PAGE_NUMBER), eq(DEFAULT_PAGE_SIZE));
        }

        @Test
        void getCategories_ShouldReturnCategories_WhenOrderIsAsc() {
            // Arrange
            String order = "asc";
            List<Category> expectedCategories = List.of(new Category(null, "Apple", null), new Category(null, "Banana", null));

            when(categoryPersistentPort.getCategories(order, DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE)).thenReturn(expectedCategories);

            // Act
            List<Category> result = categoryUseCase.getCategories(order,DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE);

            // Assert
            assertThat(result).hasSize(2);
            assertThat(result.get(0).getName()).isEqualTo("Apple");
            assertThat(result.get(1).getName()).isEqualTo("Banana");

            verify(categoryPersistentPort, times(1)).getCategories(order,DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE);
        }

        @Test
        void getCategories_ShouldReturnCategories_WhenOrderIsDesc() {
            // Arrange
            String order = "desc";
            List<Category> expectedCategories = List.of(new Category(null, "Banana", null), new Category(null, "Apple", null));

            when(categoryPersistentPort.getCategories(order,DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE)).thenReturn(expectedCategories);

            // Act
            List<Category> result = categoryUseCase.getCategories(order,DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE);

            // Assert
            assertThat(result).hasSize(2);
            assertThat(result.get(0).getName()).isEqualTo("Banana");
            assertThat(result.get(1).getName()).isEqualTo("Apple");

            verify(categoryPersistentPort, times(1)).getCategories(order,DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE);
        }
    }
}