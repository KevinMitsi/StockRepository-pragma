package com.kevin.emazon.domain.usecase;

import com.kevin.emazon.domain.api.IItemServicePort;
import com.kevin.emazon.domain.model.Item;
import com.kevin.emazon.domain.spi.ItemPersistentPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@AllArgsConstructor
public class ItemUseCase implements IItemServicePort {

    private final ItemPersistentPort _IitemPersistentPort;
    @Override
    public Iterable<Item> getItems() {
        return _IitemPersistentPort.getItems();
    }

    @Override
    public Optional<Item> getItem(Long id) {
        return _IitemPersistentPort.getItem(id);
    }

    @Override
    public Item saveItem(Item item) {
        return _IitemPersistentPort.saveItem(item);
    }

    @Override
    public void deleteItem(Long id) {
        _IitemPersistentPort.deleteItem(id);
    }
}
