package com.amazon.ata.dynamodbscanandserialization.icecream;

import com.amazon.ata.dynamodbscanandserialization.icecream.dao.ReceiptDao;
import com.amazon.ata.dynamodbscanandserialization.icecream.model.Receipt;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

/**
 * Provides Ice Cream Parlor admin functionality.
 */
public class IceCreamParlorAdminService {
    private final ReceiptDao receiptDao;

    /**
     * Constructs service with the provided DAOs.
     * @param receiptDao the ReceiptDao to use for accessing receipts
     */
    @Inject
    public IceCreamParlorAdminService(ReceiptDao receiptDao) {
        this.receiptDao = receiptDao;
    }

    /**
     * Get the value of the sundae sales between the two provided dates (inclusive).
     * @param fromDate the start of the period to calculate sales for (inclusive)
     * @param toDate the end of the period to calculate sales for (inclusive)
     * @return the total amount of sales during the time period
     */
    public BigDecimal getSalesForTimePeriod(final ZonedDateTime fromDate, final ZonedDateTime toDate) {
        //return new BigDecimal(-1);
        return receiptDao.getSalesBetweenDates(fromDate, toDate);
    }

    /**
     * Returns a subset of ice cream parlor receipts. This method will return the number of receipts
     * requested by the limit parameter. After the first call to this method, the caller can provide
     * a Receipt of where to start retrieving Receipts. If the end of the receipt table has been
     * reached fewer than limit receipts may be returned.
     * @param limit the number of Receipts to be retrieved
     * @param exclusiveStartReceipt the starting location to retrieve results, null should be provided
     *                              to start at the beginning
     * @return a list of Receipts of size limit or less than limit if the end of the receipts has been reached
     */
    public List<Receipt> getCustomerReceipts(final int limit, final Receipt exclusiveStartReceipt) {
        //return Collections.emptyList();
        return receiptDao.getReceiptsPaginated(limit, exclusiveStartReceipt);
    }

}
