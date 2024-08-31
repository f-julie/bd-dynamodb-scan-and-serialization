package com.amazon.ata.dynamodbscanandserialization.icecream.model;

import com.amazon.ata.dynamodbscanandserialization.icecream.converter.SundaeConverter;
import com.amazon.ata.dynamodbscanandserialization.icecream.converter.ZonedDateTimeConverter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Represents a receipt item in the DynamoDBScan-Receipts table.
 */
@DynamoDBTable(tableName = "DynamoDBScan-Receipts")
public class Receipt {
    private String customerId;
    private ZonedDateTime purchaseDate;
    private BigDecimal salesTotal;

    private List<Sundae> sundaes;

    @DynamoDBHashKey(attributeName = "customerId")
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @DynamoDBRangeKey(attributeName = "purchaseDate")
    @DynamoDBTypeConverted(converter = ZonedDateTimeConverter.class)
    public ZonedDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(ZonedDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @DynamoDBAttribute(attributeName = "salesTotal")
    public BigDecimal getSalesTotal() {
        return salesTotal;
    }

    public void setSalesTotal(BigDecimal salesTotal) {
        this.salesTotal = salesTotal;
    }

    //@DynamoDBIgnore
    @DynamoDBAttribute(attributeName = "sundaes")
    @DynamoDBTypeConverted(converter = SundaeConverter.class)
    public List<Sundae> getSundaes() {
        // TODO: updated this getter
        //return Collections.emptyList();
        return sundaes;
    }

    public void setSundaes(List<Sundae> sundaes) {
        this.sundaes = sundaes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Receipt receipt = (Receipt) o;
        return customerId.equals(receipt.customerId) &&
            purchaseDate.equals(receipt.purchaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, purchaseDate);
    }

}
