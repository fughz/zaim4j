package net.vvakame.zaim4j;

/**
 * Argument for Money transfer insert API.
 * @see Zaim.Money.Transfer.Insert#execute(net.vvakame.zaim4j.Zaim.ZaimListener)
 * @author vvakame
 */
public class MoneyTransferInsertArgument {

	// reqruired
	double amount;

	String date;

	long fromAccountId;

	long toAccountId;

	// not required

	String comment;


	/**
	 * the constructor.
	 * @param amount
	 * @param date
	 * @param fromAccountId
	 * @param toAccountId
	 * @category constructor
	 * @deprecated replace to {@link #MoneyTransferInsertArgument(double, String, OtherAccountItem, OtherAccountItem)}.
	 */
	@Deprecated
	public MoneyTransferInsertArgument(double amount, String date, long fromAccountId,
			long toAccountId) {
		super();
		this.amount = amount;
		this.date = date;
		this.fromAccountId = fromAccountId;
		this.toAccountId = toAccountId;
	}

	/**
	 * the constructor.
	 * @param amount
	 * @param date
	 * @param fromAccount
	 * @param toAccount
	 * @category constructor
	 */
	public MoneyTransferInsertArgument(double amount, String date, OtherAccountItem fromAccount,
			OtherAccountItem toAccount) {
		this(amount, date, fromAccount.getId(), toAccount.getId());
	}

	@Override
	public String toString() {
		return "MoneyTransferInsertArgument [amount=" + amount + ", date=" + date
				+ ", fromAccountId=" + fromAccountId + ", toAccountId=" + toAccountId
				+ ", comment=" + comment + "]";
	}

	/**
	 * set fromAccountId.<br>
	 * fromAccountId = {@link OtherAccountItem#getId()}.
	 * @param fromAccount
	 * @category accessor
	 */
	public void setFromAccount(OtherAccountItem fromAccount) {
		setFromAccountId(fromAccount.getId());
	}

	/**
	 * @param fromAccountId the fromAccountId to set
	 * @category accessor
	 * @deprecated replace to {@link #setFromAccount(OtherAccountItem)}.
	 */
	@Deprecated
	public void setFromAccountId(long fromAccountId) {
		this.fromAccountId = fromAccountId;
	}

	/**
	 * set toAccountId.<br>
	 * toAccountId = {@link OtherAccountItem#getId()}.
	 * @param toAccount
	 * @category accessor
	 */
	public void setToAccount(OtherAccountItem toAccount) {
		setToAccountId(toAccount.getId());
	}

	/**
	 * @param toAccountId the toAccountId to set
	 * @category accessor
	 * @deprecated replace to {@link #setToAccount(OtherAccountItem)}.
	 */
	@Deprecated
	public void setToAccountId(long toAccountId) {
		this.toAccountId = toAccountId;
	}

	// ---- generated by eclipse 

	/**
	 * @return the amount
	 * @category accessor
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 * @category accessor
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @return the date
	 * @category accessor
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 * @category accessor
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the fromAccountId
	 * @category accessor
	 */
	public long getFromAccountId() {
		return fromAccountId;
	}

	/**
	 * @return the toAccountId
	 * @category accessor
	 */
	public long getToAccountId() {
		return toAccountId;
	}

	/**
	 * @return the comment
	 * @category accessor
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 * @category accessor
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
}
