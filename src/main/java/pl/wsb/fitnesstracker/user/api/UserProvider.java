package pl.wsb.fitnesstracker.user.api;

import java.util.List;
import java.util.Optional;

/**
 * The interface User provider.
 */
public interface UserProvider {

    /**
     * Retrieves a user based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param userId id of the user to be searched
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    Optional<User> getUser(Long userId);

    /**
     * Retrieves a user based on their email.
     * If the user with given email is not found, then empty List will be returned.
     *
     * @param email The email of the user to be searched
     * @return An {@link List} containing the located user
     */
    List<User> getUsersByEmail(String email);

    /**
     * Retrieves all users.
     *
     * @return An {@link Optional} containing the all users,
     */
    List<User> findAllUsers();

    /**
     * Gets user by name and last name.
     *
     * @param name     the name
     * @param lastName the last name
     * @return An {@link Optional} with user that fits this criteria,
     */
    List<User> getUserByNameAndLastName(String name, String lastName);

}
