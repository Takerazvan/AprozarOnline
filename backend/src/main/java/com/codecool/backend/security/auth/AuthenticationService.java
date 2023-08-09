    package com.codecool.backend.security.auth;



    import com.codecool.backend.exception.ResourceNotFoundException;
    import com.codecool.backend.notifications.EmailService;
    import com.codecool.backend.security.jwt.JWTService;
    import com.codecool.backend.users.repository.AppUser;
    import com.codecool.backend.users.repository.AppUserDTO;
    import com.codecool.backend.users.repository.AppUserDTOMapper;
    import com.codecool.backend.users.RegistrationRequest;
    import com.codecool.backend.users.repository.AppUserRepository;
    import com.codecool.backend.users.service.AppUserService;
    import jakarta.servlet.http.HttpServletRequest;
    import org.springframework.beans.factory.annotation.Qualifier;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.security.core.Authentication;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.stereotype.Service;

    import java.time.temporal.ChronoUnit;
    import java.util.Optional;

    @Service
    public class AuthenticationService {
        private final AuthenticationManager authenticationManager;
        private final JWTService jwtService;
        private final AppUserDTOMapper appUserDTOMapper;
        private final AppUserService userService;
        private EmailService emailService;
        private AppUserRepository userRepository;



        public AuthenticationService(AuthenticationManager authenticationManager, JWTService jwtService, AppUserDTOMapper appUserDTOMapper, @Qualifier("appUser") AppUserService userService, EmailService emailService, AppUserRepository userRepository) {
            this.authenticationManager = authenticationManager;
            this.jwtService = jwtService;
            this.appUserDTOMapper = appUserDTOMapper;
            this.userService = userService;
            this.emailService = emailService;
            this.userRepository = userRepository;
        }

        public AuthenticationResponse login(LoginRequest request) {
            System.out.println("Email="+ request.email());
            System.out.println("Password="+ request.password());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()
                    )
            );
            AppUser principal = (AppUser) authentication.getPrincipal();
            if (!principal.isVerified()) {
                throw new IllegalStateException("Email not verified");
            }
            AppUserDTO userDTO = appUserDTOMapper.apply(principal);
            String token = jwtService.issueToken(userDTO.email());


            return new AuthenticationResponse(token, userDTO);
        }

        public AuthenticationResponse registerCustomer(RegistrationRequest request) {
            AppUser newUser = userService.addUser(request);

            AppUserDTO newUserDTO = appUserDTOMapper.apply(newUser);


            String token = jwtService.issueToken(newUserDTO.email());
            String email = newUser.getEmail();

            System.out.println(jwtService.getSubject(token));

            // Generate verification link
            String verificationLink =
                    "http://localhost:8080/api/auth/user?token=" + token;


            emailService.send(email, verificationLink);
            return new AuthenticationResponse(token, newUserDTO);
        }

        public void logout(HttpServletRequest request) {
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String jwt = authHeader.substring(7);
                String subject = jwtService.getSubject(jwt);
                if (subject != null) {
                    SecurityContextHolder.getContext().setAuthentication(null);
                }
            }
        }


        public boolean verifyEmail(String token) {
            String email = jwtService.getSubject(token);

            Optional<AppUser> optionalUser = userRepository.findAppUserByEmail(email);

            if (optionalUser.isPresent()) {
                AppUser user = optionalUser.get();
                user.setVerified(true);
                userRepository.save(user);

                return true;
            }

            return false;
        }
        public String generateResetPasswordToken(String email) {
            //TODO replace in front ""
            Optional<AppUser> optionalUser = userRepository.findAppUserByEmail(email.replace("\"", "").trim());

           if(optionalUser.isPresent()){

               return   jwtService.issueTokenEmail(email,10,ChronoUnit.MINUTES);
           } else {
               throw new ResourceNotFoundException("User does not exist");
           }


        }

        public boolean isResetTokenExpired(String token) {
            return jwtService.isTokenLinkExpired(token);
        }
    }
