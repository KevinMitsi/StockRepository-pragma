package com.kevin.emazon.domain.usecase;

import com.kevin.emazon.domain.model.Brand;
import com.kevin.emazon.domain.spi.IBrandPersistentPort;

import com.kevin.emazon.infraestructure.entity.BrandEntity;
import com.kevin.emazon.infraestructure.exceptions.BrandException;
import com.kevin.emazon.infraestructure.exceptions.InvalidOrderingMethodException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BrandUseCaseTest {
    @Mock
    private IBrandPersistentPort brandPersistentPort;

    @InjectMocks
    private BrandUseCase brandUseCase;


    @Nested
    class SaveBrandTest {
        @Test
        void saveBrand_ShouldSaveBrand_WhenBrandDoesNotExist() {
            // Arrange
            Brand brand = new Brand(null, "Valid Name", "Valid Description");

            when(brandPersistentPort.existByNameIgnoreCase(brand.getName())).thenReturn(false);

            // Act
            brandUseCase.saveBrand(brand);

            // Assert
            verify(brandPersistentPort, times(1)).saveBrand(brand);
        }

        @Test
        void saveBrand_ShouldThrowException_WhenBrandAlreadyExists() {
            // Arrange
            Brand brand = new Brand(null, "Iphone", "Valid Description");

            when(brandPersistentPort.existByNameIgnoreCase(brand.getName())).thenReturn(true);

            // Act & Assert
            assertThatThrownBy(() -> brandUseCase.saveBrand(brand))
                    .isInstanceOf(BrandException.class)
                    .hasMessage("Esta marca ya existe");

            verify(brandPersistentPort, never()).saveBrand(any());
        }
    }

    @Nested
    class GetBrandsTest {
        @Test
        void getBrands_ShouldReturnSortedBrandsAscending_WhenOrderIsAsc() {
            // Arrange
            BrandEntity brandEntity1 = new BrandEntity();
            brandEntity1.setName("Apple");
            BrandEntity brandEntity2 = new BrandEntity();
            brandEntity2.setName("Banana");

            when(brandPersistentPort.getAll("asc")).thenReturn(List.of(new Brand(null, "Apple", null), new Brand(null, "Banana", null)));

            // Act
            List<Brand> result = brandUseCase.getAll("asc");

            // Assert
            assertThat(result).hasSize(2);
            assertThat(result.get(0).getName()).isEqualTo("Apple");
            assertThat(result.get(1).getName()).isEqualTo("Banana");

            verify(brandPersistentPort, times(1)).getAll("asc");
        }

        @Test
        void getBrands_ShouldReturnSortedBrandsDescending_WhenOrderIsDesc() {
            // Arrange
            BrandEntity brandEntity1 = new BrandEntity();
            brandEntity1.setName("Banana");
            BrandEntity brandEntity2 = new BrandEntity();
            brandEntity2.setName("Apple");



            when(brandPersistentPort.getAll("desc")).thenReturn(List.of(new Brand(null, "Banana", null), new Brand(null, "Apple", null)));

            // Act
            List<Brand> result = brandUseCase.getAll("desc");

            // Assert
            assertThat(result).hasSize(2);
            assertThat(result.get(0).getName()).isEqualTo("Banana");
            assertThat(result.get(1).getName()).isEqualTo("Apple");

            verify(brandPersistentPort, times(1)).getAll("desc");
        }

        @Test
        void getBrands_ShouldThrowException_WhenOrderIsInvalid() {
            // Act & Assert
            assertThatThrownBy(() -> brandUseCase.getAll("invalid"))
                    .isInstanceOf(InvalidOrderingMethodException.class)
                    .hasMessage("Elija un ordenamiento valido: 'ASC' o 'DESC'");

            verify(brandPersistentPort, never()).getAll(any());
        }
    }
}