package de.fherfurt.persons.client.objects;

import lombok.*;

/**
 * Container that holds all information of a persons account for the transport from the
 * <i>PERSON-Service</i> to another one.
 *
 * @author Tobias Kärst <tobias.kaerstn@fh-erfurt.de>
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with", toBuilder = true)
@EqualsAndHashCode( onlyExplicitlyIncluded = true )
public class AccountDto {
    /**
     * Unique identifier of an already persisted account.
     */
    private Long id = 0L;

    /**
     * The database version.
     */
    private Long version;

    /**
     * The username of the persons account.
     */
    private String username;

    /**
     * The email of the persons account.
     */
    private String email;

    /**
     * The hashed password of the persons account.
     */
    private String password;

    /**
     * Checks if account has all needed attributes.
     */
    public boolean isValidToCreate() {
        return (
            this.email != null &&
            this.username != null
        );
    }

    public void merge(AccountDto newAccount) {
        this.email = newAccount.getEmail() != null ? newAccount.getEmail() : this.email;
        this.username = newAccount.getUsername() != null ? newAccount.getUsername() : this.username;
        this.password = newAccount.getPassword() != null ? newAccount.getPassword() : this.password;
    }
}
