package org.example.service;

import org.example.dao.AccountDao;
import org.example.dao.AccountDaoImpl;
import org.example.dao.ExhibitionDao;
import org.example.model.Account;
import org.example.model.Exhibition;
import org.example.util.PasswordUtil;
import org.example.validator.InputValidator;
import org.example.validator.UserValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class AccountServiceImpl {


    private final UserValidator userValidator = new UserValidator();

    private final AccountDao accountDao = new AccountDaoImpl();

    private final ExhibitionDao exhibitionDao = ExhibitionDao.getInstance();

    public void update(HttpServletRequest req) {
        Account user = (Account) req.getSession().getAttribute("user");
        if (req.getParameter("newUsername") != null) {
            String valid = InputValidator.validate(req.getParameter("newUsername"));
            if (!(userValidator.userNameInBase(valid))) {
                user.setUsername(valid);
                accountDao.updateUsername(valid, user.getId());
            } else {
                req.setAttribute("present", "error");
            }
        }
        if (req.getParameter("newPassword") != null) {
            user.setPassword(PasswordUtil.encrypt(req.getParameter("newPassword")));
            accountDao.updatePassword(PasswordUtil.encrypt(req.getParameter("newPassword")), user.getId());
        }
        if (req.getParameter("del") != null) {
            accountDao.delete(user.getId());
            req.getSession().invalidate();
        }
    }

    public void createBooking(HttpServletRequest req) {
        Account user = (Account) req.getSession().getAttribute("user");
        user.add(Long.valueOf(req.getParameter("favourites")));
        accountDao.createBooking(user, Long.valueOf(req.getParameter("favourites")));
        List<Exhibition> mangas = new ArrayList<>();
        for (Long mangaId : user.getMangaId()) {
            mangas.add( exhibitionDao.findById(mangaId).orElseThrow(RuntimeException::new));
        }
        req.getSession().setAttribute("tickets", mangas);
    }

    public void registerAccount(String usernameValid, String passwordR) {
        accountDao.save( new Account(usernameValid, PasswordUtil.encrypt(passwordR),new ArrayList<>()));
    }

    private static class SingletonHolder{
        public static final AccountServiceImpl INSTANCE = new AccountServiceImpl();
    }

    public static AccountServiceImpl getInstance(){
        return SingletonHolder.INSTANCE;
    }
}

