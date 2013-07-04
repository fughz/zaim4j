package net.vvakame.zaim4j;

import java.util.List;

import net.vvakame.util.jsonpullparser.annotation.JsonKey;
import net.vvakame.util.jsonpullparser.annotation.JsonModel;

/**
 * Category list response.
 * @author vvakame
 */
@JsonModel(decamelize = true, genToPackagePrivate = true, treatUnknownKeyAsError = true)
public class OtherCategoryListResponse {

	@JsonKey
	List<OtherCategoryItem> categories;

	@JsonKey
	long requested;


	/**
	 * @return the categories
	 * @category accessor
	 */
	public List<OtherCategoryItem> getCategories() {
		return categories;
	}

	/**
	 * @param categories the categories to set
	 * @category accessor
	 */
	public void setCategories(List<OtherCategoryItem> categories) {
		this.categories = categories;
	}

	/**
	 * @return the requested
	 * @category accessor
	 */
	public long getRequested() {
		return requested;
	}

	/**
	 * @param requested the requested to set
	 * @category accessor
	 */
	public void setRequested(long requested) {
		this.requested = requested;
	}
}
