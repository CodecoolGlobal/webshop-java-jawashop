package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
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
        supplierDao = SupplierDaoMem.getInstance();
    }

    @Test
    public void findReturnProductIfExists(){
        Supplier supplier = makeSupplier();

        supplierDao.add(makeSupplier());
        supplierDao.add(supplier);
        supplierDao.add(makeSupplier());

        assertSame(supplier, supplierDao.find(supplier.getId()));
    }

    @Test
    public void findReturnNullIfProductNotExists(){
        Supplier supplier = makeSupplier();

        supplierDao.add(makeSupplier());
        supplierDao.add(makeSupplier());
        supplierDao.add(makeSupplier());

        assertSame(null, supplierDao.find(supplier.getId()));
    }


    @Test
    public void removeProductReturnNull(){
        Supplier supplier = makeSupplier();

        supplierDao.add(makeSupplier());
        supplierDao.add(supplier);
        supplierDao.add(makeSupplier());

        supplierDao.remove(supplier.getId());
        assertSame(null, supplierDao.find(supplier.getId()));

    }

    @Test
    public void getReturnsAllProductsIfProductListNotNull(){
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