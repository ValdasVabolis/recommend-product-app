package com.seb.valdas.productrecommendationservice.products.service;

import com.seb.valdas.productrecommendationservice.products.dto.ProductDTO;
import com.seb.valdas.productrecommendationservice.products.entity.Product;
import com.seb.valdas.productrecommendationservice.products.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class ProductsServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductsService productsService;

    AutoCloseable openMocks;

    private static final HashMap<String, Product> RECOMMEND_TEST_CASES =
            new HashMap<>() {{
                put("CURRENT_ACCOUNT", new Product(1L, "CurrentAccount", 18L, null, 1L, null, false));
                put("CURRENT_ACCOUNT_PLUS", new Product(2L, "CurrentAccountPlus", 18L, null, 40001L, null, false));
                put("JUNIOR_SAVER_ACCOUNT", new Product(3L, "JuniorSaverAccount", 0L, 18L, null, null, false));
                put("STUDENT_ACCOUNT", new Product(4L, "StudentAccount", 18L, null, 0L, null, true));
                put("SENIOR_ACCOUNT", new Product(5L, "SeniorAccount", 65L, null, 0L, null, false));
                put("DEBIT_CARD", new Product(5L, "DebitCard", 18L, null, 0L, 12001L, false));
                put("CREDIT_CARD", new Product(5L, "CreditCard", 18L, null, 12001L, null, false));
                put("GOLD_CREDIT_CARD", new Product(5L, "GoldCreditCard", 18L, null, 40001L, null, false));
            }};

    @BeforeEach
    public void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        productsService = new ProductsService(productRepository);
    }

    @Test
    public void recommend_current_account_test() {
        doReturn(List.of(
                RECOMMEND_TEST_CASES.get("CURRENT_ACCOUNT")
        )).when(productRepository).findAll();
        List<ProductDTO> products = productsService.recommend(false, "18-64", "1-12000");
        assertEquals(1, products.size());
    }

    @Test
    public void recommend_current_account_plus_test() {
        doReturn(List.of(
                RECOMMEND_TEST_CASES.get("CURRENT_ACCOUNT_PLUS"),
                RECOMMEND_TEST_CASES.get("CURRENT_ACCOUNT"),
                RECOMMEND_TEST_CASES.get("CREDIT_CARD"),
                RECOMMEND_TEST_CASES.get("GOLD_CREDIT_CARD")
        )).when(productRepository).findAll();
        List<ProductDTO> products = productsService.recommend(false, "18-64", "40001+");
        assertEquals(4, products.size());
    }

    @Test
    public void recommend_junior_account_test() {
        doReturn(List.of(
                RECOMMEND_TEST_CASES.get("CURRENT_ACCOUNT"),
                RECOMMEND_TEST_CASES.get("JUNIOR_SAVER_ACCOUNT")
        )).when(productRepository).findAll();
        List<ProductDTO> products = productsService.recommend(false, "0-17", "0");
        assertEquals(1, products.size());
        assertEquals(products.get(0).getName(), RECOMMEND_TEST_CASES.get("JUNIOR_SAVER_ACCOUNT").getName());
    }

    @Test
    public void recommend_broke_student_account_test() {
        doReturn(List.of(
            RECOMMEND_TEST_CASES.get("STUDENT_ACCOUNT"),
            RECOMMEND_TEST_CASES.get("DEBIT_CARD")
        )).when(productRepository).findAll();
        List<ProductDTO> products = productsService.recommend(true, "18-64", "0");
        assertEquals(2, products.size());
    }

    @Test
    public void recommend_rich_student_account_test() {
        doReturn(List.of(
                RECOMMEND_TEST_CASES.get("STUDENT_ACCOUNT"),
                RECOMMEND_TEST_CASES.get("CURRENT_ACCOUNT"),
                RECOMMEND_TEST_CASES.get("CURRENT_ACCOUNT_PLUS"),
                RECOMMEND_TEST_CASES.get("CREDIT_CARD"),
                RECOMMEND_TEST_CASES.get("GOLD_CREDIT_CARD")
        )).when(productRepository).findAll();
        List<ProductDTO> products = productsService.recommend(true, "18-64", "40001+");
        assertEquals(5, products.size());
    }

    @Test
    public void recommend_broke_senior_account_test() {
        doReturn(List.of(
            RECOMMEND_TEST_CASES.get("SENIOR_ACCOUNT"),
            RECOMMEND_TEST_CASES.get("DEBIT_CARD")
        )).when(productRepository).findAll();
        List<ProductDTO> products = productsService.recommend(false, "65+", "0");
        assertEquals(2, products.size());
    }

    @Test
    public void recommend_rich_senior_account_test() {
        doReturn(List.of(
                RECOMMEND_TEST_CASES.get("SENIOR_ACCOUNT"),
                RECOMMEND_TEST_CASES.get("CURRENT_ACCOUNT"),
                RECOMMEND_TEST_CASES.get("CURRENT_ACCOUNT_PLUS"),
                RECOMMEND_TEST_CASES.get("CREDIT_CARD"),
                RECOMMEND_TEST_CASES.get("GOLD_CREDIT_CARD")
        )).when(productRepository).findAll();
        List<ProductDTO> products = productsService.recommend(false, "65+", "40001+");
        assertEquals(5, products.size());
    }

}
