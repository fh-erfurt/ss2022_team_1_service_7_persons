package de.fherfurt.person.person.entity.models;

import de.fherfurt.person.core.persistence.BaseBusinessEntity;
import lombok.*;

/**
 * Data object that holds the information from the database.
 *
 * @author Tobias Kärst <tobias.kaerst@fh-erfurt.de>
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode( onlyExplicitlyIncluded = true, callSuper = true )
public class Account extends BaseBusinessEntity
{
    private String username;
    private String email;
    private String password;

    @Builder( setterPrefix = "with" )
    public Account(int id, String username, String email, String password )
    {
        super( id );
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
