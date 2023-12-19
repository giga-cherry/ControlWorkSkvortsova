package org.example.validator;

import org.example.dao.AccountDao;
import org.example.dao.AccountDaoImpl;
import org.example.model.Account;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserValidator {

    public final AccountDao dao = new AccountDaoImpl();

    public boolean userNameInBase (String username) {
        List<String> accounts = dao.getAll().stream().map(Account::getUsername).toList();
        return accounts.contains(username);
    }

    public boolean userInBase(Account account, HttpServletRequest request) {
        List<Account> accounts = dao.getAll();
        if (userNameInBase(account.getUsername())) {
            if (account.getPassword()
                    .equals(dao.getPasswordByName(account.getUsername()))){

                for (Account acc: accounts) {
                    if (acc.getUsername().equals(account.getUsername())) {
                        account.setId(acc.getId());
                    }
                }

                if (!(dao.isDeleted(account.getUsername()))) {
                    request.setAttribute("incorrect", null);
                    return true;
                }
            }
        }
        request.setAttribute("incorrect", "error");
        return false;
    }


}
