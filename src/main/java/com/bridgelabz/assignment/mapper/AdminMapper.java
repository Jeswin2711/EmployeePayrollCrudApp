package com.bridgelabz.assignment.mapper;

import com.bridgelabz.assignment.admin.dto.AdminDto;
import com.bridgelabz.assignment.admin.model.Admin;
import org.mapstruct.Mapper;

import java.util.List;

/*
    Mapper class for Admin
 */
@Mapper(componentModel = "spring")
public interface AdminMapper
{
    Admin dtoToModel(AdminDto adminDto);

    List<Admin> dtoToModels(List<AdminDto> adminDtos);

    AdminDto modelToDto(Admin admin);

    List<AdminDto> modelsToDtos(List<Admin> admins);
}
