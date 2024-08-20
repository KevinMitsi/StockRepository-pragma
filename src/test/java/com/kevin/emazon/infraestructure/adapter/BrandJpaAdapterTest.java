package com.kevin.emazon.infraestructure.adapter;
import com.kevin.emazon.domain.model.Brand;
import com.kevin.emazon.infraestructure.entity.BrandEntity;
import com.kevin.emazon.infraestructure.exceptions.BrandException;
import com.kevin.emazon.infraestructure.mapper.IBrandEntityMapper;
import com.kevin.emazon.infraestructure.repositories.BrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class BrandJpaAdapterTest {

    private BrandJpaAdapter brandJpaAdapter;
    private BrandRepository brandRepository;
    private IBrandEntityMapper brandEntityMapper; // Moved outside

    @BeforeEach
    void setUp() {
        brandRepository = Mockito.mock(BrandRepository.class);
        brandEntityMapper = Mockito.mock(IBrandEntityMapper.class);
        brandJpaAdapter = new BrandJpaAdapter(brandRepository, brandEntityMapper);
    }

    @Nested
    class saveBrand {
        @Test
        void saveBrand_ShouldSaveBrand_WhenBrandDoesNotExist() {
            Brand brand = new Brand(null, "Valid Name", "Valid Description");

            when(brandRepository.existsByNameIgnoreCase(brand.getName())).thenReturn(false);

            brandJpaAdapter.saveBrand(brand);

            verify(brandRepository, times(1)).save(any());
        }

        @Test
        void saveBrand_ShouldThrowException_WhenBrandAlreadyExists() {
            Brand brand = new Brand(null, "Acer", "Valid Description");

            when(brandRepository.existsByNameIgnoreCase(brand.getName())).thenReturn(true);

            assertThrows(BrandException.class, () -> brandJpaAdapter.saveBrand(brand));

            verify(brandRepository, never()).save(any());
        }
    }

    @Nested
    class GetBrandsTest {
        Pageable pageable;

        @BeforeEach
        void setUp() {
            pageable = PageRequest.of(0, 10);
        }

        @Test
        void getBrands_ShouldReturnSortedBrandsAscending_WhenOrderIsAsc() {
            // Arrange
            BrandEntity brandEntity1 = new BrandEntity();
            brandEntity1.setName("Apple");
            BrandEntity brandEntity2 = new BrandEntity();
            brandEntity2.setName("Banana");

            Page<BrandEntity> brandEntitiesPage = new PageImpl<>(List.of(brandEntity1, brandEntity2));

            when(brandRepository.findAll(any(Pageable.class))).thenReturn(brandEntitiesPage);
            when(brandEntityMapper.brandEntityToBrand(brandEntity1)).thenReturn(new Brand(null, "Apple", null));
            when(brandEntityMapper.brandEntityToBrand(brandEntity2)).thenReturn(new Brand(null, "Banana", null));

            // Act
            Page<Brand> result = brandJpaAdapter.getAll("asc", pageable);

            // Assert
            assertThat(result.getContent()).hasSize(2);
            assertThat(result.getContent().get(0).getName()).isEqualTo("Apple");
            assertThat(result.getContent().get(1).getName()).isEqualTo("Banana");

            verify(brandRepository, times(1)).findAll(any(Pageable.class));
        }

        @Test
        void getBrands_ShouldReturnSortedBrandsDescending_WhenOrderIsDesc() {
            // Arrange
            BrandEntity brandEntity1 = new BrandEntity();
            brandEntity1.setName("Banana");
            BrandEntity brandEntity2 = new BrandEntity();
            brandEntity2.setName("Apple");

            Page<BrandEntity> brandEntitiesPage = new PageImpl<>(List.of(brandEntity1, brandEntity2));

            when(brandRepository.findAll(any(Pageable.class))).thenReturn(brandEntitiesPage);
            when(brandEntityMapper.brandEntityToBrand(brandEntity1)).thenReturn(new Brand(null, "Banana", null));
            when(brandEntityMapper.brandEntityToBrand(brandEntity2)).thenReturn(new Brand(null, "Apple", null));

            // Act
            Page<Brand> result = brandJpaAdapter.getAll("desc", pageable);

            // Assert
            assertThat(result.getContent()).hasSize(2);
            assertThat(result.getContent().get(0).getName()).isEqualTo("Banana");
            assertThat(result.getContent().get(1).getName()).isEqualTo("Apple");

            verify(brandRepository, times(1)).findAll(any(Pageable.class));
        }

        @Test
        void getBrands_ShouldThrowException_WhenOrderIsInvalid() {
            // Act & Assert
            assertThatThrownBy(() -> brandJpaAdapter.getAll("invalid", pageable))
                    .isInstanceOf(BrandException.class)
                    .hasMessage("metodo de ordenamiento invalido ingrese 'asc' o 'desc'");

            verify(brandRepository, never()).findAll(any(Pageable.class));
        }
    }
}
