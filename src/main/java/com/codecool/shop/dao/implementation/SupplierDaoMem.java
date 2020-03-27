package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.List;

public class SupplierDaoMem implements SupplierDao {

    private List<Supplier> data = new ArrayList<>();
    private static SupplierDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private SupplierDaoMem() {
    }

    public static SupplierDaoMem getInstance() {
        if (instance == null) {
            instance = new SupplierDaoMem();
        }
        return instance;
    }

    public static SupplierDao create() {
        return new SupplierDaoMem();
    }

    @Override
    public void add(Supplier supplier) {
        data.add(supplier);
    }

    @Override
    public Supplier find(String id) {
        return data.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Product> getProductsBySupplierId(String id, List<Product> products) {
        List<Product> productsBySupplier = new ArrayList<>();
        for (Product product : products) {
            if (product.getSupplier().getId().equals(id)) {
                productsBySupplier.add(product);
            }
        }
        return productsBySupplier;
    }

    @Override
    public void remove(String id) {
        data.remove(find(id));
    }

    @Override
    public List<Supplier> getAll() {
        return data;
    }
}
