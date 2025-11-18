package com.vetenrinaria.booking.infrastructure.drivenadapter.imperativeweb.rest;

import com.vetenrinaria.booking.domain.model.booking.BookingWithData;
import com.vetenrinaria.booking.domain.usecase.BookingUseCase;
import com.vetenrinaria.booking.domain.usecase.FindBooking;
import com.vetenrinaria.booking.infrastructure.drivenadapter.imperativeweb.dto.BookingRequest;
import com.vetenrinaria.booking.infrastructure.drivenadapter.imperativeweb.dto.BookingResponse;
import com.vetenrinaria.booking.infrastructure.drivenadapter.imperativeweb.mapper.BookingMapper;
import jakarta.servlet.ServletException;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Server;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Handler {
    private final BookingUseCase bookingUseCase;
    private final FindBooking findBooking;
    private final BookingMapper bookingMapper;


    public ServerResponse findById(ServerRequest request){
        Long id = Long.valueOf(request.pathVariable("id"));
        return this.findBooking.findByIdBooking(id)
                .map(bookingMapper::toResponse)
                .map(ServerResponse.ok()::body)
                .get();

    }

    public ServerResponse findByClientId(ServerRequest request){
        Long id = Long.valueOf(request.pathVariable("clientid"));
        List<BookingWithData> bookings = this.findBooking.findByClientId(id);
        List<BookingResponse> response  = bookings.stream().map(bookingMapper::toResponse).toList();
        return ServerResponse.ok().body(response);
    }

    public ServerResponse findByEmployeeId(ServerRequest request){
        Long id = Long.valueOf(request.pathVariable("employeeid"));
        List<BookingWithData> bookings = this.findBooking.findByEmployee(id);
        List<BookingResponse> response  = bookings.stream().map(bookingMapper::toResponse).toList();
        return ServerResponse.ok().body(response);
    }

    public ServerResponse findByProductId(ServerRequest request){
        Long id = Long.valueOf(request.pathVariable("productid"));
        List<BookingWithData> bookings = this.findBooking.findByProduct(id);
        List<BookingResponse> response  = bookings.stream().map(bookingMapper::toResponse).toList();
        return ServerResponse.ok().body(response);
    }

    public ServerResponse findAll(ServerRequest request){
        List<BookingWithData> bookings = this.findBooking.findAll();
        List<BookingResponse> response  = bookings.stream().map(bookingMapper::toResponse).toList();
        return ServerResponse.ok().body(response);
    }


    public ServerResponse create(ServerRequest request) throws ServletException, IOException {
        BookingRequest bodyRequest =  request.body(BookingRequest.class);
        return this.bookingUseCase.createByClient(bookingMapper.toDomain(bodyRequest))
                .map(bookingMapper::toResponse)
                .map(ServerResponse.created(request.uri())::body).get();

    }
}
