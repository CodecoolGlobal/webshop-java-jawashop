package com.codecool.shop.dao.in_memory;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDaoMemTest {
    SupplierDao supplierDao;

    @BeforeEach
    public void init(){
        supplierDao = SupplierDaoMem.create();
    }

    @Test
    public void findReturnSupplierIfExists(){
        Supplier supplier = makeSupplier();

        supplierDao.add(makeSupplier());
        supplierDao.add(supplier);
        supplierDao.add(makeSupplier());

        assertSame(supplier, supplierDao.find(supplier.getId()));
    }

    @Test
    public void findReturnNullIfSupplierNotExists(){
        Supplier supplier = makeSupplier();

        supplierDao.add(makeSupplier());
        supplierDao.add(makeSupplier());
        supplierDao.add(makeSupplier());

        assertNull(supplierDao.find(supplier.getId()));
    }

    @Test
    public void removeSupplierReturnNull(){
        Supplier supplier = makeSupplier();

        supplierDao.add(makeSupplier());
        supplierDao.add(supplier);
        supplierDao.add(makeSupplier());

        supplierDao.remove(supplier.getId());
        assertNull(supplierDao.find(supplier.getId()));

    }

    @Test
    public void getReturnsAllSuppliersIfProductListNotNull(){
        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(makeSupplier());
        suppliers.add(makeSupplier());
        suppliers.add(makeSupplier());

        for (Supplier supplier: suppliers) {
            supplierDao.add(supplier);
        }

        assertEquals(suppliers, supplierDao.getAll());
    }

    private Supplier makeSupplier(){
        return new Supplier(UUID.randomUUID().toString(), null, null);
    }
}
