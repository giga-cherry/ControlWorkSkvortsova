package org.example.service;

import javax.servlet.http.HttpServletRequest;
import org.example.dao.BookingDao;
import org.example.model.Account;
import org.example.model.Booking;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class BookingService {

    private static BookingDao repository;

    private static AccountServiceImpl userService;

    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public void createBooking(HttpServletRequest request){
        LocalDateTime from = LocalDateTime.parse(request.getParameter("date")+ " "+request.getParameter("from"), formatter);
        LocalDateTime to = LocalDateTime.parse(request.getParameter("date")+ " "+request.getParameter("to"), formatter);
        Booking booking = new Booking(null,
                ((Account)(request.getSession().getAttribute("user"))).getId(),
                Long.valueOf(request.getParameter("exhibition_id")),
                from,
                to, Long.parseLong(request.getParameter("type")));
        repository.create(booking);
    }

    public boolean updateBooking(HttpServletRequest request){
        Optional<Booking> booking = repository.findById(Long.valueOf(request.getParameter("booking_id")));
        if (booking.isPresent()){
            LocalDateTime from = LocalDateTime.parse(request.getParameter("date")+ " "+request.getParameter("from"), formatter);
            LocalDateTime to = LocalDateTime.parse(request.getParameter("date")+ " "+request.getParameter("to"), formatter);
            Booking newBooking = new Booking(null,
                    ((Account)(request.getSession().getAttribute("user"))).getId(),
                    Long.valueOf(request.getParameter("exhibition_id")),
                    from,
                    to, Long.parseLong(request.getParameter("type")));
            repository.update(newBooking);
            return true;
        }
        return false;
    }

    public void deleteBooking(HttpServletRequest request){
        repository.delete(Long.valueOf(request.getParameter("booking_id")));
    }

    public Booking getBookingById(HttpServletRequest request){
        return repository.findById(Long.valueOf(request.getParameter("booking_id")))
                .orElseThrow(RuntimeException::new);
    }

    public List<Booking> getBookingByUserId(HttpServletRequest request){
        return repository.findByUserId(Long.valueOf(request.getParameter("user_id")));
    }

    public List<Booking> getBookingByUserId(Long id){
        return repository.findByUserId(id);
    }

    public List<Booking> getBookingByPCId(HttpServletRequest request){
        return repository.findByExhibitionId(Long.valueOf(request.getParameter("pc_id")));
    }

    public List<Booking> getBookingByUserIdAndPCId(HttpServletRequest request){
        return repository.findByUserIdAndExhibitionId(((Account)(request.getSession().getAttribute("user"))).getId(),
                Long.valueOf(request.getParameter("exhibition_id")));
    }

    private BookingService(){
        repository = BookingDao.getInstance();
        userService = AccountServiceImpl.getInstance();
    }

    private static class SingletonHolder{
        public static final BookingService INSTANCE = new BookingService();
    }

    public static BookingService getInstance(){
        return SingletonHolder.INSTANCE;
    }

}
