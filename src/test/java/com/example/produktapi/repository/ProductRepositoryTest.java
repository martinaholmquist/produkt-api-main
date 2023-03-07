package com.example.produktapi.repository;


import com.example.produktapi.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.*;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository underTest;


    @Test
    void testingOurRepository(){
        List<Product> products = underTest.findAll();
        Assertions.assertFalse(products.isEmpty());
    }

    @Test
    void whenSearchingForAnExistingTitleReturnThatProduct(){
        //given
        String title = "En dator";
        Product product = new Product(title, 2699.0, "Electronik", "bra att ha", "urlTillEnBild");
                underTest.save(product);

        //when
        Optional<Product> optionalProduct = underTest.findByTitle(title);

        //then
        Assertions.assertTrue(optionalProduct.isPresent());
        Assertions.assertEquals(title, optionalProduct.get().getTitle());

    }

    @Test
    void whenSearchingforNOTexistingTitleReturnEmptyOptional(){
        //given
        String title = "Denna titel finns inte";

        //when
        Optional<Product> optionalProduct = underTest.findByTitle(title);

        //then
        Assertions.assertFalse(optionalProduct.isPresent());
        Assertions.assertTrue(optionalProduct.isEmpty());
        Assertions.assertThrows(NoSuchElementException.class, ()->optionalProduct.get());
    }



    @Test
    void whenSearchingForAnExcistingCategoryThenReturnCategory(){
        //given
        String category = "Electronik";
        Product product = new Product("Usb", 2699.0, category , "bra att ha", "urlTillEnBild");
        underTest.save(product);

        //when
        List <Product> categoryExcist = underTest.findByCategory(category);

        //then
        assertFalse(categoryExcist.isEmpty());
        assertEquals(categoryExcist.get(0).getCategory(),"Electronik");

    }

    @Test
    void whenSearchingForANonExcistingCategoryThenReturnEmpty(){

        underTest.deleteAll();
       //given
        List <Product> categoryDontExcist = underTest.findByCategory("hittepå");

        //then
        assertTrue(categoryDontExcist.isEmpty());

    }

    @Test
    void whenSearchingForAllExcistingCategoriesThenReturnCorrect(){
        //given
        List<String>categories = underTest.findAllCategories();

        //then
        Assertions.assertFalse(categories.isEmpty());
        Assertions.assertEquals(categories.size(),4);
    }


    @Test
    void whenSearchingForAllExcistingCategoriesCheckForNoDublicatesThenReturnCorrectIfNoDublicates(){
        //given & when
        List<String>categorylista = underTest.findAllCategories();
        int antalKategorier = underTest.findAllCategories().size();

        Set<String> set = categorylista.stream().collect(Collectors.toSet());
        int antalEfterBorttagningAvDubletter = set.size();

        //then
        Assertions.assertEquals(antalKategorier, antalEfterBorttagningAvDubletter);
    }

}