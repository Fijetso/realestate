package vn.edu.uit.realestate.DataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.uit.realestate.Model.Security.ConfirmationToken;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, String> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}
