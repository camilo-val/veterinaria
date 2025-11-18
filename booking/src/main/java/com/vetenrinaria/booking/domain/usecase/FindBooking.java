package com.vetenrinaria.booking.domain.usecase;

import com.vetenrinaria.booking.domain.model.booking.BookingWithData;
import com.vetenrinaria.booking.domain.model.booking.gateway.BookingGateway;
import com.vetenrinaria.booking.domain.model.exceptions.BusinessExceptions;
import com.vetenrinaria.booking.domain.model.exceptions.BusinessMessageExceptions;
import com.vetenrinaria.booking.domain.model.person.PersonWithUser;
import com.vetenrinaria.booking.domain.model.person.gateway.PersonGateway;
import com.vetenrinaria.booking.domain.model.product.Product;
import com.vetenrinaria.booking.domain.model.product.gateway.ProductGateway;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class FindBooking {
    private final BookingGateway bookingGateway;
    private final PersonGateway personGateway;
    private final ProductGateway productGateway;

    public Optional<BookingWithData> findByIdBooking(Long id) {
        return this.bookingGateway.findById(id)
            .flatMap(booking -> personGateway.findByID(booking.getClientId())
            .flatMap(client -> personGateway.findByID(booking.getEmployeeId())
            .flatMap(employee -> productGateway.findByID(booking.getProductId())
            .map(product -> new BookingWithData(client, employee, product, booking)))))
            .or(() -> {
                throw new BusinessExceptions(BusinessMessageExceptions.INVALID_BOOKING);
            });
    }

    public List<BookingWithData> findByClientId(Long clientId) {
        List<PersonWithUser> employees = this.personGateway.findAll();
        List<Product> products = this.productGateway.findAll();
        PersonWithUser client = this.personGateway.findByID(clientId).get();

        return this.bookingGateway.findByClientId(clientId).stream()
                .map(booking -> employees.stream()
                        .filter(employeeBd -> (employeeBd.getPerson().getId().equals(booking.getEmployeeId()))
                                && !employeeBd.getUser().getRole().equals("CLIENT")).findFirst()
                        .flatMap(employee ->
                                products.stream()
                                .filter(productBd -> productBd.getId().equals(booking.getProductId())).findFirst()
                                        .map(product -> new BookingWithData(client, employee, product, booking))).get()).toList();
    }

    public List<BookingWithData> findByEmployee(Long employeeId) {
        List<PersonWithUser> clients = this.personGateway.findAll();
        List<Product> products = this.productGateway.findAll();
        PersonWithUser employee = this.personGateway.findByID(employeeId).get();

        return this.bookingGateway.findByEmployeeId(employeeId).stream()
                .map(booking -> clients.stream()
                        .filter(clientBd -> (clientBd.getPerson().getId().equals(booking.getClientId()))
                                && clientBd.getUser().getRole().equals("CLIENT")).findFirst()
                        .flatMap(client ->
                                products.stream()
                                        .filter(productBd -> productBd.getId().equals(booking.getProductId())).findFirst()
                                        .map(product -> new BookingWithData(client, employee, product, booking))).get()).toList();
    }

    public List<BookingWithData> findByProduct(Long productId) {
        List<PersonWithUser> clients = this.personGateway.findAll();
        Optional<Product> product = this.productGateway.findByID(productId).or(() -> {
            throw new BusinessExceptions(BusinessMessageExceptions.INVALID_BOOKING);
        });
        List<PersonWithUser> employees = this.personGateway.findAll();

        return this.bookingGateway.findByProductId(productId).stream()
                .map(booking -> clients.stream()
                        .filter(clientBd -> (clientBd.getPerson().getId().equals(booking.getClientId()))
                                && clientBd.getUser().getRole().equals("CLIENT")).findFirst()
                        .flatMap(client ->
                                employees.stream()
                                        .filter(employeeBd -> employeeBd.getPerson().getId().equals(booking.getEmployeeId())
                                                 && !employeeBd.getUser().getRole().equals("CLIENT")).findFirst()
                                        .map(employee -> new BookingWithData(client, employee, product.get(), booking))).get()).toList();
    }

    public List<BookingWithData> findAll() {
        List<PersonWithUser> clients = this.personGateway.findAll();
        List<Product> products = this.productGateway.findAll();
        List<PersonWithUser> employees = this.personGateway.findAll();
        return this.bookingGateway.findAll()
                .stream()
                .map(booking -> clients.stream()
                        .filter(client -> (client.getPerson().getId().equals(booking.getClientId()))
                                && client.getUser().getRole().equals("CLIENT"))
                        .findFirst()
                        .flatMap(client -> employees.stream()
                                .filter(employee -> employee.getPerson().getId().equals(booking.getEmployeeId())
                                                && !employee.getUser().getRole().equals("CLIENT"))
                                .findFirst()
                                .flatMap(employee -> products.stream()
                                        .filter(product -> product.getId().equals(booking.getProductId()))
                                        .findFirst()
                                        .map(product -> new BookingWithData(client, employee, product, booking)))).get()).toList();
    }

}
