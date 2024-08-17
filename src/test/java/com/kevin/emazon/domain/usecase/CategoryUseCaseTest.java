package com.kevin.emazon.domain.usecase;

import com.kevin.emazon.domain.model.Category;
import com.kevin.emazon.domain.spi.ICategoryPersistentPort;
import com.kevin.emazon.infraestructure.exceptions.CategoryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class CategoryUseCaseTest {

    @Mock
    private ICategoryPersistentPort categoryPersistentPort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveCategory_ShouldThrowException_WhenCategoryIsInvalid() {
        // Arrange
        Category invalidCategory = new Category();
        invalidCategory.setName("ThisNameIsWayTooLongToBeValidSinceItExceedsFiftyCharactersLimit");
        invalidCategory.setDescription("This description is also way too long to be valid and it exceeds the ninety characters limit.");

        // Act & Assert
        assertThatThrownBy(() -> categoryUseCase.saveCategory(invalidCategory))
                .isInstanceOf(CategoryException.class)
                .hasMessage("El nombre o la descripción supera el total de caracteres. Nombre(Max 50) Descripcion(Max 90)");

        verify(categoryPersistentPort, never()).saveCategory(any(Category.class));
    }

    @Test
    void saveCategory_ShouldSaveCategory_WhenCategoryIsValid() {
        // Arrange
        Category validCategory = new Category();
        validCategory.setName("ValidName");
        validCategory.setDescription("Valid description");

        // Act
        categoryUseCase.saveCategory(validCategory);

        // Assert
        verify(categoryPersistentPort, times(1)).saveCategory(validCategory);
    }
}
