package org.example.dao;

import org.example.model.Account;

public interface AccountDao extends Dao<Account> {
    String getPasswordByName(String username);

    boolean isDeleted(String username);

    void updateUsername(String valid, Long id);

    void updatePassword(String newPassword, Long id);

    void createBooking(Account user, Long favourites);

    String getRole(Long id);
}
