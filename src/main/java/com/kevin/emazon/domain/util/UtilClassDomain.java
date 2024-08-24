package com.kevin.emazon.domain.util;

import com.kevin.emazon.infraestructure.exceptions.InvalidOrderingMethodException;

public class UtilClassDomain {
    private UtilClassDomain() {}

    public static void validateOrderingMethod(String order) {
        if (!order.equalsIgnoreCase("asc") && !order.equalsIgnoreCase("desc")) {
            throw new InvalidOrderingMethodException("Elija un ordenamiento valido: 'ASC' o 'DESC'");
        }
    }
}
