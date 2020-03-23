package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoJDBC;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.SQLException;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        try {
//            DataSource dataSource = connect();
//            new ProductDaoJDBC(dataSource);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        ProductDao products = ProductDaoMem.getInstance();
//        ProductCategoryDao productCategories = ProductCategoryDaoMem.getInstance();
//        SupplierDao suppliers = SupplierDaoMem.getInstance();
//
//        // Suppliers
//
//            // Body Augmentation
//            Supplier bakumatsuChipmasters = new Supplier("3f9ba30c-62b8-11ea-bc55-0242ac130003","Bakumatsu Chipmasters", "We are a manufacturing company based on human augmentations.");
//            Supplier sarifIndustries = new Supplier("3f9ba622-62b8-11ea-bc55-0242ac130003","Sarif Industries", "We are committed to providing the highest quality, customer service and products.");
//            suppliers.add(bakumatsuChipmasters);
//            suppliers.add(sarifIndustries);
//
//            // Electric Animals
//            Supplier aniMate = new Supplier("3f9ba406-62b8-11ea-bc55-0242ac130003","AniMate", "We are your only provider of electric animals. Show everyone how empathic you are!");
//            suppliers.add(aniMate);
//
//            // Fashion
//            Supplier cyberdog = new Supplier("3f9ba7b2-62b8-11ea-bc55-0242ac130003","Cyberdog", "Colorful fashion, rave outfits, no future.");
//            Supplier pandemonium = new Supplier("3f9ba87a-62b8-11ea-bc55-0242ac130003","Pandemonium", "Don't you want to look cool?");
//            suppliers.add(cyberdog);
//            suppliers.add(pandemonium);
//
//
//        // Categories
//        ProductCategory bodyAugmentation = new ProductCategory("3f9ba938-62b8-11ea-bc55-0242ac130003","Body Augmentation", "Human Enhancement", "Technological alteration of the human body in order to enhance physical or mental capabilities.");
//        ProductCategory electricAnimals = new ProductCategory("3f9ba9f6-62b8-11ea-bc55-0242ac130003","Electric Animals", "Toy", "Devices to show how empathetic you are.");
//        ProductCategory fashion = new ProductCategory("3f9baabe-62b8-11ea-bc55-0242ac130003", "Fashion", "Clothing", "If you survived you might want to look cool.");
//        productCategories.add(bodyAugmentation);
//        productCategories.add(electricAnimals);
//        productCategories.add(fashion);
//
//        // Products
//
//            // Implants ID: 1 - 6
//            products.add(new Product("b5694c96-62b4-11ea-bc55-0242ac130003","Dialect Chip", 75000, "JPY", "Be able to speak the 69420 languages of our world.", bodyAugmentation, bakumatsuChipmasters));
//            products.add(new Product("3f9b8d4a-62b8-11ea-bc55-0242ac130003","Reflex Stabilizer", 50000, "JPY", "Move like a cat. Meow.", bodyAugmentation, bakumatsuChipmasters));
//            products.add(new Product("3f9b90c4-62b8-11ea-bc55-0242ac130003","USB Finger", 30000, "JPY", "Connect the world to your fingertip.", bodyAugmentation, bakumatsuChipmasters));
//            products.add(new Product("3f9b91d2-62b8-11ea-bc55-0242ac130003","Bionic Eye Implant", 55000, "JPY", "See more than you have ever seen.", bodyAugmentation, sarifIndustries));
//            products.add(new Product("3f9b92ae-62b8-11ea-bc55-0242ac130003","Portable Encyclopedia", 35000, "JPY", "Know more than you have ever known.", bodyAugmentation, sarifIndustries));
//            products.add(new Product("3f9b9448-62b8-11ea-bc55-0242ac130003", "Mood Organ", 65000, "JPY", "Feel more than you have ever felt.", bodyAugmentation, sarifIndustries));
//
//            // Animals ID: 7 - 10
//            products.add(new Product("3f9b9510-62b8-11ea-bc55-0242ac130003", "Horse B100-02", 650000, "JPY", "Impress your neighbours with this beautiful creature.", electricAnimals, aniMate));
//            products.add(new Product("3f9b95d8-62b8-11ea-bc55-0242ac130003", "Sheep AZ15-82", 450000, "JPY", "You can feel the warmth of his nose.", electricAnimals, aniMate));
//            products.add(new Product("3f9b97ea-62b8-11ea-bc55-0242ac130003","Dog LT54-62", 550000, "JPY", "Guards your house and cares for children, so you don't need to.", electricAnimals, aniMate));
//            products.add(new Product("3f9b98bc-62b8-11ea-bc55-0242ac130003", "Cat ZY85-32", 500000, "JPY", "An inseparable pair. Now comes in a bundle.", electricAnimals, aniMate));
//
//            // Fashion-items: 11 - 20
//            products.add(new Product("3f9b997a-62b8-11ea-bc55-0242ac130003","Proton Pants", 5000, "JPY", "Proton Pants are back! Adjustable at the sides, with gigantic pockets.", fashion, cyberdog));
//            products.add(new Product("3f9b9a42-62b8-11ea-bc55-0242ac130003", "Space Commander Bodysuit", 6000, "JPY", "Limited edition bodysuit with eye-catching translucent box and a hood with visor.", fashion, cyberdog));
//            products.add(new Product("3f9b9b00-62b8-11ea-bc55-0242ac130003", "Trapper Dress", 8000, "JPY", "Part of the Electric Dreams collection by Terry Davy.", fashion, cyberdog));
//            products.add(new Product("3f9b9bc8-62b8-11ea-bc55-0242ac130003","Black (K)night Wear Woman", 15000, "JPY", "Durable, feminine and black. Do you need more?", fashion, pandemonium));
//            products.add(new Product("3f9b9c90-62b8-11ea-bc55-0242ac130003","Black (K)night Wear Man", 15000, "JPY", "Durable, masculine and black. Do you need more?", fashion, pandemonium));
//            products.add(new Product("3f9b9eb6-62b8-11ea-bc55-0242ac130003","Urban Wear Woman", 15000, "JPY", "Comfortable, feminine and brown. Do you need more?", fashion, pandemonium));
//            products.add(new Product("3f9b9f88-62b8-11ea-bc55-0242ac130003","Urban Wear Man", 15000, "JPY", "Comfortable, masculine and brown. Do you need more?", fashion, pandemonium));
//            products.add(new Product("3f9ba046-62b8-11ea-bc55-0242ac130003","Duo Goggles", 5000, "JPY", "Has a built-in microchip visor.", fashion, pandemonium));
//            products.add(new Product("3f9ba104-62b8-11ea-bc55-0242ac130003","Viral Glasses", 5000, "JPY", "Funky glasses which create a psychedelic experience!", fashion, pandemonium));
//            products.add(new Product("3f9ba1cc-62b8-11ea-bc55-0242ac130003","McFly Hoverboard", 25000, "JPY", "Marty, McFly away!", fashion, cyberdog));
    }

//    private DataSource connect() throws SQLException {
//        PGSimpleDataSource dataSource = new PGSimpleDataSource();
//        dataSource.setDatabaseName("coolshop_db");
//        dataSource.setUser("postgres");
//        dataSource.setPassword("postgres");
//        System.out.println("Trying to connect...");
//        dataSource.getConnection().close();
//        System.out.println("Connection OK");
//        return dataSource;
//    }
}
