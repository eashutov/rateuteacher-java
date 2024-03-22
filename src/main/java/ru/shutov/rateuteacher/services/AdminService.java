package ru.shutov.rateuteacher.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shutov.rateuteacher.entities.Admin;
import ru.shutov.rateuteacher.repositories.AdminRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;

    public List<Admin> getAdmins() {
        return adminRepository.findAll();
    }

}
