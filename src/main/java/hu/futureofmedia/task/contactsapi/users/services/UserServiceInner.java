package hu.futureofmedia.task.contactsapi.users.services;

import hu.futureofmedia.task.contactsapi.users.entities.AppUser;

public interface UserServiceInner {
    AppUser findUserByEmailOrThrow(String email);
}
