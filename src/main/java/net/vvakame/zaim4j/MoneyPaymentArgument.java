package net.vvakame.zaim4j;

/**
 * Argument for Money payment API.
 * @see Zaim.Money#payment(MoneyPaymentArgument)
 * @author vvakame
 */
public class MoneyPaymentArgument {

	// reqruired

	long categoryId;

	long genreId;

	double amount;

	String date;

	// not required

	Long fromAccountId;

	String comment;

	String name;

	String place;


	/**
	 * the constructor.
	 * @param categoryId
	 * @param genreId
	 * @param amount
	 * @param date
	 * @category constructor
	 */
	public MoneyPaymentArgument(long categoryId, long genreId, double amount, String date) {
		this.categoryId = categoryId;
		this.genreId = genreId;
		this.amount = amount;
		this.date = date;
	}

	/**
	 * @return the categoryId
	 * @category accessor
	 */
	public long getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId the categoryId to set
	 * @category accessor
	 */
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the genreId
	 * @category accessor
	 */
	public long getGenreId() {
		return genreId;
	}

	/**
	 * @param genreId the genreId to set
	 * @category accessor
	 */
	public void setGenreId(long genreId) {
		this.genreId = genreId;
	}

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
	public Long getFromAccountId() {
		return fromAccountId;
	}

	/**
	 * @param fromAccountId the fromAccountId to set
	 * @category accessor
	 */
	public void setFromAccountId(Long fromAccountId) {
		this.fromAccountId = fromAccountId;
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

	/**
	 * @return the name
	 * @category accessor
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 * @category accessor
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the place
	 * @category accessor
	 */
	public String getPlace() {
		return place;
	}

	/**
	 * @param place the place to set
	 * @category accessor
	 */
	public void setPlace(String place) {
		this.place = place;
	}
}
