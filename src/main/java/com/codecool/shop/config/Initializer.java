package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        // Suppliers

            // Body Augmentation
            Supplier bakumatsuChipmasters = new Supplier("Bakumatsu Chipmasters", "We are a manufacturing company based on human augmentations.");
            Supplier sarifIndustries = new Supplier("Sarif Industries", "We are committed to providing the highest quality, customer service and products.");
            supplierDataStore.add(bakumatsuChipmasters);
            supplierDataStore.add(sarifIndustries);

            // Electric Animals
            Supplier aniMate = new Supplier("AniMate", "We are your only provider of electric animals. Show everyone how empathic you are!");
            supplierDataStore.add(aniMate);

            // Fashion
            Supplier cyberdog = new Supplier("Cyberdog", "Colorful fashion, rave outfits, no future.");
            Supplier pandemonium = new Supplier("Pandemonium", "Don't you want to look cool?");
            supplierDataStore.add(cyberdog);
            supplierDataStore.add(pandemonium);


        //Categories
        ProductCategory bodyAugmentation = new ProductCategory("Body Augmentation", "Human Enhancement", "Technological alteration of the human body in order to enhance physical or mental capabilities.");
        ProductCategory electricAnimals = new ProductCategory("Electric Animals", "Toy", "Devices to show how empathetic you are.");
        ProductCategory fashion = new ProductCategory("Fashion", "Clothing", "If you survived you might want to look cool.");
        productCategoryDataStore.add(bodyAugmentation);
        productCategoryDataStore.add(electricAnimals);
        productCategoryDataStore.add(fashion);

        //Products

            // Implants ID: 1 - 6
            productDataStore.add(new Product("Dialect Chip", 75000, "JPY", "Be able to speak the 69420 languages of our world.", bodyAugmentation, bakumatsuChipmasters));
            productDataStore.add(new Product("Reflex Stabilizer", 50000, "JPY", "Move like a cat. Meow.", bodyAugmentation, bakumatsuChipmasters));
            productDataStore.add(new Product("USB Finger", 30000, "JPY", "Connect the world to your fingertip.", bodyAugmentation, bakumatsuChipmasters));
            productDataStore.add(new Product("Bionic Eye Implant", 55000, "JPY", "See more than you have ever seen.", bodyAugmentation, sarifIndustries));
            productDataStore.add(new Product("Portable Encyclopedia", 35000, "JPY", "Know more than you have ever known.", bodyAugmentation, sarifIndustries));
            productDataStore.add(new Product("Mood Organ", 65000, "JPY", "Feel more than you have ever felt.", bodyAugmentation, sarifIndustries));

            //Animals ID:
    }
}
