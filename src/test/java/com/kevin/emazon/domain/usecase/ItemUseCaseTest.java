package com.kevin.emazon.domain.usecase;

import com.kevin.emazon.domain.model.Brand;
import com.kevin.emazon.domain.model.Category;
import com.kevin.emazon.domain.model.Item;
import com.kevin.emazon.domain.spi.IBrandPersistentPort;
import com.kevin.emazon.domain.spi.ICategoryPersistentPort;
import com.kevin.emazon.domain.spi.IItemCategoryPersistentPort;
import com.kevin.emazon.domain.spi.IItemPersistentPort;
import com.kevin.emazon.infraestructure.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
        void saveItem_ShouldSaveItem_WhenValidationsPass() {
            // Arrange
            Brand brand = new Brand(1L, "Valid Brand", "description");
            Category category1 = new Category(1L, "Category 1", "description");
            Category category2 = new Category(2L, "Category 2", "description");
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
            Brand brand = new Brand(1L, "Invalid Brand", "description");
            Item item = new Item(null, "Valid Item", 100D, 1L, brand);

            when(brandPersistentPort.findByName(brand.getName())).thenReturn(Optional.empty());

            // Act & Assert
            assertThrows(BrandException.class, () -> itemUseCase.saveItem(item));
            verify(itemPersistentPort, never()).saveItem(any());
        }

        @Test
        void saveItem_ShouldThrowCategoryException_WhenCategoryDoesNotExist() {
            // Arrange
            Brand brand = new Brand(1L, "Valid Brand", "description");
            Category category = new Category(1L, "Invalid Category", "description");
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
            Brand brand = new Brand(1L, "Valid Brand", "description");
            Category category = new Category(1L, "Valid Category", "description");
            Item item = new Item(null, "Valid Item", 100D, 1L, brand);
            item.setCategories(List.of(category, category));

            when(brandPersistentPort.findByName(brand.getName())).thenReturn(Optional.of(brand));
            when(categoryPersistentPort.findByName(category.getName())).thenReturn(Optional.of(category));

            // Act & Assert
            assertThrows(ItemException.class, () -> itemUseCase.saveItem(item));
            verify(itemPersistentPort, never()).saveItem(any());
        }

        @Test
        void saveItem_ShouldThrowItemException_WhenMoreThanThreeCategoriesAreAssigned() {
            // Arrange
            Brand brand = new Brand(1L, "Valid Brand", "description");
            Category category1 = new Category(1L, "Category 1", "description");
            Category category2 = new Category(2L, "Category 2", "description");
            Category category3 = new Category(3L, "Category 3", "description");
            Category category4 = new Category(4L, "Category 4", "description");
            Item item = new Item(null, "Valid Item", 100D, 1L, brand);
            item.setCategories(List.of(category1, category2, category3, category4));

            when(brandPersistentPort.findByName(brand.getName())).thenReturn(Optional.of(brand));
            when(categoryPersistentPort.findByName(anyString())).thenReturn(Optional.of(category1));

            // Act & Assert
            assertThrows(ItemException.class, () -> itemUseCase.saveItem(item));
            verify(itemPersistentPort, never()).saveItem(any());
        }
    }

    @Test
    void updateStock_ShouldUpdateStock_WhenQuantityIsPositive() {
        // Arrange
        Long itemId = 1L;
        Long newStock = 20L;
        // Configura el mock para que devuelva el ítem cuando se busque por itemId
        when(itemPersistentPort.existById(itemId)).thenReturn(true);

        // Act
        itemUseCase.updateStockItem(itemId, newStock);

        // Assert
        verify(itemPersistentPort, times(1)).updateItemStock(itemId, newStock);
    }


    @Test
    void updateStock_ShouldThrowItemException_WhenQuantityIsNegative() {
        // Arrange
        Long itemId = 1L;
        Long newStock = -5L;

        // Act & Assert
        assertThrows(ItemException.class, () -> itemUseCase.updateStockItem(itemId, newStock));
        verify(itemPersistentPort, never()).updateItemStock(anyLong(), anyLong());
    }
}
