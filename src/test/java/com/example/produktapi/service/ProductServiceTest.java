package com.example.produktapi.service;

import com.example.produktapi.exception.BadRequestException;
import com.example.produktapi.exception.EntityNotFoundException;
import com.example.produktapi.model.Product;
import com.example.produktapi.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;



@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService underTest;

    @Captor
    ArgumentCaptor<Product> productCaptor;

    @Captor
    ArgumentCaptor<Integer> intcaptor;

    @Captor
    ArgumentCaptor<String> stringcaptor;


    @Test
    void givenGetAllProductsAndOnlyOneInteraction_AndNoMoreInteractions () {
        //when
        underTest.getAllProducts();
        //then
        BDDMockito.verify(repository).findAll();
        BDDMockito.verify(repository, Mockito.times(1)).findAll();
        BDDMockito.verifyNoMoreInteractions(repository);
    }


    @Test
    void givenGetAllCategoriesAndOnlyOneInteraction_AndNoMoreInteractions () {
        //when
        underTest.getAllCategories();
        //then
        BDDMockito.verify(repository).findAllCategories();
        BDDMockito.verify(repository, Mockito.times(1)).findAllCategories();
        BDDMockito.verifyNoMoreInteractions(repository);
    }


    @Test
    void givenAnExictingCategory_WhenGetProductByCategory_ThenReceiveANonEmptyListAndCheckCorrectCategory(){
        //given
        Product product = new Product("En grym pryttel", 400.0, "En riktigt bra kategori", "", "");
        BDDMockito.given(repository.findByCategory("En riktigt bra kategori")).willReturn(List.of(product));
        //when
        underTest.getProductsByCategory("En riktigt bra kategori");
        //then
        BDDMockito.verify(repository,Mockito.times(1)).findByCategory(stringcaptor.capture());
        Assertions.assertEquals("En riktigt bra kategori",stringcaptor.getValue());
        Assertions.assertFalse(product.getCategory().isEmpty());
    }

    @Test
    void whenAddingAProductThenSaveMethodShouldBeCalled(){
        //given
        Product product = new Product("Dator", 400.0, "a", "", "");
        //when
        underTest.addProduct(product);
        BDDMockito.verify(repository).save(productCaptor.capture());
        //then
        Assertions.assertEquals(product, productCaptor.getValue());
    }

    @Test
    void whenAddingAProductWithDuplicatedTitle_thenThrowError(){
        //given
        String title = "Titeln finns";
        Product product = new Product(title,34.0,"","","");

        BDDMockito.given(repository.findByTitle(title)).willReturn(Optional.of(product));
        BadRequestException exception = assertThrows(BadRequestException.class,
        //when
                ()-> underTest.addProduct(product));
        BDDMockito.verify(repository,BDDMockito.times(1)).findByTitle(title);
        BDDMockito.verify(repository,BDDMockito.never()).save(BDDMockito.any());
        assertEquals("En produkt med titeln: Titeln finns finns redan",exception.getMessage());
        BDDMockito.verifyNoMoreInteractions(repository);

    }


    @Test
    void whenDeletingAProductThen_DeleteById_ShouldBeCalledAndControlOfDeleatedId() {
        //given
        Product product = new Product("Dator", 400.0, "", "", "");
        product.setId(1);
        BDDMockito.given(repository.findById(1)).willReturn(Optional.of(product));
        //when
        underTest.deleteProduct(1);
        //then
        BDDMockito.verify(repository).deleteById(intcaptor.capture());
        Assertions.assertEquals(1, intcaptor.getValue());
        BDDMockito.verify(repository, Mockito.times(1)).deleteById(1);
        BDDMockito.verifyNoMoreInteractions(repository);

    }

    @Test
    void ifNoProductWithInputIDThrowExceptionAndDontDeleteAnyProductTest() {
                //given
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                //when&then
                ()-> underTest.deleteProduct(1));
        BDDMockito.verify(repository,BDDMockito.never()).deleteById(BDDMockito.any());
        Assertions.assertEquals("Produkt med id 1 hittades inte",exception.getMessage());
    }

    @Test
    void whenGetProductByIdAndFound_CorrectProductShouldBeFound() {
        //given
        Product product = new Product("Dator", 400.0, "", "", "");
        product.setId(1);
        BDDMockito.given(repository.findById(1)).willReturn(Optional.of(product));

        //when
        Product savedProduct = underTest.getProductById(1);

        //then
        Assertions.assertEquals(product, savedProduct);
    }

    @Test
    void whenGetProductByIdIdNotFound_Exception_ShouldBeCalled() {
        //then
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, ()->{
                    underTest.getProductById(1);
                }
        );
        Assertions.assertEquals("Produkt med id " + 1 + " hittades inte", exception.getMessage());
    }


    @Test
    void whenUpdateingAProductThen_SaveProduct_ShouldBeCalledWithCorrectProduct() {
        //given
        Product product = new Product("En riktig skitdator", 400.0, "superdålig", "köp ej", "dålig bild");
        product.setId(1);
        Product updatedProduct = new Product("Den allra bästa datorn", 400.0, "bäst", "har du inte köpt den än?", "bästa bilden");

        BDDMockito.given(repository.findById(1)).willReturn(Optional.of(product));

        //when
        underTest.updateProduct(updatedProduct, product.getId());
        BDDMockito.verify(repository).save((productCaptor.capture()));
        //then
        Assertions.assertEquals(updatedProduct, productCaptor.getValue());
        Assertions.assertEquals("Den allra bästa datorn",updatedProduct.getTitle());
        BDDMockito.verify(repository, Mockito.times(1)).save(updatedProduct);
        BDDMockito.verifyNoMoreInteractions(repository);
    }

    @Test
    void whenUpdateingAProductThenCheckException() {
        //given
        Product updatedProduct = new Product("Dator", 400.0, "inte så bra", "sämre", "dålig bild");
        updatedProduct.setId(1);
        //when
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, ()->{
            underTest.updateProduct(updatedProduct,1 );
        });
        //then
        Assertions.assertEquals("Produkt med id " + 1 + " hittades inte", exception.getMessage());
    }
}