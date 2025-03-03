package com.demo.vaultspring.utils;

import com.demo.vaultspring.model.Account;
import com.demo.vaultspring.model.User;
import com.demo.vaultspring.model.enums.Role;

import java.math.BigDecimal;
import java.util.ArrayList;

public class TestDataUtils {
    public static User BASE_USER() {
        User user = new User(1L, "johndoe", "1234", Role.USER, new ArrayList<>());

        return  user;
    }

    public static User USER_WITH_1_ACCOUNT() {
        User user = BASE_USER();
        user.addAccount(BASIC_ACCOUNT(user));

        return user;
    }

    public static User USER_WITH_3_ACCOUNTS() {
        User user = BASE_USER();

        for (int i = 0; i < 3; i ++)
        {
            // Convert int to long for id
            Long newId = (long) i;
            user.addAccount(ITERABLE_ACCOUNT(newId, user));
        }

        return user;
    }

    public static User USER_WITH_X_ACCOUNTS(int x) {
        User user = BASE_USER();

        for (int i = 0; i < x; i ++)
        {
            // Convert int to long for id
            Long newId = (long) i;
            user.addAccount(ITERABLE_ACCOUNT(newId, user));
        }

        return user;
    }

    public static Account BASIC_ACCOUNT() {
        Account account = new Account(1L, "0000LOCL12345678", BigDecimal.valueOf(2000), null);
        return account;
    }

    public static Account BASIC_ACCOUNT(User user) {
        Account account = new Account(1L, "0000LOCL12345678", BigDecimal.valueOf(2000), user);
        return account;
    }

    public static Account ITERABLE_ACCOUNT(Long id) {
        Long newNum = 1000L + id;
        String accountNumber = "0000LOCL1234" + newNum.toString();
        Account account = new Account(id, accountNumber, BigDecimal.valueOf(2000), null);
        return account;
    }

    public static Account ITERABLE_ACCOUNT(Long id, User user) {
        Long newNum = 1000L + id;
        String accountNumber = "0000LOCL1234" + newNum.toString();
        Account account = new Account(id, accountNumber, BigDecimal.valueOf(2000), user);
        return account;
    }
}
