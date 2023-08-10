package com.library.libraryapi.models.book;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
@EqualsAndHashCode 
public class NonFiction {
    
    @Id
    @OneToOne()
    @JoinColumn(name = "ISBN", referencedColumnName = "ISBN", unique = true)
    private Book book;

    @Column(name = "numpages", nullable = false)
    private int numpages;

    @Column(name = "genre", nullable = false)
    private String genre;

    @Column(name = "isPaperBack", nullable = false)
    private Boolean isPaperBack;
    
    @Column(name = "price", nullable = false, columnDefinition = "REAL")
    private double price;

    @Column(name = "isTextBook", nullable = false)
    private Boolean isTextBook;

    /**
     * @return int return the numpages
     */
    public int getNumpages() {
        return numpages;
    }

    /**
     * @param numpages the numpages to set
     */
    public void setNumpages(int numpages) {
        this.numpages = numpages;
    }

    /**
     * @return String return the genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * @param genre the genre to set
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * @return Boolean return the isPaperBack
     */
    public Boolean isIsPaperBack() {
        return isPaperBack;
    }

    /**
     * @param isPaperBack the isPaperBack to set
     */
    public void setIsPaperBack(Boolean isPaperBack) {
        this.isPaperBack = isPaperBack;
    }

    /**
     * @return double return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }


    /**
     * @return Boolean return the isTextBook
     */
    public Boolean isIsTextBook() {
        return isTextBook;
    }

    /**
     * @param isTextBook the isTextBook to set
     */
    public void setIsTextBook(Boolean isTextBook) {
        this.isTextBook = isTextBook;
    }


    /**
     * @return ISBN return the ISBN
     */
    public String getISBN() {
        return book.getISBN();
    }

    /**
     * @param ISBN the ISBN to set
     */
    public void setISBN(String ISBN) {
        this.book.setISBN(ISBN);
    }


}
