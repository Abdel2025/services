package com.example.demo.services;

import com.example.demo.dao.*;
import com.example.demo.entities.*;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {
    // Repositories for interacting with the database
//    private CustomerRepository customerRepository;
    private CartRepository cartRepository;
//    private VacationRepository vacationRepository;
//    private ExcursionRepository excursionRepository;
//    private CartItemRepository cartItemRepository;

    // Constructor to inject dependencies
//    public CheckoutServiceImpl(CustomerRepository customerRepository, CartRepository cartRepository,
//                               VacationRepository vacationRepository, ExcursionRepository excursionRepository, CartItemRepository cartItemRepository) {
//        this.customerRepository = customerRepository;
//        this.cartRepository = cartRepository;
//        this.vacationRepository = vacationRepository;
//        this.excursionRepository = excursionRepository;
//        this.cartItemRepository = cartItemRepository;
//    }
    public CheckoutServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    // Method to place an order and save all related entities
    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        // Generate a tracking number for the order
        String orderTrackingNumber = generateOrderTrackingNumber();
        Cart cart = purchase.getCart();
        Set<CartItem>cartItems = purchase.getCartItems();
        cartItems.forEach(item -> cart.add(item));
        cart.setStatus(StatusType.CartStatus.ordered);
        cartRepository.save(cart);
        // Set the order tracking number in the Cart entity
//        purchase.getCart().setOrderTrackingNumber(orderTrackingNumber);

        // Change the Cart status to "ordered"
//        purchase.getCart().setStatus(StatusType.CartStatus.ordered);

        // Get the first vacation item from the cart items
//        Vacation vacation = purchase.getCartItems()
//                .stream()
//                .findFirst()
//                .map(CartItem::getVacation)
//                .orElseThrow(() -> new IllegalArgumentException("Vacation cannot be null."));

        // Save the vacation entity in the database
//        vacationRepository.save(vacation);


        // Save the cart (which contains the order tracking number and status)
//        Cart savedCart = cartRepository.save(purchase.getCart());
//
//        // If the vacation has excursions, link each excursion to the vacation
//        Optional.ofNullable(vacation.getExcursions())
//                .ifPresent(excursions -> excursions.forEach(excursion -> {
//                    if (excursion.getVacation() == null) {
//                        excursion.setVacation(vacation);
//                    }
//                    excursionRepository.save(excursion);
//                }));
//
//        // For each cart item, associate it with the saved cart and save it
//        purchase.getCartItems().forEach(cartItem -> {
//            cartItem.setCart(savedCart);
//
//            cartItemRepository.save(cartItem);
//        });
//
//        // For each cart item, link the associated excursions to the cart item
//        purchase.getCartItems().forEach(cartItem -> {
//            Set<Excursion> excursionsForCartItem = cartItem.getExcursions();
//            if(excursionsForCartItem != null) {
//                excursionsForCartItem.forEach(excursion -> {
//                    Excursion persistedExcursion = excursionRepository.findById(excursion.getId()).orElse(null);
//                    if (persistedExcursion != null) {
//                        persistedExcursion.getCartitems().add(cartItem);
//                        excursionRepository.save(persistedExcursion);
//                    }
//                });
//            }
//        });
//
//        // Save the customer related to the order
//        Customer customer = purchase.getCustomer();
//        customerRepository.save(customer);

        // Return a response containing the order tracking number
        return new PurchaseResponse(orderTrackingNumber);
    }

    // Method to generate a unique order tracking number using UUID
    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}
