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
        ProductDao products = ProductDaoMem.getInstance();
        ProductCategoryDao productCategories = ProductCategoryDaoMem.getInstance();
        SupplierDao suppliers = SupplierDaoMem.getInstance();

        // Suppliers

            // Body Augmentation
            Supplier bakumatsuChipmasters = new Supplier("Bakumatsu Chipmasters", "We are a manufacturing company based on human augmentations.");
            Supplier sarifIndustries = new Supplier("Sarif Industries", "We are committed to providing the highest quality, customer service and products.");
            suppliers.add(bakumatsuChipmasters);
            suppliers.add(sarifIndustries);

            // Electric Animals
            Supplier aniMate = new Supplier("AniMate", "We are your only provider of electric animals. Show everyone how empathic you are!");
            suppliers.add(aniMate);

            // Fashion
            Supplier cyberdog = new Supplier("Cyberdog", "Colorful fashion, rave outfits, no future.");
            Supplier pandemonium = new Supplier("Pandemonium", "Don't you want to look cool?");
            suppliers.add(cyberdog);
            suppliers.add(pandemonium);


        // Categories
        ProductCategory bodyAugmentation = new ProductCategory("Body Augmentation", "Human Enhancement", "Technological alteration of the human body in order to enhance physical or mental capabilities.");
        ProductCategory electricAnimals = new ProductCategory("Electric Animals", "Toy", "Devices to show how empathetic you are.");
        ProductCategory fashion = new ProductCategory("Fashion", "Clothing", "If you survived you might want to look cool.");
        productCategories.add(bodyAugmentation);
        productCategories.add(electricAnimals);
        productCategories.add(fashion);

        // Products

            // Implants ID: 1 - 6
            products.add(new Product("Dialect Chip", 75000, "JPY", "Be able to speak the 69420 languages of our world.", bodyAugmentation, bakumatsuChipmasters));
            products.add(new Product("Reflex Stabilizer", 50000, "JPY", "Move like a cat. Meow.", bodyAugmentation, bakumatsuChipmasters));
            products.add(new Product("USB Finger", 30000, "JPY", "Connect the world to your fingertip.", bodyAugmentation, bakumatsuChipmasters));
            products.add(new Product("Bionic Eye Implant", 55000, "JPY", "See more than you have ever seen.", bodyAugmentation, sarifIndustries));
            products.add(new Product("Portable Encyclopedia", 35000, "JPY", "Know more than you have ever known.", bodyAugmentation, sarifIndustries));
            products.add(new Product("Mood Organ", 65000, "JPY", "Feel more than you have ever felt.", bodyAugmentation, sarifIndustries));

            // Animals ID: 7 - 10
            products.add(new Product("Horse B100-02", 650000, "JPY", "Impress your neighbours with this beautiful creature.", electricAnimals, aniMate));
            products.add(new Product("Sheep AZ15-82", 450000, "JPY", "You can feel the warmth of his nose.", electricAnimals, aniMate));
            products.add(new Product("Dog LT54-62", 550000, "JPY", "Guards your house and cares for children, so you don't need to.", electricAnimals, aniMate));
            products.add(new Product("Cat ZY85-32", 500000, "JPY", "An inseparable pair. Now comes in a bundle.", electricAnimals, aniMate));

            // Fashion-items: 11 - 20
            products.add(new Product("Proton Pants", 5000, "JPY", "Proton Pants are back! Adjustable at the sides, with gigantic pockets.", fashion, cyberdog));
            products.add(new Product("Space Commander Bodysuit", 6000, "JPY", "Limited edition bodysuit with eye-catching translucent box and a hood with visor.", fashion, cyberdog));
            products.add(new Product("Trapper Dress", 8000, "JPY", "Part of the Electric Dreams collection by Terry Davy.", fashion, cyberdog));
            products.add(new Product("Black (K)night Wear Woman", 15000, "JPY", "Durable, feminine and black. Do you need more?", fashion, pandemonium));
            products.add(new Product("Black (K)night Wear Man", 15000, "JPY", "Durable, masculine and black. Do you need more?", fashion, pandemonium));
            products.add(new Product("Urban Wear Woman", 15000, "JPY", "Comfortable, feminine and brown. Do you need more?", fashion, pandemonium));
            products.add(new Product("Urban Wear Man", 15000, "JPY", "Comfortable, masculine and brown. Do you need more?", fashion, pandemonium));
            products.add(new Product("Duo Goggles", 5000, "JPY", "Has a built-in microchip visor.", fashion, pandemonium));
            products.add(new Product("Viral Glasses", 5000, "JPY", "Funky glasses which create a psychedelic experience!", fashion, pandemonium));
            products.add(new Product("McFly Hoverboard", 25000, "JPY", "Marty, McFly away!", fashion, cyberdog));
    }
}
