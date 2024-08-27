package com.kevin.emazon.application.util;

public class ConstantUtilClass {
    private static final String NAME_NOTNULL = "El nombre no debe ser nulo";
    private static final String NAME_NOTBLANK = "El nombre no debe estar vacío ni lleno de espacios";
    private static final String SIZE_NAME = "El nombre debe estar entre los 3 y los 50 caracteres";

    private static final String DESCRIPTION_NOTNULL = "La descripción no debe ser nula";
    private static final String DESCRIPTION_NOTBLANK = "La descripción no debe estar llena de espacios";
    private static final String SIZE_DESCRIPTION = "La descripción debe estar entre los 5 y los 90 caracteres";
    private ConstantUtilClass(){}
    public static class CategoryErrorMsg {
        private CategoryErrorMsg(){}
        public static final String NN_NAME = "NullCategoryNameException: "+ NAME_NOTNULL;
        public static final String NB_NAME = "BlankCategoryNameException: " + NAME_NOTBLANK;
        public static final String S_NAME = "WrongSizeCategoryNameException: " + SIZE_NAME;

        public static final String NN_DESCRIPTION = "NullCategoryDescriptionException: "+ DESCRIPTION_NOTNULL;
        public static final String NB_DESCRIPTION = "BlankCategoryDescriptionException: " + DESCRIPTION_NOTBLANK;
        public static final String  S_DESCRIPTION= "WrongSizeCategoryDescriptionException: " + SIZE_DESCRIPTION;
    }
    public static class BrandErrorMsg{
        private BrandErrorMsg(){}
        public static final String NN_NAME = "NullBrandNameException: " + NAME_NOTNULL;
        public static final String NB_NAME = "BlankBrandNameException: " + NAME_NOTBLANK;
        public static final String S_NAME = "WrongSizeBrandNameException: " + SIZE_NAME;

        public static final String NN_DESCRIPTION = "NullBrandDescriptionException: " + DESCRIPTION_NOTNULL;
        public static final String NB_DESCRIPTION = "BlankBrandDescriptionException: " + DESCRIPTION_NOTBLANK;
        public static final String  S_DESCRIPTION= "WrongSizeBrandDescriptionException: " + SIZE_DESCRIPTION;

    }
    public static class ItemErrorMsg{
        private ItemErrorMsg(){}
        public static final String NN_NAME = "NullItemNameException: " + NAME_NOTNULL;
        public static final String NB_NAME = "BlankItemNameException: "+ NAME_NOTBLANK;
        public static final String S_NAME = "WrongSizeItemNameException: "+ SIZE_NAME;

        public static final String NN_PRICE = "NullPriceItemException: El precio del item no puede ser null";
        public static final String MIN_PRICE = "InvalidMinPriceValue: El precio del item no puede ser menor a 500";
        public static final String MAX_PRICE = "InvalidMaxPrice: El precio del Item no puede ser mayor a 20'000.000";

        public static final String NN_STOCK = "NullStockException: La cantidad de stock no puede ser null";
        public static final String MIN_STOCK = "InvalidStockValue: La cantidad de Stock no puede ser menor a 0";

        public static final String NN_BRAND = "NullBrandException: La marca a la que pertenece el item no puede ser null";

        public static final String NN_CATEGORIES = "NullCategoriesException: Las categorías del item no pueden ser nulas";
        public static final String SIZE_CATEGORIES = "InvalidSizeCategories: El número de categorías no puede ser menor a 1 ni mayor a 3";

    }
}
