package ru.shutov.rateuteacher.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.shutov.rateuteacher.repositories.AdminRepository;
import ru.shutov.rateuteacher.security.AdminDetails;

@Service
@RequiredArgsConstructor
public class AdminDetailsService implements UserDetailsService {
    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new AdminDetails(adminRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Incorrect login")));
    }
}
