package com.kevin.emazon.infraestructure.adapter;
import com.kevin.emazon.domain.model.Brand;
import com.kevin.emazon.infraestructure.exceptions.BrandException;
import com.kevin.emazon.infraestructure.mapper.IBrandEntityMapper;
import com.kevin.emazon.infraestructure.repositories.BrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class BrandJpaAdapterTest {

    private BrandJpaAdapter brandJpaAdapter;
    private BrandRepository brandRepository;

    @BeforeEach
    void setUp() {
        brandRepository = Mockito.mock(BrandRepository.class);
        IBrandEntityMapper brandEntityMapper = Mockito.mock(IBrandEntityMapper.class);
        brandJpaAdapter = new BrandJpaAdapter(brandRepository, brandEntityMapper);
    }

    @Test
    void saveBrand_ShouldSaveBrand_WhenBrandDoesNotExist() {
        Brand brand = new Brand(null,"Valid Name", "Valid Description");

        when(brandRepository.existsByNameIgnoreCase(brand.getName())).thenReturn(false);

        brandJpaAdapter.saveBrand(brand);

        verify(brandRepository, times(1)).save(any());
    }

    @Test
    void saveBrand_ShouldThrowException_WhenBrandAlreadyExists() {
        Brand brand = new Brand(null,"Acer", "Valid Description");

        when(brandRepository.existsByNameIgnoreCase(brand.getName())).thenReturn(true);

        assertThrows(BrandException.class, () -> brandJpaAdapter.saveBrand(brand));

        verify(brandRepository, never()).save(any());
    }
}
