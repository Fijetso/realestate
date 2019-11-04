package vn.edu.uit.realestate.Relational.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.uit.realestate.Relational.Model.Security.ConfirmationToken;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, String> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}
