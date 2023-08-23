package com.library.libraryapi_2_7_14.models.book;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
@EqualsAndHashCode 
public class Periodical implements Serializable{
    
    @Id
    @OneToOne()
    @JoinColumn(name = "ISBN", referencedColumnName = "ISBN", unique = true)
    private Book book;

    @Column(name = "numpages", nullable = false)
    private int numpages;
    
    @Column(name = "seriesNo", nullable = false)
    private int seriesNo;
    
    @Column(name = "issueNo", nullable = false)
    private int issueNo;
    
    @Column(name = "price", nullable = false, columnDefinition = "REAL")
    private double price;


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
     * @return int return the seriesNo
     */
    public int getSeriesNo() {
        return seriesNo;
    }

    /**
     * @param seriesNo the seriesNo to set
     */
    public void setSeriesNo(int seriesNo) {
        this.seriesNo = seriesNo;
    }

    /**
     * @return int return the issueNo
     */
    public int getIssueNo() {
        return issueNo;
    }

    /**
     * @param issueNo the issueNo to set
     */
    public void setIssueNo(int issueNo) {
        this.issueNo = issueNo;
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
