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

import static com.kevin.emazon.domain.usecase.ItemUseCase.*;
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

    @Test
    void geItemsInUserCart_ShouldThrowIllegalArgumentException_WhenItemIdsIsEmpty() {
        // Arrange
        List<Long> emptyItemIds = List.of();

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                itemUseCase.geItemsInUserCart(emptyItemIds, null, null));
        assertEquals(EMPTY_ID_LIST_MESSAGE, exception.getMessage());
    }

    @Test
    void geItemsInUserCart_ShouldReturnItems_WhenCategoryAndBrandAreNull() {
        // Arrange
        List<Long> itemIds = List.of(1L, 2L, 3L);
        List<Item> expectedItems = List.of(new Item(), new Item(), new Item());

        when(itemPersistentPort.getItemsByIds(itemIds)).thenReturn(expectedItems);

        // Act
        List<Item> result = itemUseCase.geItemsInUserCart(itemIds, null, null);

        // Assert
        assertEquals(expectedItems.size(), result.size());
        verify(itemPersistentPort, times(1)).getItemsByIds(itemIds);
    }

    @Test
    void geItemsInUserCart_ShouldReturnItems_WhenCategoryIsNotNullAndBrandIsNull() {
        // Arrange
        List<Long> itemIds = List.of(1L, 2L, 3L);
        Long categoryToOrder = 10L;
        List<Item> expectedItems = List.of(new Item(), new Item());

        when(itemPersistentPort.findByCategoryIdAndItemIds(categoryToOrder, itemIds)).thenReturn(expectedItems);

        // Act
        List<Item> result = itemUseCase.geItemsInUserCart(itemIds, categoryToOrder, null);

        // Assert
        assertEquals(expectedItems.size(), result.size());
        verify(itemPersistentPort, times(1)).findByCategoryIdAndItemIds(categoryToOrder, itemIds);
    }

    @Test
    void geItemsInUserCart_ShouldReturnItems_WhenCategoryIsNullAndBrandIsNotNull() {
        // Arrange
        List<Long> itemIds = List.of(1L, 2L, 3L);
        Long brandToOrder = 20L;
        List<Item> expectedItems = List.of(new Item(), new Item(), new Item());

        when(itemPersistentPort.findByBrandIdAndItemIds(brandToOrder, itemIds)).thenReturn(expectedItems);

        // Act
        List<Item> result = itemUseCase.geItemsInUserCart(itemIds, null, brandToOrder);

        // Assert
        assertEquals(expectedItems.size(), result.size());
        verify(itemPersistentPort, times(1)).findByBrandIdAndItemIds(brandToOrder, itemIds);
    }

    @Test
    void geItemsInUserCart_ShouldReturnItems_WhenCategoryAndBrandAreNotNull() {
        // Arrange
        List<Long> itemIds = List.of(1L, 2L, 3L);
        Long categoryToOrder = 10L;
        Long brandToOrder = 20L;
        List<Item> expectedItems = List.of(new Item());

        when(itemPersistentPort.findByIdAndBrandIdAndItemIds(categoryToOrder, brandToOrder, itemIds))
                .thenReturn(expectedItems);

        // Act
        List<Item> result = itemUseCase.geItemsInUserCart(itemIds, categoryToOrder, brandToOrder);

        // Assert
        assertEquals(expectedItems.size(), result.size());
        verify(itemPersistentPort, times(1))
                .findByIdAndBrandIdAndItemIds(categoryToOrder, brandToOrder, itemIds);
    }

    @Test
    void geItemsInUserCart_ShouldThrowIllegalArgumentException_WhenItemIdsIsNull() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                itemUseCase.geItemsInUserCart(null, null, null));
        assertEquals(EMPTY_ID_LIST_MESSAGE, exception.getMessage());
    }


    @Test
    void reduceStock_ShouldReduceStock_WhenConditionsAreMet() {
        // Arrange
        Long existingItemId = 1L;
        Long quantity = 5L;

        when(itemPersistentPort.existById(existingItemId)).thenReturn(true);
        when(itemPersistentPort.isEnoughInStock(existingItemId, quantity)).thenReturn(true);

        // Act
        itemUseCase.reduceStock(existingItemId, quantity);

        // Assert
        verify(itemPersistentPort, times(1)).existById(existingItemId);
        verify(itemPersistentPort, times(1)).isEnoughInStock(existingItemId, quantity);
        verify(itemPersistentPort, times(1)).reduceStock(existingItemId, quantity);
    }


    @Test
    void reduceStock_ShouldThrowException_WhenNotEnoughStock() {
        // Arrange
        Long existingItemId = 1L;
        Long quantity = 10L;

        when(itemPersistentPort.existById(existingItemId)).thenReturn(true);
        when(itemPersistentPort.isEnoughInStock(existingItemId, quantity)).thenReturn(false);

        // Act & Assert
        ItemException exception = assertThrows(ItemException.class,
                () -> itemUseCase.reduceStock(existingItemId, quantity));

        assertEquals(NO_HAY_SUFICIENTES_ITEMS_PARA_COMPRAR, exception.getMessage());
        verify(itemPersistentPort, times(1)).existById(existingItemId);
        verify(itemPersistentPort, times(1)).isEnoughInStock(existingItemId, quantity);
        verify(itemPersistentPort, never()).reduceStock(anyLong(), anyLong());
    }

    @Test
    void reduceStock_ShouldThrowException_WhenItemDoesNotExist() {
        // Arrange
        Long nonExistingItemId = 1L;
        Long quantity = 1L;

        when(itemPersistentPort.existById(nonExistingItemId)).thenReturn(false);

        // Act & Assert
        ItemException exception = assertThrows(ItemException.class,
                () -> itemUseCase.reduceStock(nonExistingItemId, quantity));

        assertEquals(REDUCE_STOCK_ITEM_EXCEPTION, exception.getMessage());
        verify(itemPersistentPort, never()).isEnoughInStock(anyLong(), anyLong());
        verify(itemPersistentPort, never()).reduceStock(anyLong(), anyLong());
    }

}
