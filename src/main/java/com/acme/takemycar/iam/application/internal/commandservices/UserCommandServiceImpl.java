package com.acme.takemycar.iam.application.internal.commandservices;

import com.acme.takemycar.iam.application.internal.outboundservices.hashing.HashingService;
import com.acme.takemycar.iam.application.internal.outboundservices.tokens.TokenService;
import com.acme.takemycar.iam.domain.model.aggregates.User;
import com.acme.takemycar.iam.domain.model.commands.SignInCommand;
import com.acme.takemycar.iam.domain.model.commands.SignUpCommand;
import com.acme.takemycar.iam.domain.model.valueobjects.Roles;
import com.acme.takemycar.iam.domain.services.UserCommandService;
import com.acme.takemycar.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * User command service implementation
 * <p>
 *     This class implements the {@link UserCommandService} interface and provides the implementation for the
 *     {@link SignInCommand} and {@link SignUpCommand} commands.
 * </p>
 */
@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;

    public UserCommandServiceImpl(UserRepository userRepository, HashingService hashingService, TokenService tokenService) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
    }

    /**
     * Handle the sign-in command
     * <p>
     *     This method handles the {@link SignInCommand} command and returns the user and the token.
     * </p>
     * @param command the sign-in command containing the username and password
     * @return and optional containing the user matching the username and the generated token
     * @throws RuntimeException if the user is not found or the password is invalid
     */
    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByEmail(command.email());
        if (user.isEmpty())
            throw new RuntimeException("User not found");

        if (!hashingService.matches(command.password(), user.get().getPassword()))
            throw new RuntimeException("Invalid password");


        var token = tokenService.generateToken(user.get().getEmail());
        return Optional.of(ImmutablePair.of(user.get(), token));
    }

    /**
     * Handle the sign-up command
     * <p>
     *     This method handles the {@link SignUpCommand} command and returns the user.
     * </p>
     * @param command the sign-up command containing the username and password
     * @return the created user
     */
    @Override
    public Optional<User> handle(SignUpCommand command) {
        if (userRepository.existsByEmail(command.email()))
            throw new RuntimeException("Username already exists");
        if(!Roles.exists(command.rol()))
            throw new RuntimeException("Invalid role");

        var user = new User(command.email(), hashingService.encode(command.password()), command.rol(), command.name());
        userRepository.save(user);
        return userRepository.findByEmail(command.email());
    }
}
