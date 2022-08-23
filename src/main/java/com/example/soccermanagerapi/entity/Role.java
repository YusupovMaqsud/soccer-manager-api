package com.example.soccermanagerapi.entity;

import com.example.soccermanagerapi.entity.enums.PermissionEnum;
import com.example.soccermanagerapi.entity.enums.RoleEnum;
import com.example.soccermanagerapi.entity.template.AbsLongEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role extends AbsLongEntity{

    @Column(nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    private RoleEnum roleName;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<PermissionEnum> permissionEnumList;

    public Role(RoleEnum roleName, List<PermissionEnum> permissionEnumList) {
        this.roleName = roleName;
        this.permissionEnumList = permissionEnumList;
    }
}
