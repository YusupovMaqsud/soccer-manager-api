package com.example.soccermanagerapi.service;

import com.example.soccermanagerapi.entity.Player;
import com.example.soccermanagerapi.entity.Role;
import com.example.soccermanagerapi.entity.Team;
import com.example.soccermanagerapi.entity.User;
import com.example.soccermanagerapi.entity.enums.RoleEnum;
import com.example.soccermanagerapi.payload.ApiResponse;
import com.example.soccermanagerapi.payload.LoginDto;
import com.example.soccermanagerapi.payload.RegisterDto;
import com.example.soccermanagerapi.repository.PlayerRepository;
import com.example.soccermanagerapi.repository.RoleRepository;
import com.example.soccermanagerapi.repository.TeamRepository;
import com.example.soccermanagerapi.repository.UserRepository;
import com.example.soccermanagerapi.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    TeamRepository teamRepository;

    public ApiResponse registerUser(RegisterDto registerDto) {
        boolean existsByEmail = userRepository.existsByEmail(registerDto.getEmail());

        if (existsByEmail)
            return new ApiResponse("Bunday email mavjud", false);

        Optional<Team> optionalTeam = teamRepository.findById(registerDto.getTeamId());
        if(!optionalTeam.isPresent()){
            return new ApiResponse("Bunday Team mavjud",false);
        }

        User user = new User(
                registerDto.getFirstName(),
                registerDto.getLastName(),
                registerDto.getEmail(),
                passwordEncoder.encode(registerDto.getPassword()),
                roleRepository.findByRoleEnum(RoleEnum.ROLE_USER),
                optionalTeam.get()

        );
        int code = new Random().nextInt(999999);
        user.setEmailCode(String.valueOf(code).substring(0, 4));
        sendEmail(user.getEmail(), user.getEmailCode());
        userRepository.save(user);
        return new ApiResponse("User saqlandi", true);
    }


    public ApiResponse login(LoginDto loginDto) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getEmail(), loginDto.getPassword()));
            User user = (User) authenticate.getPrincipal();
            String token = jwtProvider.generateToken(loginDto.getEmail());
            return new ApiResponse("Token", true, token);
        } catch (Exception e) {
            return new ApiResponse("Login yoki parol xato", false);
        }
    }


    public Boolean sendEmail(String email, String emailCode) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("Test@gmail.com");
            mailMessage.setTo(email);
            mailMessage.setSubject("Accauntni tasdiqlash");
            mailMessage.setText(emailCode);
            javaMailSender.send(mailMessage);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public ApiResponse verifyEmail(String email, String emailCode) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (emailCode.equals(user.getEmailCode())) {
                user.setEnabled(true);
                userRepository.save(user);
                return new ApiResponse("Accaount aktivlashtirildi", true);
            }
            return new ApiResponse("Kod xato", false);
        }
        return new ApiResponse("Bunday user mavjud emas", false);

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(username);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new UsernameNotFoundException(username + "Topilmadi");
    }



}
