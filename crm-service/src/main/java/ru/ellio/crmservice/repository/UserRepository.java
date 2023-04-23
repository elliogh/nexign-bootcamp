package ru.ellio.crmservice.repository;

import ru.ellio.crmservice.model.User;

public interface UserRepository {
    User getByLogin(String login);
}
