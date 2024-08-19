package com.kevin.emazon.domain.usecase;
import com.kevin.emazon.domain.model.Brand;
import com.kevin.emazon.domain.spi.IBrandPersistentPort;
import com.kevin.emazon.infraestructure.exceptions.BrandException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class BrandUseCaseTest {

    private BrandUseCase brandUseCase;
    private IBrandPersistentPort persistentPort;

    @BeforeEach
    void setUp() {
        persistentPort = Mockito.mock(IBrandPersistentPort.class);
        brandUseCase = new BrandUseCase(persistentPort);
    }

    static Stream<Arguments> provideInvalidBrands() {
        return Stream.of(
                Arguments.of(new Brand(null,"", "Valid Description")), // Nombre vacío
                Arguments.of(new Brand(null,"Valid Name", "")), // Descripción vacía
                Arguments.of(new Brand(null,"This name is way too long and exceeds fifty characterssssssssssssssss", "Valid Description")), // Nombre demasiado largo
                Arguments.of(new Brand(null,"Valid Name", "This description is way too long and exceeds ninety characters, making it invalidddddddddddddddddddd.")) // Descripción demasiado larga
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidBrands")
    void saveBrand_ShouldThrowException_WhenBrandIsInvalid(Brand brand) {
        assertThrows(BrandException.class, () -> brandUseCase.saveBrand(brand));
        verify(persistentPort, never()).saveBrand(any(Brand.class));
    }

    @ParameterizedTest
    @MethodSource("provideValidBrands")
    void saveBrand_ShouldSaveBrand_WhenBrandIsValid(Brand brand) {
        brandUseCase.saveBrand(brand);
        verify(persistentPort, times(1)).saveBrand(brand);
    }

    static Stream<Arguments> provideValidBrands() {
        return Stream.of(
                Arguments.of(new Brand(null,"Valid Name", "Valid Description")) // Marca válida
        );
    }
}