package com.kevin.emazon.domain.usecase;

import com.kevin.emazon.domain.model.Brand;
import com.kevin.emazon.domain.model.Category;
import com.kevin.emazon.domain.model.Item;
import com.kevin.emazon.domain.spi.IBrandPersistentPort;
import com.kevin.emazon.domain.spi.ICategoryPersistentPort;
import com.kevin.emazon.domain.spi.IItemCategoryPersistentPort;
import com.kevin.emazon.domain.spi.IItemPersistentPort;
import com.kevin.emazon.infraestructure.exceptions.BrandException;
import com.kevin.emazon.infraestructure.exceptions.CategoryException;
import com.kevin.emazon.infraestructure.exceptions.ItemException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ItemUseCaseTest {

    private ItemUseCase itemUseCase;
    private IItemPersistentPort itemPersistentPort;
    private ICategoryPersistentPort categoryPersistentPort;
    private IBrandPersistentPort brandPersistentPort;
    private IItemCategoryPersistentPort itemCategoryPersistentPort;

    @BeforeEach
    void setUp() {
        itemPersistentPort = Mockito.mock(IItemPersistentPort.class);
        categoryPersistentPort = Mockito.mock(ICategoryPersistentPort.class);
        brandPersistentPort = Mockito.mock(IBrandPersistentPort.class);
        itemCategoryPersistentPort = Mockito.mock(IItemCategoryPersistentPort.class);
        itemUseCase = new ItemUseCase(itemPersistentPort, categoryPersistentPort, brandPersistentPort, itemCategoryPersistentPort);
    }

    @Nested
    class SaveItemTests {

        @Test
        void saveItem_ShouldSaveItem_WhenAllValidationsPass() {
            // Arrange
            Brand brand = new Brand(1L, "Valid Brand", "asdassd");
            Category category1 = new Category(1L, "Valid Category 1", "asdassd");
            Category category2 = new Category(2L, "Valid Category 2", "asdassd");
            Item item = new Item(null, "Valid Item", 100D, 1L, brand);
            item.setCategories(List.of(category1, category2));

            when(brandPersistentPort.findByName(brand.getName())).thenReturn(Optional.of(brand));
            when(categoryPersistentPort.findByName(category1.getName())).thenReturn(Optional.of(category1));
            when(categoryPersistentPort.findByName(category2.getName())).thenReturn(Optional.of(category2));
            when(itemPersistentPort.saveItem(any(Item.class))).thenReturn(item);

            // Act
            itemUseCase.saveItem(item);

            // Assert
            verify(itemPersistentPort, times(1)).saveItem(any(Item.class));
            verify(itemCategoryPersistentPort, times(2)).saveItemCategory(any());
        }

        @Test
        void saveItem_ShouldThrowBrandException_WhenBrandDoesNotExist() {
            // Arrange
            Brand brand = new Brand(1L, "Invalid Brand", "asdasdsasd");
            Item item = new Item(null, "Valid Item", 100D, 1L, brand);

            when(brandPersistentPort.findByName(brand.getName())).thenReturn(Optional.empty());

            // Act & Assert
            assertThrows(BrandException.class, () -> itemUseCase.saveItem(item));
            verify(itemPersistentPort, never()).saveItem(any());
        }

        @Test
        void saveItem_ShouldThrowCategoryException_WhenCategoryDoesNotExist() {
            // Arrange
            Brand brand = new Brand(1L, "Valid Brand", "asdasda");
            Category category = new Category(1L, "Invalid Category", "asdasd");
            Item item = new Item(null, "Valid Item", 100D, 1L, brand);
            item.setCategories(List.of(category));


            when(brandPersistentPort.findByName(brand.getName())).thenReturn(Optional.of(brand));
            when(categoryPersistentPort.findByName(category.getName())).thenReturn(Optional.empty());

            // Act & Assert
            assertThrows(CategoryException.class, () -> itemUseCase.saveItem(item));
            verify(itemPersistentPort, never()).saveItem(any());
        }

        @Test
        void saveItem_ShouldThrowItemException_WhenCategoriesAreRepeated() {
            // Arrange
            Brand brand = new Brand(1L, "Valid Brand", "asdasd");
            Category category = new Category(1L, "Valid Category", "asdasd");
            Item item = new Item(null, "Valid Item", 100D, 1L, brand);
            item.setCategories(List.of(category, category));

            when(brandPersistentPort.findByName(brand.getName())).thenReturn(Optional.of(brand));
            when(categoryPersistentPort.findByName(category.getName())).thenReturn(Optional.of(category));

            // Act & Assert
            assertThrows(ItemException.class, () -> itemUseCase.saveItem(item));
            verify(itemPersistentPort, never()).saveItem(any());
        }

        @Test
        void saveItem_ShouldThrowItemException_WhenCategoryListIsEmpty() {
            // Arrange
            Brand brand = new Brand(1L, "Valid Brand", "asdasdasd");
            Item item = new Item(null, "Valid Item", 100D, 1L, brand);

            when(brandPersistentPort.findByName(brand.getName())).thenReturn(Optional.of(brand));

            // Act & Assert
            assertThrows(ItemException.class, () -> itemUseCase.saveItem(item));
            verify(itemPersistentPort, never()).saveItem(any());
        }

        @Test
        void saveItem_ShouldThrowItemException_WhenCategoryListExceedsThreeCategories() {
            // Arrange
            Brand brand = new Brand(1L, "Valid Brand", "asdasd");
            Category category1 = new Category(1L, "Category 1", "adas");
            Category category2 = new Category(2L, "Category 2", "asdas");
            Category category3 = new Category(3L, "Category 3", "asdas");
            Category category4 = new Category(4L, "Category 4", "asdasd");
            Item item = new Item(null, "Valid Item", 100D, 1L, brand);
            item.setCategories(List.of(category1,category2,category3,category4));

            when(brandPersistentPort.findByName(brand.getName())).thenReturn(Optional.of(brand));
            when(categoryPersistentPort.findByName(category1.getName())).thenReturn(Optional.of(category1));
            when(categoryPersistentPort.findByName(category2.getName())).thenReturn(Optional.of(category2));
            when(categoryPersistentPort.findByName(category3.getName())).thenReturn(Optional.of(category3));
            when(categoryPersistentPort.findByName(category4.getName())).thenReturn(Optional.of(category4));

            // Act & Assert
            assertThrows(ItemException.class, () -> itemUseCase.saveItem(item));
            verify(itemPersistentPort, never()).saveItem(any());
        }
    }
}
