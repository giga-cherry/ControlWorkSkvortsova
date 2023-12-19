package org.example.service;

import javax.servlet.http.HttpServletRequest;

import org.example.dao.ExhibitionDao;
import org.example.model.Exhibition;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExhibitionService {

    private final ExhibitionDao repository;

    private final static DateTimeFormatter formatter_time = DateTimeFormatter.ofPattern("HH:mm");

    private final static DateTimeFormatter formatter_date = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public void deleteExhibition(HttpServletRequest request){
        repository.delete(Long.valueOf(request.getParameter("pc_id")));
    }

    public Exhibition getExhibition(HttpServletRequest request){
        return repository.findById(Long.valueOf(request.getParameter("exhibition_id"))).orElseThrow(RuntimeException::new);
    }

    public List<Exhibition> getAll(){
        return repository.findAll();
    }

    public List<Exhibition> findOnTime(HttpServletRequest request){
        LocalDateTime from = LocalDateTime.parse(request.getParameter("date")+ " "+request.getParameter("from"), formatter_date);
        LocalDateTime to = LocalDateTime.parse(request.getParameter("date")+ " "+request.getParameter("to"), formatter_date);
        return repository.findOnTime(from, to);
    }

    private ExhibitionService(){
        repository = ExhibitionDao.getInstance();
    }

    private static class SingletonHolder{
        public static final ExhibitionService INSTANCE = new ExhibitionService();
    }

    public static ExhibitionService getInstance(){
        return SingletonHolder.INSTANCE;
    }

}
