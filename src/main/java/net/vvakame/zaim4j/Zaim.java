package net.vvakame.zaim4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import net.vvakame.util.jsonpullparser.JsonFormatException;

/**
 * API for Zaim.
 * @author vvakame
 */
public class Zaim {

	OAuthConnector connector;


	private Zaim() {
	}

	/**
	 * Get new instance, it do not has user authenticate credential.
	 * @param configuration
	 * @return Zaim instance
	 * @see OAuthConfiguration
	 * @see OAuthCredential
	 * @author vvakame
	 */
	public static Zaim newInstanceWithUserAuthenticationNotRequired(OAuthConfiguration configuration) {
		Zaim zaim = new Zaim();
		zaim.connector = new OAuthConnector(configuration, null);
		return zaim;
	}

	/**
	 * Get new instance.
	 * @param credential
	 * @return Zaim instance
	 * @author vvakame
	 */
	public static Zaim newInstance(OAuthCredential credential) {
		Zaim zaim = new Zaim();
		zaim.connector = new OAuthConnector(credential.configuration, credential);
		return zaim;
	}

	/**
	 * API about user.
	 * @return user api
	 * @author vvakame
	 */
	public User user() {
		return new User();
	}

	/**
	 * API about money.
	 * @return money api
	 * @author vvakame
	 */
	public Money money() {
		return new Money();
	}

	/**
	 * API about account.
	 * @return account api
	 * @author vvakame
	 */
	public Account account() {
		return new Account();
	}

	/**
	 * API about category.
	 * @return category api
	 * @author vvakame
	 */
	public Category category() {
		return new Category();
	}

	/**
	 * API about genre.
	 * @return genre api
	 * @author vvakame
	 */
	public Genre genre() {
		return new Genre();
	}

	/**
	 * API about other.
	 * @return other api
	 * @author vvakame
	 */
	public Other other() {
		return new Other();
	}


	/**
	 * Zaim API call result listener.
	 * @author vvakame
	 * @param <S>
	 */
	public interface ZaimListener<S> {

		/**
		 * Call on success.
		 * @param success
		 * @author vvakame
		 */
		public void onSuccess(S success);

		/**
		 * Call on failure
		 * @param failure
		 * @author vvakame
		 */
		public void onFailure(ErrorResponse failure);

		/**
		 * Call on error
		 * @param e
		 * @author vvakame
		 */
		public void onError(Exception e);
	}

	interface ResponseConverter<T> {

		public T convert(InputStream is) throws IOException, JsonFormatException;
	}


	<T>void doCallback(HttpURLConnection connection, ZaimListener<T> listener,
			ResponseConverter<T> converter) {

		try {
			final int responseCode = connection.getResponseCode();
			if (responseCode == 200) {
				listener.onSuccess(converter.convert(connection.getInputStream()));
			} else if (responseCode == 401) {
				String message = OAuthConnector.streamToString(connection.getErrorStream());
				listener.onError(new IllegalAccessException(message));
			} else {
				System.err.println("status code:" + connection.getResponseCode());
				listener.onFailure(ErrorResponseGen.get(connection.getErrorStream()));
			}
		} catch (IOException e) {
			listener.onError(e);
		} catch (JsonFormatException e) {
			listener.onError(e);
		}
	}


	/**
	 * User API.
	 * @author vvakame
	 */
	public class User {

		private User() {
		}


		/**
		 * User verify API.
		 * @author vvakame
		 */
		public class Verify {

			private Verify() {
			}

			/**
			 * Get user verify api.
			 * @param listener
			 * @author vvakame
			 */
			public void execute(ZaimListener<UserVerifyResponse> listener) {
				if (listener == null) {
					throw new NullPointerException("listener is required");
				}

				HttpURLConnection connection = connector.doGet("/v2/home/user/verify", null);

				doCallback(connection, listener, new ResponseConverter<UserVerifyResponse>() {

					@Override
					public UserVerifyResponse convert(InputStream is) throws IOException,
							JsonFormatException {
						return UserVerifyResponseGen.get(is);
					}
				});
			}
		}


		/**
		 * user verify api.
		 * @return verify api
		 * @author vvakame
		 */
		public Verify verify() {
			return new Verify();
		}
	}

	/**
	 * Money API.
	 * @author vvakame
	 */
	public class Money {

		private Money() {
		}


		/**
		 * Money list API.
		 * @author vvakame
		 */
		public class List {

			MoneyListArgument arg;


			private List(MoneyListArgument arg) {
				if (arg == null) {
					this.arg = new MoneyListArgument();
				} else {
					this.arg = arg;
				}
			}

			/**
			 * Get money list api.
			 * @param listener
			 * @author vvakame
			 */
			public void execute(ZaimListener<MoneyListResponse> listener) {
				if (listener == null) {
					throw new NullPointerException("listener is required");
				}

				Map<String, String> params = new HashMap<String, String>();
				if (arg.getCategoryId() != null) {
					params.put("category_id", String.valueOf(arg.getCategoryId()));
				}
				if (arg.getGenreId() != null) {
					params.put("genre_id", String.valueOf(arg.getGenreId()));
				}
				if (arg.getMode() != null) {
					params.put("mode", arg.getMode().name().toLowerCase());
				}
				if (arg.getOrder() != null) {
					params.put("order", arg.getOrder().name().toLowerCase());
				}
				if (arg.getStartDate() != null) {
					params.put("start_date", arg.getStartDate());
				}
				if (arg.getEndDate() != null) {
					params.put("end_date", arg.getEndDate());
				}
				if (arg.getPage() != null) {
					params.put("page", String.valueOf(arg.getPage()));
				}
				if (arg.getLimit() != null) {
					params.put("limit", String.valueOf(arg.getLimit()));
				}
				HttpURLConnection connection = connector.doGet("/v2/home/money", params);

				doCallback(connection, listener, new ResponseConverter<MoneyListResponse>() {

					@Override
					public MoneyListResponse convert(InputStream is) throws IOException,
							JsonFormatException {
						return MoneyListResponseGen.get(is);
					}
				});
			}
		}


		/**
		 * Money list api.
		 * @param arg
		 * @return list api
		 * @author vvakame
		 */
		public List list(MoneyListArgument arg) {
			return new List(arg);
		}

		/**
		 * Money list api.
		 * @return list api
		 * @author vvakame
		 */
		public List list() {
			return new List(null);
		}


		/**
		 * Money payment api.
		 * @author vvakame
		 */
		public class Payment {

			MoneyPaymentArgument arg;


			private Payment(MoneyPaymentArgument arg) {
				if (arg == null) {
					throw new NullPointerException("argment is reqruired.");
				} else {
					this.arg = arg;
				}
			}

			/**
			 * Get money list api.
			 * @param listener
			 * @author vvakame
			 */
			public void execute(ZaimListener<MoneyPostResponse> listener) {
				if (listener == null) {
					throw new NullPointerException("listener is required");
				}

				Map<String, String> params = new HashMap<String, String>();
				// required
				params.put("category_id", String.valueOf(arg.getCategoryId()));
				params.put("genre_id", String.valueOf(arg.getGenreId()));
				params.put("amount", String.valueOf(arg.getAmount()));
				params.put("date", String.valueOf(arg.getDate()));
				// optional
				if (arg.getFromAccountId() != null) {
					params.put("from_account_id", String.valueOf(arg.getFromAccountId()));
				}
				if (arg.getComment() != null) {
					params.put("comment", arg.getComment());
				}
				if (arg.getName() != null) {
					params.put("name", arg.getName());
				}
				if (arg.getPlace() != null) {
					params.put("place", arg.getPlace());
				}

				HttpURLConnection connection = connector.doPost("/v2/home/money/payment", params);

				doCallback(connection, listener, new ResponseConverter<MoneyPostResponse>() {

					@Override
					public MoneyPostResponse convert(InputStream is) throws IOException,
							JsonFormatException {
						return MoneyPostResponseGen.get(is);
					}
				});
			}
		}


		/**
		 * Money payment api.
		 * @param arg
		 * @return payment api
		 * @author vvakame
		 */
		public Payment payment(MoneyPaymentArgument arg) {
			return new Payment(arg);
		}


		/**
		 * Money income api.
		 * @author vvakame
		 */
		public class Income {

			MoneyIncomeArgument arg;


			private Income(MoneyIncomeArgument arg) {
				if (arg == null) {
					throw new NullPointerException("argment is reqruired.");
				} else {
					this.arg = arg;
				}
			}

			/**
			 * Get money list api.
			 * @param listener
			 * @author vvakame
			 */
			public void execute(ZaimListener<MoneyPostResponse> listener) {
				if (listener == null) {
					throw new NullPointerException("listener is required");
				}

				Map<String, String> params = new HashMap<String, String>();
				// required
				params.put("category_id", String.valueOf(arg.getCategoryId()));
				params.put("amount", String.valueOf(arg.getAmount()));
				params.put("date", String.valueOf(arg.getDate()));
				// optional
				if (arg.getToAccountId() != null) {
					params.put("to_account_id", String.valueOf(arg.getToAccountId()));
				}
				if (arg.getComment() != null) {
					params.put("comment", arg.getComment());
				}

				HttpURLConnection connection = connector.doPost("/v2/home/money/income", params);

				doCallback(connection, listener, new ResponseConverter<MoneyPostResponse>() {

					@Override
					public MoneyPostResponse convert(InputStream is) throws IOException,
							JsonFormatException {
						return MoneyPostResponseGen.get(is);
					}
				});
			}
		}


		/**
		 * Money income api.
		 * @param arg
		 * @return income api
		 * @author vvakame
		 */
		public Income income(MoneyIncomeArgument arg) {
			return new Income(arg);
		}


		/**
		 * Money transfer api.
		 * @author vvakame
		 */
		public class Transfer {

			MoneyTransferArgument arg;


			private Transfer(MoneyTransferArgument arg) {
				if (arg == null) {
					throw new NullPointerException("argment is reqruired.");
				} else {
					this.arg = arg;
				}
			}

			/**
			 * Get money list api.
			 * @param listener
			 * @author vvakame
			 */
			public void execute(ZaimListener<MoneyPostResponse> listener) {
				if (listener == null) {
					throw new NullPointerException("listener is required");
				}

				Map<String, String> params = new HashMap<String, String>();
				// required
				params.put("amount", String.valueOf(arg.getAmount()));
				params.put("date", String.valueOf(arg.getDate()));
				params.put("from_account_id", String.valueOf(arg.getFromAccountId()));
				params.put("to_account_id", String.valueOf(arg.getToAccountId()));
				// optional
				if (arg.getComment() != null) {
					params.put("comment", arg.getComment());
				}

				HttpURLConnection connection = connector.doPost("/v2/home/money/transfer", params);

				doCallback(connection, listener, new ResponseConverter<MoneyPostResponse>() {

					@Override
					public MoneyPostResponse convert(InputStream is) throws IOException,
							JsonFormatException {
						return MoneyPostResponseGen.get(is);
					}
				});
			}
		}


		/**
		 * Money transfer api.
		 * @param arg
		 * @return transfer api
		 * @author vvakame
		 */
		public Transfer transfer(MoneyTransferArgument arg) {
			return new Transfer(arg);
		}
	}

	/**
	 * Account API.
	 * @author vvakame
	 */
	public class Account {
	}

	/**
	 * Category API.
	 * @author vvakame
	 */
	public class Category {
	}

	/**
	 * Genre API.
	 * @author vvakame
	 */
	public class Genre {
	}

	/**
	 * Other API.
	 * @author vvakame
	 */
	public class Other {

	}
}