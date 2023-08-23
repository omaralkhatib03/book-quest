package com.library.libraryapi_2_7_14.models.token;

import com.library.libraryapi_2_7_14.models.user.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tokens", uniqueConstraints = @UniqueConstraint(columnNames = { "TOKEN" }))
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Token {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TID")
    private Integer id;

    @Column(name = "TOKEN", unique = true)
    private String token;

    @Column(name = "EXPIRATION")
    private long expiration;

    @Column(name  = "ISSUED_AT")
    private long issuedAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UID")
    private User user;


    /**
     * @return Integer return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return String return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return long return the expiration
     */
    public long getExpiration() {
        return expiration;
    }

    /**
     * @param expiration the expiration to set
     */
    public void setExpiration(long expiration) {
        this.expiration = expiration;
    }

    /**
     * @return long return the issuedAt
     */
    public long getIssuedAt() {
        return issuedAt;
    }

    /**
     * @param issuedAt the issuedAt to set
     */
    public void setIssuedAt(long issuedAt) {
        this.issuedAt = issuedAt;
    }

    /**
     * @return User return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

}
