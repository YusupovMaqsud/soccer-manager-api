package com.example.soccermanagerapi.component;

import com.example.soccermanagerapi.entity.Role;
import com.example.soccermanagerapi.entity.enums.PermissionEnum;
import com.example.soccermanagerapi.entity.enums.RoleEnum;
import com.example.soccermanagerapi.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;

import static com.example.soccermanagerapi.entity.enums.PermissionEnum.*;
import static com.example.soccermanagerapi.entity.enums.RoleEnum.ROLE_ADMIN;
import static com.example.soccermanagerapi.entity.enums.RoleEnum.ROLE_USER;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    RoleRepository roleRepository;

    @Value("${spring.sql.init.mode}")
    private String initialMode;


    @Override
    public void run(String... args) throws Exception {

        if(initialMode.equals("always")){
            PermissionEnum[] permissionEnums = PermissionEnum.values();
            roleRepository.save(new Role(
                    RoleEnum.ROLE_ADMIN,
                    Arrays.asList(permissionEnums)
            ));

            roleRepository.save(new Role(
                    RoleEnum.ROLE_USER,
                    Arrays.asList(VIEW_MY_TEAM,ADD_TRANSFER,
                            VIEW_MY_TRANSFER,VIEW_PLAYER,VIEW_TRANSFER)
            ));
        }

    }
}
